package com.med.library.service;

import com.med.library.entity.Author;
import com.med.library.entity.Book;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    public List<Author> getAuthors();
    public Optional<Author> getAuthorById(Long authorId);
    public Author saveAuthor(Author author);
    public void deleteAuthorById(Long authorId);
    public void addBook(Author author, Book book);
}
