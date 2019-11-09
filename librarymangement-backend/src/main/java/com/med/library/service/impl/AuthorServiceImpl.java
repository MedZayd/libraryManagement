package com.med.library.service.impl;

import com.med.library.entity.Author;
import com.med.library.entity.Book;
import com.med.library.repository.AuthorRepository;
import com.med.library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> getById(Long authorId) {
        return authorRepository.findById(authorId);
    }

    @Override
    public Author save(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public void deleteById(Long authorId) {
        authorRepository.deleteById(authorId);
    }

    @Override
    public void addBook(Author author, Book book) {
        author.addBook(book);
        authorRepository.save(author);
    }
}
