package com.med.library.service.impl;

import com.med.library.dTo.AuthorDTO;
import com.med.library.dTo.BookDTO;
import com.med.library.entity.Author;
import com.med.library.entity.Book;
import com.med.library.entity.Borrow;
import com.med.library.mapper.AuthorMapper;
import com.med.library.mapper.BookMapper;
import com.med.library.repository.AuthorRepository;
import com.med.library.repository.BookRepository;
import com.med.library.restExceptionHandler.exception.HttpNotFoundException;
import com.med.library.restExceptionHandler.exception.InvalidIdException;
import com.med.library.service.AuthorService;
import com.med.library.service.BookService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;
    private AuthorRepository authorRepository;

    private BookMapper bookMapper = Mappers.getMapper(BookMapper.class);
    private AuthorMapper authorMapper = Mappers.getMapper(AuthorMapper.class);

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<BookDTO> getAll() {
        List<Book> books = bookRepository.findAll();
        return bookMapper.toDTOs(books);
    }

    @Override
    public BookDTO findById(Long bookId) {
        Book book = bookRepository.findById(bookId).orElse(null);
        if ( book == null ) {
            throw new HttpNotFoundException("ID " + bookId + " not found.");
        }
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
        bookRepository.deleteById(bookId);
    }

    // FIXME ...
    @Override
    public BookDTO addAuthor(long bookId, AuthorDTO authorDTO) {
        if (bookId > 0) {
            if (authorDTO.getId() > 0) {
                Optional<Book> book = bookRepository.findById(bookId);
                if (book.isPresent()) {
                    Optional<Author> author = authorRepository.findById(authorDTO.getId());
                    if (author.isPresent()) {
                        List<Author> bookAuthors = book.get().getAuthors();
                        bookAuthors.add(authorMapper.toEntity(authorDTO));
                        book.get().setAuthors(bookAuthors);
                        Book updatedBook = bookRepository.save(book.get());
                        return bookMapper.toDTO(updatedBook);
                    } else {
                        throw new HttpNotFoundException("Author ID " + authorDTO.getId() + " not found.");
                    }
                } else {
                    throw new HttpNotFoundException("Book ID " + bookId + " not found.");
                }
            } else {
              throw new InvalidIdException("INVALID AUTHOR ID");
            }
        } else {
            throw new InvalidIdException("INVALID BOOK ID");
        }
    }
}
