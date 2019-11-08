package com.med.library.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Book title is mandatory")
    private String title;

    @NotNull(message = "Publication date is mandatory")
    private Date publicationDate;

    @NotNull(message = "Number of copies is mandatory")
    private int copies;

    @NotNull(message = "Rent price per day is mandatory")
    private double rentPricePerDay;

    private int pages;

    private String language;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    @OneToMany(mappedBy = "book")
    private List<Borrow> borrows;

    public Book(@NotNull(message = "Book title is mandatory") String title, @NotNull(message = "Publication date is mandatory") Date publicationDate, @NotNull(message = "Number of copies is mandatory") int copies, @NotNull(message = "Rent price per day is mandatory") double rentPricePerDay, int pages, String language, Author author, Publisher publisher) {
        this.title = title;
        this.publicationDate = publicationDate;
        this.copies = copies;
        this.rentPricePerDay = rentPricePerDay;
        this.pages = pages;
        this.language = language;
        this.author = author;
        this.publisher = publisher;
    }

    public void addBorrow(Borrow borrow) {
        this.borrows.add(borrow);
    }
}
