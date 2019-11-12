package com.med.library.dTo;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class BookDTO {
    private long id;

    @NotNull(message = "Book title is mandatory")
    private String title;

    @NotNull(message = "Publication year is mandatory")
    private String year;

    @NotNull(message = "Number of copies is mandatory")
    private int copies;

    @NotNull(message = "Rent price per day is mandatory")
    private double rentPricePerDay;

    private int pages;
    private String language;
}
