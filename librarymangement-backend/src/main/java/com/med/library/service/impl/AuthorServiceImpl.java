package com.med.library.service.impl;

import com.med.library.entity.Author;
import com.med.library.entity.Book;
import com.med.library.repository.AuthorRepository;
import com.med.library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class AuthorServiceImpl implements AuthorService {

    private Logger logger = Logger.getLogger(this.getClass().getName());

    private AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> getAuthorById(Long authorId) {
        return authorRepository.findById(authorId);
    }

    @Override
    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public void deleteAuthorById(Long authorId) {
        authorRepository.deleteById(authorId);
    }

    @Override
    public void addBook(Author author, Book book) {
        author.addBook(book);
        authorRepository.save(author);
    }
}
