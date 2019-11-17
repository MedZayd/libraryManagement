package com.med.library.service;

import com.med.library.dTo.BookDTO;
import com.med.library.dTo.BookPAs;

import java.util.List;

public interface BookService {
    List<BookDTO> getAll();
    BookDTO findById(Long bookId);
    BookDTO save(BookDTO bookDTO);
    BookDTO setBookAuthorsAndPublisher(long bookId, BookPAs bookPAs);
    BookDTO detachBookAuthorsAndPublisher(long bookId, BookPAs bookPAs);
    void deleteById(Long bookId);
}
