package com.med.library.service.impl;

import com.med.library.dTo.AuthorDTO;
import com.med.library.entity.Author;
import com.med.library.entity.Book;
import com.med.library.mapper.AuthorMapper;
import com.med.library.repository.AuthorRepository;
import com.med.library.restExceptionHandler.exception.HttpNotFoundException;
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
    public List<AuthorDTO> findAllEnabled() {
        List<Author> authors = authorRepository.findByEnabledTrue();
        return authorMapper.toDtos(authors);
    }

    @Override
    public AuthorDTO findById(long authorId) {
        Author author = validateId(authorId);
        return authorMapper.toDto(author);
    }

    @Override
    public AuthorDTO save(AuthorDTO authorDTO) {
        String authorName = authorDTO.getName().trim();
        validateName(authorName);
        authorDTO.setName(authorName);
        Author persistedAuthor = authorRepository.save(authorMapper.toEntity(authorDTO));
        return authorMapper.toDto(persistedAuthor);
    }

    @Override
    public AuthorDTO update(AuthorDTO authorDTO, long authorId) {
        Author author = validateId(authorId);
        String authorName = authorDTO.getName().trim();
        validateName(authorName);
        author.setName(authorName);
        Author updatedAuthor = authorRepository.save(author);
        return authorMapper.toDto(updatedAuthor);
    }

    @Override
    public AuthorDTO enable(boolean enable, long authorId) {
        Author author = validateId(authorId);
        author.setEnabled(enable);
        authorRepository.save(author);
        return authorMapper.toDto(author);
    }

    @Override
    public void delete(long authorId) {
        Author author = validateId(authorId);
        authorRepository.delete(author);
    }

    private Author validateId(long authorId) {
        Optional<Author> author = authorRepository.findById(authorId);
        if(!author.isPresent()) throw new HttpNotFoundException("Author with ID " + authorId + " not found !");
        return author.get();
    }

    private void validateName(String name) {
        Optional<Author> authorDb = authorRepository.findByName(name);
        if (authorDb.isPresent()) throw new RuntimeException("Author " + name + " already exist in database.");
    }
}
