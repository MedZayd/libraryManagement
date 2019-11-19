package com.med.library.service.impl;

import com.med.library.dTo.PublisherDTO;
import com.med.library.entity.Book;
import com.med.library.entity.Publisher;
import com.med.library.mapper.PublisherMapper;
import com.med.library.repository.BookRepository;
import com.med.library.repository.PublisherRepository;
import com.med.library.restExceptionHandler.exception.EntityHasAssociation;
import com.med.library.restExceptionHandler.exception.HttpNotFoundException;
import com.med.library.service.PublisherService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class PublisherServiceImpl implements PublisherService {

    private PublisherMapper publisherMapper = Mappers.getMapper(PublisherMapper.class);

    private PublisherRepository publisherRepository;
    private BookRepository bookRepository;

    @Autowired
    public PublisherServiceImpl(PublisherRepository publisherRepository, BookRepository bookRepository) {
        this.publisherRepository = publisherRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<PublisherDTO> findAllEnabled() {
        List<Publisher> publishers = publisherRepository.findByEnabledTrue();
        return publisherMapper.toDtos(publishers);
    }

    @Override
    public PublisherDTO findById(long publisherId) {
        Publisher publisher = validateId(publisherId);
        return publisherMapper.toDto(publisher);
    }

    @Override
    public PublisherDTO save(PublisherDTO publisherDTO) {
        String publisherName = publisherDTO.getName().trim();
        publisherDTO.setName(publisherName);
        Publisher persistedPublisher = publisherRepository.save(publisherMapper.toEntity(publisherDTO));
        return publisherMapper.toDto(persistedPublisher);
    }

    @Override
    public PublisherDTO update(PublisherDTO publisherDTO, long publisherId) {
        Publisher publisher = validateId(publisherId);
        String publisherName = publisherDTO.getName().trim();
        publisher.setName(publisherName);
        Publisher updatedPublisher = publisherRepository.save(publisher);
        return publisherMapper.toDto(updatedPublisher);
    }

    @Override
    public PublisherDTO enable(boolean enable, long publisherId) {
        Publisher publisher = validateId(publisherId);
        publisher.setEnabled(enable);
        publisherRepository.save(publisher);
        return publisherMapper.toDto(publisher);
    }

    @Override
    public void delete(Long publisherId) {
        Publisher publisher = validateId(publisherId);
        List<Book> books = bookRepository.findByPublisher(publisher);
        int size = books.size();
        if(size>0) {
            throw new EntityHasAssociation("Publisher is associated with " + size + " books.");
        }
        publisherRepository.delete(publisher);
    }

    private Publisher validateId(long publisherId) {
        Optional<Publisher> publisher = publisherRepository.findById(publisherId);
        if(!publisher.isPresent()) throw new HttpNotFoundException("Publisher with ID " + publisherId + " not found !");
        return publisher.get();
    }

}
