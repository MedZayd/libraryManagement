package com.med.library.dTo;

import javax.validation.constraints.NotNull;

public class AuthorDTO {

    private long id;

    @NotNull(message = "Author name is mandatory")
    private String name;
}
