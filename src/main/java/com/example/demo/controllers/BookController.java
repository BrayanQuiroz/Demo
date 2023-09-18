package com.example.demo.controllers;

import com.example.demo.entities.Book;
import com.example.demo.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
public class BookController {

    private final Logger log = LoggerFactory.getLogger(BookController.class);
    private BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/api/books")
    public List<Book> findAll(){
        return  bookRepository.findAll();
    }

    @GetMapping("/api/books/{id}")
    public ResponseEntity<Book> findOneById(@PathVariable Long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);

        return bookOptional
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @PostMapping("/api/books")
    public ResponseEntity<Book> create(@RequestBody Book book, @RequestHeader HttpHeaders headers) {
        if(book.getId() != null){
            log.warn("Trying create a book with id");
            return ResponseEntity.badRequest().build();
        }
        Book result = bookRepository.save(book);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/api/books/")
    public  ResponseEntity<Book>  update(@RequestBody Book book){

        if(book.getId() != null){
            log.warn("Trying create a book with id");
            return ResponseEntity.badRequest().build();
        }

        if(!bookRepository.existsById(book.getId())){
            log.warn("Trying create a book with id");
            return ResponseEntity.notFound().build();
        }

        Book result = bookRepository.save(book);
        return ResponseEntity.ok(result);
    }
}

