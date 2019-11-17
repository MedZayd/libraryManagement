package com.med.library.service;

import com.med.library.dTo.BookDTO;

import java.util.List;

public interface BookService {
    List<BookDTO> getAll();
    BookDTO findById(Long bookId);
    BookDTO save(BookDTO bookDTO);
    BookDTO addAuthor(long bookId, long authorId);
    BookDTO setBookPublisher(long bookId, long publisherId);
    BookDTO detachAuthor(long bookId, long authorId);
    BookDTO detachPublisher(long bookId, long publisherId);
    void deleteById(Long bookId);
}
