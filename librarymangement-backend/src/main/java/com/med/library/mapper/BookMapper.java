package com.med.library.mapper;

import com.med.library.dTo.BookDTO;
import com.med.library.entity.Book;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface BookMapper {

    BookDTO bookToBookDTO(Book book);

    Book bookDTOToBook(BookDTO bookDTO);

    @IterableMapping(elementTargetType = BookDTO.class)
    List<BookDTO> booksToBookDTOs(List<Book> books);

    @IterableMapping(elementTargetType = Book.class)
    List<Book> bookDTOsToBooks(List<BookDTO> bookDTOS);
}
