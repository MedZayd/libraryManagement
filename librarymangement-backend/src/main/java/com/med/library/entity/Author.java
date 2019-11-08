package com.med.library.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Author name is mandatory")
    private String name;

    @OneToMany(mappedBy = "author")
    private List<Book> books;

    public Author(@NotNull(message = "Author name is mandatory") String name) {
        this.name = name;
    }

    public void addBook(Book book) {
        this.books.add(book);
    }
}
