package com.med.library.dTo;

import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AuthorDTO {

    private long id;

    @NotNull(message = "Author name is mandatory")
    private String name;

    private boolean enabled = true;
}
