package com.med.library.service.impl;

import com.med.library.dTo.BookDTO;
import com.med.library.entity.Book;
import com.med.library.entity.Borrow;
import com.med.library.mapper.BookMapper;
import com.med.library.repository.AuthorRepository;
import com.med.library.repository.BookRepository;
import com.med.library.repository.PublisherRepository;
import com.med.library.restExceptionHandler.exception.HttpNotFoundException;
import com.med.library.service.BookService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private PublisherRepository publisherRepository;

    private BookMapper bookMapper = Mappers.getMapper(BookMapper.class);

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public List<BookDTO> getAll() {
        List<Book> books = bookRepository.findAll();
        return bookMapper.booksToBookDTOs(books);
    }

    @Override
    public BookDTO findById(Long bookId) {
        Book book = bookRepository.findById(bookId).orElse(null);
        if ( book == null ) {
            throw new HttpNotFoundException("ID " + bookId + " not found.");
        }
        return bookMapper.bookToBookDTO(book);
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void deleteById(Long bookId) {
        bookRepository.deleteById(bookId);
    }

    @Override
    public void addBorrow(Book book, Borrow borrow) {
        book.addBorrow(borrow);
        bookRepository.save(book);
    }
}
