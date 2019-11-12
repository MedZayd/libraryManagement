package com.med.library.restController;

import com.med.library.dTo.BookDTO;
import com.med.library.entity.Book;
import com.med.library.restExceptionHandler.exception.HttpNotFoundException;
import com.med.library.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookRestController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<BookDTO> getAll() {
        return bookService.getAll();
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable("bookId") long bookId) {
        BookDTO bookDTO = bookService.findById(bookId);
        return  new ResponseEntity<>(bookDTO, HttpStatus.OK);
    }

    @PostMapping
    public Book saveBook(@RequestBody Book book) {
        book.setId(0);
        return bookService.save(book);
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<Book> updateBook(@PathVariable("bookId") long bookId  , @RequestBody Book book) {
        bookService.findById(bookId);
        book.setId(bookId);
        Book updatedBook = bookService.save(book);
        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<?> deleteBook(@PathVariable("bookId") long bookId) {
        bookService.findById(bookId);
        bookService.deleteById(bookId);
        return new ResponseEntity<>("Book deleted successfully !", HttpStatus.OK);
    }
}