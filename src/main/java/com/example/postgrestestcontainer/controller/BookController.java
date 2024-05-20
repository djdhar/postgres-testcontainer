package com.example.postgrestestcontainer.controller;

import com.example.postgrestestcontainer.dto.BookCreateRequest;
import com.example.postgrestestcontainer.dto.BookUpdateRequest;
import com.example.postgrestestcontainer.model.Book;
import com.example.postgrestestcontainer.service.BookService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class BookController {

    BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/v1/book/{id}")
    public Mono<Book> getBook(@PathVariable Long id) {
        return bookService.getBook(id);
    }

    @PostMapping("/v1/book")
    public Mono<Book> addBook(@RequestBody @Valid BookCreateRequest bookCreateRequest) {
        return bookService.addBook(bookCreateRequest);
    }

    @PutMapping("/v1/book/{id}")
    public Mono<Book> updateBook(@RequestBody BookUpdateRequest bookUpdateRequest, @PathVariable Long id) {
        return bookService.updateBook(bookUpdateRequest, id);
    }

    @DeleteMapping("/v1/book/{id}")
    public Mono<Book> deleteBook(@PathVariable Long id) {
        return bookService.deleteBook(id);
    }

    @GetMapping("/v1/books")
    public Flux<Book> getBooks() {
        return bookService.getBooks();
    }

}
