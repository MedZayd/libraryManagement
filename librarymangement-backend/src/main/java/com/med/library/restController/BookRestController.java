package com.med.library.restController;

import com.med.library.dTo.BookDTO;
import com.med.library.entity.Book;
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
    public ResponseEntity<List<BookDTO>> getAll() {
        return new ResponseEntity<>(bookService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable("bookId") long bookId) {
        BookDTO bookDTO = bookService.findById(bookId);
        return  new ResponseEntity<>(bookDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BookDTO> saveBook(@RequestBody BookDTO bookDTO) {
        bookDTO.setId(0);
        return new ResponseEntity<>(bookService.save(bookDTO), HttpStatus.OK);
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable("bookId") long bookId  , @RequestBody BookDTO bookDTO) {
        bookService.findById(bookId);
        bookDTO.setId(bookId);
        BookDTO updatedBook = bookService.save(bookDTO);
        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<?> deleteBook(@PathVariable("bookId") long bookId) {
        bookService.findById(bookId);
        bookService.deleteById(bookId);
        return new ResponseEntity<>("Book deleted successfully !", HttpStatus.OK);
    }

}