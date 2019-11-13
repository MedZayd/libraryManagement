package com.med.library.mapper;

import com.med.library.dTo.BookDTO;
import com.med.library.entity.Author;
import com.med.library.entity.Book;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(uses = {AuthorMapper.class} )
public interface BookMapper {

    @Mapping(source = "authors", target = "authorDTOs")
    BookDTO toDTO(Book book);

    Book toEntity(BookDTO bookDTO);

    @IterableMapping(qualifiedByName = "toDTO")
    List<BookDTO> toDTOs(List<Book> books);

}
