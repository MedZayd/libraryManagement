package com.med.library.repository;

import com.med.library.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByName(String name);
    List<Author> findByEnabledTrue();
    boolean existsByName(String name);
}
