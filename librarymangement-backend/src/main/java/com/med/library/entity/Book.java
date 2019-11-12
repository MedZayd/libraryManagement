package com.med.library.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@JsonIgnoreProperties("borrows")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "Book title is mandatory")
    private String title;

    @NotNull(message = "Publication date is mandatory")
    private String year;

    @NotNull(message = "Number of copies is mandatory")
    private int copies;

    @NotNull(message = "Rent price per day is mandatory")
    private double rentPricePerDay;

    private int pages;

    private String language;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    @JsonIgnoreProperties("books")
    private List<Author> authors;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "publisher_id")
    @JsonIgnoreProperties("books")
    private Publisher publisher;

    @ManyToMany(mappedBy = "books")
    private List<Borrow> borrows;

    public Book(@NotNull(message = "Book title is mandatory") String title,
                @NotNull(message = "Publication date is mandatory") String year,
                @NotNull(message = "Number of copies is mandatory") int copies,
                @NotNull(message = "Rent price per day is mandatory") double rentPricePerDay,
                int pages, String language, List<Author> authors, Publisher publisher) {
        this.title = title;
        this.year = year;
        this.copies = copies;
        this.rentPricePerDay = rentPricePerDay;
        this.pages = pages;
        this.language = language;
        this.authors = authors;
        this.publisher = publisher;
    }

    public void addAuthor(Author author) {
        this.authors.add(author);
    }

    public void addBorrow(Borrow borrow) {
        this.borrows.add(borrow);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", year='" + year + '\'' +
                ", copies=" + copies +
                ", rentPricePerDay=" + rentPricePerDay +
                ", pages=" + pages +
                ", language='" + language + '\'' +
                ", authors=" + authors +
                ", publisher=" + publisher +
                '}';
    }
}
