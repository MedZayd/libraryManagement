package com.med.library;

import com.med.library.entity.Author;
import com.med.library.entity.Book;
import com.med.library.entity.Publisher;
import com.med.library.service.AuthorService;
import com.med.library.service.BookService;
import com.med.library.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class LibrarymangementApplication implements CommandLineRunner {

	private BookService bookService;
	private AuthorService authorService;
	private PublisherService publisherService;

	@Autowired
	public LibrarymangementApplication(BookService bookService, AuthorService authorService, PublisherService publisherService) {
		this.bookService = bookService;
		this.authorService = authorService;
		this.publisherService = publisherService;
	}

	public static void main(String[] args) {
		SpringApplication.run(LibrarymangementApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
/*
		Publisher manning = new Publisher("Manning");
		Publisher apress = new Publisher("Apress");

		Author craig_walls = new Author("Craig Walls");
		Author felipe_gutierrez = new Author("Felipe Gutierrez");
		Author antonov_a = new Author("Antonov A.");


		List<Author> authors = new ArrayList<>();
		authors.add(craig_walls);
		Book b1 = new Book("Spring Boot in Action", "2015", 10, 5, 264, "English", authors, manning);

		authors = new ArrayList<>();
		authors.add(felipe_gutierrez);
		Book b2 = new Book("Pro Spring Boot", "2016", 10, 5, 373, "English", authors, apress);

		authors = new ArrayList<>();
		authors.add(antonov_a);
		Book b3 = new Book("Spring boot cookbook", "2016", 10, 5, 296, "English", authors, apress);


		publisherService.save(manning);
		publisherService.save(apress);
		authorService.save(craig_walls);

		authorService.save(felipe_gutierrez);
		authorService.save(antonov_a);

		bookService.save(b1);
		bookService.save(b2);
		bookService.save(b3);

		System.out.println(">>>>>>>>>>>>>> Persisted Authors:");
		authors = authorService.getAll();
		for(Author author : authors) {
			System.out.println(author.toString());
		}

		System.out.println(">>>>>>>>>>>>>> Persisted Books:");
		List<Book> books = bookService.getAll();
		for(Book b: books) {
			System.out.println(b.toString());
		}*/
	}
}