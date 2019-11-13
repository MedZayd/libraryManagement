package com.med.library.restController;

import com.med.library.dTo.BookDTO;
import com.med.library.restExceptionHandler.HttpErrorResponse;
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

    @Autowired
    private BookService bookService;

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

    @PostMapping
    public ResponseEntity<?> saveBook(@Valid @RequestBody BookDTO bookDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            Map<String, String> errors = new HashMap<>();
            for (ObjectError objectError : bindingResult.getAllErrors()) {
                String field = ((FieldError) objectError).getField();
                String message = objectError.getDefaultMessage();
                errors.put(field, message);
            }
            HttpErrorResponse<Map<String, String>> httpErrorResponse = new HttpErrorResponse<>(HttpStatus.BAD_REQUEST.value(), "Validation Errors", new Date(), errors);
            return new ResponseEntity<>(httpErrorResponse, HttpStatus.BAD_REQUEST);
        }
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