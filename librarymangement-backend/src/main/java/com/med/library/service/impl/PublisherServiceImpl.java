package com.med.library.service.impl;

import com.med.library.entity.Book;
import com.med.library.entity.Publisher;
import com.med.library.repository.PublisherRepository;
import com.med.library.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class PublisherServiceImpl implements PublisherService {

    private PublisherRepository publisherRepository;

    @Autowired
    public PublisherServiceImpl(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @Override
    public List<Publisher> getAll() {
        return publisherRepository.findAll();
    }

    @Override
    public Optional<Publisher> getById(Long publisherId) {
        return publisherRepository.findById(publisherId);
    }

    @Override
    public Publisher save(Publisher publisher) {
        Optional<Publisher> publisherDb = publisherRepository.findByName(publisher.getName().trim());
        if (publisherDb.isPresent()) {
            throw new RuntimeException(publisher.getName() + " already exist in database.");
        }
        return publisherRepository.save(publisher);
    }

    @Override
    public void deleteById(Long publisherId) {
        Optional<Publisher> publisherDb = publisherRepository.findById(publisherId);
        if (publisherDb.isPresent()) {
            throw new RuntimeException(" Publisher not found.");
        }
        publisherRepository.deleteById(publisherId);
    }

    @Override
    public void addBook(Publisher publisher, Book book) {
        publisher.addBook(book);
        publisherRepository.save(publisher);
    }
}
