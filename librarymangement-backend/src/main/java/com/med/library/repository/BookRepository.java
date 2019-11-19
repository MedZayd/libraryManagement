package com.med.library.repository;

import com.med.library.entity.Author;
import com.med.library.entity.Book;
import com.med.library.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query(
        value = "Select * from book where id in ( select book_id from book_author where author_id = ?1 )",
        nativeQuery = true
    )
    List<Book> findByAuthor(long authorId);
    List<Book> findByPublisher(Publisher publisher);
}
