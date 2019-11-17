package com.med.library.service.impl;

import com.med.library.dTo.BookDTO;
import com.med.library.dTo.BookPAs;
import com.med.library.entity.Author;
import com.med.library.entity.Book;
import com.med.library.entity.Publisher;
import com.med.library.mapper.BookMapper;
import com.med.library.repository.AuthorRepository;
import com.med.library.repository.BookRepository;
import com.med.library.repository.PublisherRepository;
import com.med.library.restExceptionHandler.exception.HttpNotFoundException;
import com.med.library.restExceptionHandler.exception.InvalidIdException;
import com.med.library.service.BookService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Transactional
@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private PublisherRepository publisherRepository;

    private BookMapper bookMapper = Mappers.getMapper(BookMapper.class);

    @Autowired
    public BookServiceImpl(
            BookRepository bookRepository,
            AuthorRepository authorRepository,
            PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public List<BookDTO> getAll() {
        List<Book> books = bookRepository.findAll();
        return bookMapper.toDTOs(books);
    }

    @Override
    public BookDTO findById(Long bookId) {
        Book book = validateBookId(bookId);
        return bookMapper.toDTO(book);
    }

    @Override
    public BookDTO save(BookDTO bookDTO) {
        Book book = bookMapper.toEntity(bookDTO);
        Book persistedBook = bookRepository.save(book);
        return bookMapper.toDTO(persistedBook);
    }

    @Override
    public void deleteById(Long bookId) {
        Book book = validateBookId(bookId);
        bookRepository.delete(book);
    }

    @Override
    public BookDTO setBookAuthorsAndPublisher(long bookId, BookPAs bookPAs) {
        Book book = validateBookId(bookId);
        if(bookPAs.getAuthorsId() != null && bookPAs.getAuthorsId().size() > 0) {
            List<Long> authorsId = bookPAs.getAuthorsId();
            List<Author> authors = book.getAuthors();
            for (Long id : authorsId) {
                Author author = validateAuthorId(id);
                if (authors.indexOf(author) == -1) {
                    authors.add(author);
                }
            }
            book.setAuthors(authors);
        }
        if(bookPAs.getPublisherId() != null) {
            Publisher publisher = validatePublisherId(bookPAs.getPublisherId());
            if (!publisher.equals(book.getPublisher())) {
                book.setPublisher(publisher);
            }
        }
        Book updatedBook = bookRepository.save(book);
        return bookMapper.toDTO(updatedBook);
    }

    @Override
    public BookDTO detachBookAuthorsAndPublisher(long bookId, BookPAs bookPAs) {
        Book book = validateBookId(bookId);
        if(bookPAs.getAuthorsId() != null && bookPAs.getAuthorsId().size() > 0) {
            List<Long> authorsId = bookPAs.getAuthorsId();
            List<Author> authors = book.getAuthors();
            for (Long id : authorsId) {
                Author author = validateAuthorId(id);
                if (authors.indexOf(author) != -1) {
                    authors.remove(author);
                }
            }
            book.setAuthors(authors);
        }
        if(bookPAs.getPublisherId() != null) {
            Publisher publisher = validatePublisherId(bookPAs.getPublisherId());
            if (publisher.equals(book.getPublisher())) {
                book.setPublisher(null);
            }
        }
        Book updatedBook = bookRepository.save(book);
        return bookMapper.toDTO(updatedBook);
    }

    private Book validateBookId(long id) {
        Optional<Book> book = bookRepository.findById(id);
        if(!book.isPresent()) throw new HttpNotFoundException("Book ID " + id + " not found.");
        return book.get();
    }

    private Author validateAuthorId(long id) {
        Optional<Author> author = authorRepository.findById(id);
        if(!author.isPresent()) throw new HttpNotFoundException("Author ID " + id + " not found.");
        return author.get();
    }

    private Publisher validatePublisherId(long id) {
        Optional<Publisher> publisher = publisherRepository.findById(id);
        if(!publisher.isPresent()) throw new HttpNotFoundException("Publisher ID " + id + " not found.");
        return publisher.get();
    }
}
