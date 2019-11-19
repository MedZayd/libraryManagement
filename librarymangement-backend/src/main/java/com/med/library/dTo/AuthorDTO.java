package com.med.library.dTo;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AuthorDTO {

    private long id;

    @NotNull(message = "Author name is mandatory")
    private String name;

    private boolean enabled = true;
}
