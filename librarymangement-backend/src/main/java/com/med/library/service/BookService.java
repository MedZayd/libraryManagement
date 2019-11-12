package com.med.library.service;

import com.med.library.dTo.BookDTO;
import com.med.library.entity.Book;
import com.med.library.entity.Borrow;

import java.util.List;

public interface BookService {
    public List<BookDTO> getAll();
    public BookDTO findById(Long bookId);
    public BookDTO save(BookDTO bookDTO);
    public void deleteById(Long bookId);
    public void addBorrow(Book book, Borrow borrow);
}
