package com.med.library.service.impl;

import com.med.library.dTo.BookDTO;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
        Book book = validate(bookId);
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
        Book book = validate(bookId);
        bookRepository.delete(book);
    }

    @Override
    public BookDTO addAuthor(long bookId, long authorId) {
        Map<String, Object> objectMap = validate(bookId, authorId, Author.class);
        Book book = (Book) objectMap.get("book");
        Author author = (Author) objectMap.get("author");
        List<Author> bookAuthors = book.getAuthors();
        bookAuthors.add(author);
        book.setAuthors(bookAuthors);
        Book updatedBook = bookRepository.save(book);
        return bookMapper.toDTO(updatedBook);
    }

    @Override
    public BookDTO setBookPublisher(long bookId, long publisherId) {
        Map<String, Object> objectMap = validate(bookId, publisherId, Publisher.class);
        Book book = (Book) objectMap.get("book");
        Publisher publisher = (Publisher) objectMap.get("publisher");
        book.setPublisher(publisher);
        Book updatedBook = bookRepository.save(book);
        return bookMapper.toDTO(updatedBook);
    }

    @Override
    public BookDTO detachAuthor(long bookId, long authorId) {
        Map<String, Object> objectMap = validate(bookId, authorId, Author.class);
        Book book = (Book) objectMap.get("book");
        Author author = (Author) objectMap.get("author");
        List<Author> authors = book.getAuthors();
        if(authors.indexOf(author) != -1) {
            authors.remove(author);
            book.setAuthors(authors);
            return bookMapper.toDTO(bookRepository.save(book));
        } else throw new InvalidIdException("Author ID " + authorId + " not found in book's authors list.");
    }

    @Override
    public BookDTO detachPublisher(long bookId, long publisherId) {
        Map<String, Object> objectMap = validate(bookId, publisherId, Publisher.class);
        Book book = (Book) objectMap.get("book");
        Publisher publisher = (Publisher) objectMap.get("publisher");
        if ( book.getPublisher().equals(publisher) ) {
            book.setPublisher(null);
            return bookMapper.toDTO(bookRepository.save(book));
        } else throw new InvalidIdException("Publisher ID " + publisherId + " does not match Book's Publisher ID");
    }

    private Book validate(long bookId) {
        Optional<Book> book = bookRepository.findById(bookId);
        if(!book.isPresent()) throw new HttpNotFoundException("Book ID " + bookId + " not found.");
        return book.get();
    }

    private Map<String, Object> validate(long bookId, long id, Class<?> classType) {
        Map<String, Object> objects = new HashMap<>();
        Optional<Book> book = bookRepository.findById(bookId);
        if(!book.isPresent()) throw new HttpNotFoundException("Book ID " + bookId + " not found.");
        objects.put("book", book.get());
        if(classType == Author.class) {
            Optional<Author> author = authorRepository.findById(id);
            if(!author.isPresent()) throw new HttpNotFoundException("Author ID " + id + " not found.");
            objects.put("author", author.get());
        } else if(classType == Publisher.class) {
            Optional<Publisher> publisher = publisherRepository.findById(id);
            if(!publisher.isPresent()) throw new HttpNotFoundException("Publisher ID " + id + " not found.");
            objects.put("publisher", publisher.get());
        }
        return objects;
    }
}
