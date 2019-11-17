package com.med.library.restController;

import com.med.library.dTo.AuthorDTO;
import com.med.library.service.AuthorService;
import com.med.library.utils.MappingConsts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.med.library.utils.ValidationError.getErrors;

@RestController
@RequestMapping(MappingConsts.AUTHORS)
public class AuthorRestController {

    private AuthorService authorService;

    @Autowired
    public AuthorRestController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity<List<AuthorDTO>> getAllEnabled() {
        List<AuthorDTO> authorDTOS = authorService.findAllEnabled();
        return new ResponseEntity<>(authorDTOS, HttpStatus.OK);
    }

    @GetMapping(MappingConsts.AUTHOR)
    public ResponseEntity<AuthorDTO> getAuthorById(@PathVariable("authorId") long authorId) {
        AuthorDTO authorDTO = authorService.findById(authorId);
        return new ResponseEntity<>(authorDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(
            @Valid @RequestBody AuthorDTO authorDTO,
            BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return getErrors(bindingResult.getAllErrors());
        AuthorDTO persistedAuthorDto = authorService.save(authorDTO);
        return new ResponseEntity<>(persistedAuthorDto, HttpStatus.OK);
    }

    @PostMapping(MappingConsts.AUTHOR)
    public ResponseEntity<String> archive(
            @PathVariable("authorId") long authorId,
            @RequestParam("enable") boolean enable) {
        AuthorDTO updatedAuthorDto = authorService.enable(enable, authorId);
        return updatedAuthorDto.isEnabled() ?
                new ResponseEntity<>("Author is enabled.", HttpStatus.OK)
                : new ResponseEntity<>("Author is archived.", HttpStatus.OK);
    }

    @PutMapping(MappingConsts.AUTHOR)
    public ResponseEntity<?> update(
            @PathVariable("authorId") long authorId,
            @Valid @RequestBody AuthorDTO authorDTO,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return getErrors(bindingResult.getAllErrors());
        AuthorDTO updatedAuthorDto = authorService.update(authorDTO, authorId);
        return new ResponseEntity<>(updatedAuthorDto, HttpStatus.OK);
    }

    @DeleteMapping(MappingConsts.AUTHOR)
    public ResponseEntity<String> delete(@PathVariable("authorId") long authorId) {
        authorService.delete(authorId);
        return new ResponseEntity<>("Author deleted.", HttpStatus.OK);
    }
}
