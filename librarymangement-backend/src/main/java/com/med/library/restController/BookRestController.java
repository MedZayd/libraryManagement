package com.med.library.restController;

import com.med.library.dTo.AuthorDTO;
import com.med.library.dTo.BookDTO;
import com.med.library.dTo.PublisherDTO;
import com.med.library.restExceptionHandler.HttpErrorResponse;
import com.med.library.service.AuthorService;
import com.med.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/books")
public class BookRestController {


    private BookService bookService;
    private AuthorService authorService;

    @Autowired
    public BookRestController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAll() {
        List<BookDTO> bookDTOS = bookService.getAll();
        return new ResponseEntity<>(bookDTOS, HttpStatus.OK);
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable("bookId") long bookId) {
        BookDTO bookDTO = bookService.findById(bookId);
        return  new ResponseEntity<>(bookDTO, HttpStatus.OK);
    }

    @GetMapping("/{bookId}/authors")
    public ResponseEntity<List<AuthorDTO>> getBookAuthors(@PathVariable("bookId") long bookId) {
        BookDTO bookDTO = bookService.findById(bookId);
        return  new ResponseEntity<>(bookDTO.getAuthorDTOs(), HttpStatus.OK);
    }

    @GetMapping("/{bookId}/publisher")
    public ResponseEntity<PublisherDTO> getBookPublisher(@PathVariable("bookId") long bookId) {
        BookDTO bookDTO = bookService.findById(bookId);
        return  new ResponseEntity<>(bookDTO.getPublisherDTO(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveBook(@Valid @RequestBody BookDTO bookDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return getErrors(bindingResult.getAllErrors());
        }
        bookDTO.setId(0);
        return new ResponseEntity<>(bookService.save(bookDTO), HttpStatus.OK);
    }

    // FIXME ...
    @PostMapping("/{bookId}/authors")
    public ResponseEntity<?> addBookAuthor(@PathVariable("bookId") long bookId, @Valid @RequestBody AuthorDTO authorDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return getErrors(bindingResult.getAllErrors());
        }
        BookDTO bookDTO = bookService.addAuthor(bookId, authorDTO);
        return new ResponseEntity<>(bookDTO, HttpStatus.OK);
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

    private ResponseEntity<HttpErrorResponse<Map<String, String>>> getErrors(List<ObjectError> allErrors) {
        Map<String, String> errors = new HashMap<>();
        String field, message;
        for (ObjectError objectError : allErrors) {
            field = ((FieldError) objectError).getField();
            message = objectError.getDefaultMessage();
            errors.put(field, message);
        }
        HttpErrorResponse<Map<String, String>> httpErrorResponse =
                new HttpErrorResponse<>(HttpStatus.BAD_REQUEST.value(), "Validation Errors", new Date(), errors);
        return new ResponseEntity<>(httpErrorResponse, HttpStatus.BAD_REQUEST);
    }

}