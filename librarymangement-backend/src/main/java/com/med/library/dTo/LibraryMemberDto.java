package com.med.library.dTo;

import lombok.Data;

import java.util.Date;

@Data
public class LibraryMemberDto {
    private long id;
    private String lastname;
    private String firstname;
    private Date birthday;
    private String cin;
    private String mail;
    private int maxBorrowing;
    private boolean enabled;
}
