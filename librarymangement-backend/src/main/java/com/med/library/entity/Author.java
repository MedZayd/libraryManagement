package com.med.library.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "Author name is mandatory")
    @Column(unique = true, length = 30)
    private String name;

    @Column(columnDefinition = "boolean default true")
    private boolean enabled = true;

    public Author(@NotNull(message = "Author name is mandatory") String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
