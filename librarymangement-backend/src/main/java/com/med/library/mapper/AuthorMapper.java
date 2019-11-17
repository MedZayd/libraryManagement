package com.med.library.mapper;

import com.med.library.dTo.AuthorDTO;
import com.med.library.entity.Author;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface AuthorMapper {
    AuthorDTO toDto(Author author);
    Author toEntity(AuthorDTO authorDTO);
    @IterableMapping(qualifiedByName = "toDto")
    List<AuthorDTO> toDtos(List<Author> authors);

}
