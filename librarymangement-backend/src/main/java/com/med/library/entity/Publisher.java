package com.med.library.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Publisher company name is mandatory")
    private String name;

    @OneToMany(mappedBy = "publisher")
    private List<Book> books;

    public Publisher(@NotNull(message = "Publisher company name is mandatory") String name) {
        this.name = name;
    }

    public void addBook(Book book) {
        this.books.add(book);
    }
}
