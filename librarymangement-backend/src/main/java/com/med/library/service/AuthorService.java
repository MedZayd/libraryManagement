package com.med.library.service;

import com.med.library.dTo.AuthorDTO;

import java.util.List;

public interface AuthorService {
    List<AuthorDTO> findAllEnabled();
    AuthorDTO findById(long authorId);
    AuthorDTO save(AuthorDTO authorDTO);
    AuthorDTO update(AuthorDTO authorDTO, long authorId);
    AuthorDTO enable(boolean enable, long authorId);
    void delete(long authorId);
}
