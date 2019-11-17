package com.med.library.dTo;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class BookDTO {
    private long id;

    @NotNull(message = "Book title is mandatory")
    private String title;

    @NotNull(message = "Publication year is mandatory")
    private String year;

    @Min(value = 1, message = "Number of copies is mandatory")
    private int copies;

    @Min(value = 1, message = "Rent price per day is mandatory")
    private double rentPricePerDay;

    private int pages;

    @NotNull(message = "Book language is mandatory")
    private String language;

    private List<AuthorDTO> authorDTOs;

    private PublisherDTO publisherDTO;
}
