package com.med.library.service;

import com.med.library.dTo.AuthorDTO;
import com.med.library.dTo.BookDTO;
import com.med.library.entity.Author;
import com.med.library.entity.Book;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<AuthorDTO> findAllEnabled();
    AuthorDTO findById(long authorId);
    AuthorDTO save(AuthorDTO authorDTO);
    AuthorDTO update(AuthorDTO authorDTO, long authorId);
    AuthorDTO enable(boolean enable, long authorId);
    void delete(long authorId);
}
