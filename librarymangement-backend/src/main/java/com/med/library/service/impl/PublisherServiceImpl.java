package com.med.library.service.impl;

import com.med.library.dTo.PublisherDTO;
import com.med.library.entity.Book;
import com.med.library.entity.Publisher;
import com.med.library.mapper.PublisherMapper;
import com.med.library.repository.PublisherRepository;
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

    @Autowired
    public PublisherServiceImpl(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
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
        validateName(publisherName);
        publisherDTO.setName(publisherName);
        Publisher persistedPublisher = publisherRepository.save(publisherMapper.toEntity(publisherDTO));
        return publisherMapper.toDto(persistedPublisher);
    }

    @Override
    public PublisherDTO update(PublisherDTO publisherDTO, long publisherId) {
        Publisher publisher = validateId(publisherId);
        String publisherName = publisherDTO.getName().trim();
        validateName(publisherName);
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
        publisherRepository.delete(publisher);
    }

    private Publisher validateId(long publisherId) {
        Optional<Publisher> publisher = publisherRepository.findById(publisherId);
        if(!publisher.isPresent()) throw new HttpNotFoundException("Publisher with ID " + publisherId + " not found !");
        return publisher.get();
    }

    private void validateName(String name) {
        Optional<Publisher> publisherDb = publisherRepository.findByName(name);
        if (publisherDb.isPresent()) throw new RuntimeException("Publisher " + name + " already exist in database.");
    }
}
