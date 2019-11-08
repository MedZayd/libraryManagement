package com.med.library.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Borrow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date borrowDate;

    @NotNull(message = "Total of borrowing days is mandatory")
    private int totalDays;

    @NotNull(message = "Total of borrowing days is mandatory")
    private int copies;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public Borrow(Book book, Member member, Date borrowDate, @NotNull(message = "Total of borrowing days is mandatory") int totalDays) {
        this.borrowDate = borrowDate;
        this.totalDays = totalDays;
        this.book = book;
        this.member = member;
    }
}
