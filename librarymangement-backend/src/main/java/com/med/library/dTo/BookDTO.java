package com.med.library.dTo;

import lombok.Data;

@Data
public class BookDTO {
    private long id;
    private String title;
    private String year;
    private int copies;
    private double rentPricePerDay;
    private int pages;
    private String language;
}
