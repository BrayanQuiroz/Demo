package com.example.demo;

import com.example.demo.entities.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(DemoApplication.class, args);
		BookRepository repository = context.getBean(BookRepository.class);

		Book book = new Book(null, "Hola", "EASDASD", 123,123.22, LocalDate.of(2019,12,1),true);

		repository.save(book);

		System.out.println("asdasda" + repository.findAll().size());

	}
}
