package com.med.library.restController;

import com.med.library.entity.Book;
import com.med.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookRestController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAll() {
        return bookService.getAll();
    }

    @GetMapping("/{bookId}")
    public Book getBookById(@PathVariable("bookId") long bookId) {
        return bookService.findById(bookId).orElse(null);
    }

    @PostMapping
    public Book saveBook(@RequestBody Book book) {
        book.setId(0L);
        return bookService.save(book);
    }

    @PutMapping("/{bookId}")
    public Book updateBook(@PathVariable("bookId") long bookId  , @RequestBody Book book) {
        book.setId(bookId);
        return bookService.save(book);
    }

    @DeleteMapping("/{bookId}")
    public String deleteBook(@PathVariable("{bookId}") long bookId) {
        bookService.deleteById(bookId);
        return "Book deleted !";
    }
}