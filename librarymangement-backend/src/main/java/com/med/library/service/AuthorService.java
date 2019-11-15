package com.med.library.service;

import com.med.library.dTo.AuthorDTO;
import com.med.library.dTo.BookDTO;
import com.med.library.entity.Author;
import com.med.library.entity.Book;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    public List<AuthorDTO> getAll();
    public AuthorDTO getById(Long authorId);
    public AuthorDTO findByName(String name);
    public AuthorDTO save(AuthorDTO authorDTO);
    public void deleteById(Long authorId);
}
