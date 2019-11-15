package com.med.library.restController;

import com.med.library.dTo.BookDTO;
import com.med.library.service.BookService;
import com.med.library.utils.MappingConsts;
import com.med.library.utils.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.med.library.utils.MappingConsts.*;

@RestController
@RequestMapping(MappingConsts.BOOKS)
public class BookRestController {

    private BookService bookService;

    @Autowired
    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    // Get Mapping
    @GetMapping
    public ResponseEntity<List<BookDTO>> getAll() {
        List<BookDTO> bookDTOS = bookService.getAll();
        return new ResponseEntity<>(bookDTOS, HttpStatus.OK);
    }

    @GetMapping(BOOK)
    public ResponseEntity<BookDTO> getBookById(@PathVariable("bookId") long bookId) {
        BookDTO bookDTO = bookService.findById(bookId);
        return  new ResponseEntity<>(bookDTO, HttpStatus.OK);
    }

    // POST MAPPING
    @PostMapping
    public ResponseEntity<?> saveBook(
            @Valid @RequestBody BookDTO bookDTO,
            BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return ValidationError.getErrors(bindingResult.getAllErrors());
        }
        bookDTO.setId(0);
        return new ResponseEntity<>(bookService.save(bookDTO), HttpStatus.OK);
    }

    @PostMapping(BOOK_AUTHOR)
    public ResponseEntity<BookDTO> addAuthorToBook(
            @PathVariable("bookId") long bookId,
            @PathVariable("authorId") long authorId) {
        BookDTO bookDTO = bookService.addAuthor(bookId, authorId);
        return new ResponseEntity<>(bookDTO, HttpStatus.OK);
    }

    @PostMapping(BOOK_PUBLISHER)
    public ResponseEntity<BookDTO> setBookPublisher(
            @PathVariable("bookId") long bookId,
            @PathVariable("publisherId") long publisherId) {
        BookDTO bookDTO = bookService.setBookPublisher(bookId, publisherId);
        return new ResponseEntity<>(bookDTO, HttpStatus.OK);
    }

    // PUT MAPPING
    @PutMapping(BOOK)
    public ResponseEntity<?> updateBook(
            @PathVariable("bookId") long bookId,
            @Valid @RequestBody BookDTO bookDTO,
            BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return ValidationError.getErrors(bindingResult.getAllErrors());
        }
        bookService.findById(bookId);
        bookDTO.setId(bookId);
        BookDTO updatedBook = bookService.save(bookDTO);
        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }

    // DELETE MAPPING
    @DeleteMapping(BOOK)
    public ResponseEntity<String> deleteBook(@PathVariable("bookId") long bookId) {
        bookService.findById(bookId);
        bookService.deleteById(bookId);
        return new ResponseEntity<>("Book deleted successfully !", HttpStatus.OK);
    }

    @DeleteMapping(BOOK_AUTHOR)
    public ResponseEntity<BookDTO> detachAuthor(
            @PathVariable("bookId") long bookId,
            @PathVariable("authorId") long authorId
    ) {
        BookDTO bookDTO = bookService.detachAuthor(bookId, authorId);
        return new ResponseEntity<>(bookDTO, HttpStatus.OK);
    }

    @DeleteMapping(BOOK_PUBLISHER)
    public ResponseEntity<BookDTO> detachPublisher(
            @PathVariable("bookId") long bookId,
            @PathVariable("publisherId") long publisherId
    ) {
        BookDTO bookDTO = bookService.detachPublisher(bookId, publisherId);
        return new ResponseEntity<>(bookDTO, HttpStatus.OK);
    }
}