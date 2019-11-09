package com.med.library.service;

import com.med.library.entity.Book;
import com.med.library.entity.Publisher;

import java.util.List;
import java.util.Optional;

public interface PublisherService {
    public List<Publisher> getAll();
    public Optional<Publisher> getById(Long publisherId);
    public Publisher save(Publisher publisher);
    public void deleteById(Long publisherId);
    public void addBook(Publisher publisher, Book book);
}
