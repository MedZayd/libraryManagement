package com.med.library.service;

import com.med.library.entity.Book;
import com.med.library.entity.Borrow;

import java.util.List;

public interface BookService {
    public List<Book> getAll();
    public Book findById(Long bookId);
    public Book save(Book book);
    public void deleteById(Long bookId);
    public void addBorrow(Book book, Borrow borrow);
}
