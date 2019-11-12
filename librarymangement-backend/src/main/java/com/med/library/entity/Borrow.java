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
public class Borrow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Date borrowDate;

    @NotNull(message = "Total of borrowing days is mandatory")
    private int totalDays;

    @NotNull(message = "Total of borrowing days is mandatory")
    private int copies;

    @ManyToMany
    @JoinTable(
            name = "borrow_book",
            joinColumns = @JoinColumn(name = "borrow_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private List<Book> books;

    @ManyToOne
    @JoinColumn(name = "library_member_id")
    private LibraryMember libraryMember;

    public Borrow(Date borrowDate, @NotNull(message = "Total of borrowing days is mandatory") int totalDays, @NotNull(message = "Total of borrowing days is mandatory") int copies, List<Book> books, LibraryMember libraryMember) {
        this.borrowDate = borrowDate;
        this.totalDays = totalDays;
        this.copies = copies;
        this.books = books;
        this.libraryMember = libraryMember;
    }

    public void addBook(Book book) {
        this.books.add(book);
    }
}
