package com.med.library.service;

import com.med.library.entity.Author;
import com.med.library.entity.Book;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    public List<Author> getAll();
    public Optional<Author> getById(Long authorId);
    public Author save(Author author);
    public void deleteById(Long authorId);
    public void addBook(Author author, Book book);
}
