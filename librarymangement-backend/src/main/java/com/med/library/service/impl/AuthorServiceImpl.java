package com.med.library.service.impl;

import com.med.library.dTo.AuthorDTO;
import com.med.library.entity.Author;
import com.med.library.entity.Book;
import com.med.library.mapper.AuthorMapper;
import com.med.library.repository.AuthorRepository;
import com.med.library.service.AuthorService;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorMapper authorMapper = Mappers.getMapper(AuthorMapper.class);

    private AuthorRepository authorRepository;
    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<AuthorDTO> getAll() {
        return authorMapper.toDtos(authorRepository.findAll());
    }

    @Override
    public AuthorDTO getById(Long authorId) {
        Optional<Author> authorDb = authorRepository.findById(authorId);
        if (!authorDb.isPresent()) {
            throw new RuntimeException("Author with ID " + authorId + " not found !");
        }
        return authorMapper.toDto(authorDb.get());
    }

    @Override
    public AuthorDTO findByName(String name) {
        Optional<Author> authorDb = authorRepository.findByName(name);
        if (!authorDb.isPresent()) {
            throw new RuntimeException("Author " + name + " not found !");
        }
        return authorMapper.toDto(authorDb.get());
    }

    @Override
    public AuthorDTO save(AuthorDTO authorDTO) {
        Optional<Author> authorDb = authorRepository.findByName(authorDTO.getName().trim());
        if (authorDb.isPresent()) {
            throw new RuntimeException(authorDTO.getName() + " already exist in database.");
        }
        Author persistedAuthor = authorMapper.toEntity(authorDTO);
        return authorMapper.toDto(persistedAuthor);
    }

    @Override
    public void deleteById(Long authorId) {
        Optional<Author> authorDb = authorRepository.findById(authorId);
        if (authorDb.isPresent()) {
            throw new RuntimeException(" Author not found.");
        }
        authorRepository.deleteById(authorId);
    }
}
