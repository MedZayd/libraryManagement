package com.med.library.dTo;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PublisherDTO {

    private long id;

    @NotNull(message = "Publisher name is mandatory")
    private String name;
}
