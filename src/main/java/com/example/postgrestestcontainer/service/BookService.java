package com.example.postgrestestcontainer.service;

import com.example.postgrestestcontainer.dto.BookCreateRequest;
import com.example.postgrestestcontainer.dto.BookUpdateRequest;
import com.example.postgrestestcontainer.model.Book;
import com.example.postgrestestcontainer.repository.BookRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BookService {

    BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    public Mono<Book> getBook(Long id) {
        return bookRepository.findById(id)
                .switchIfEmpty(Mono.defer(()-> Mono.just(new Book())))
                .onErrorResume(err -> Mono.just(new Book()));
    }

    public Mono<Book> addBook(BookCreateRequest bookCreateRequest) {
        Book book = BookCreateRequest.bookCreateRequest(bookCreateRequest);
        return bookRepository.save(book);
    }

    public Mono<Book> updateBook(BookUpdateRequest bookUpdateRequest, Long id) {
        return bookRepository.findById(id).flatMap(bookObtained -> {
            Book book = BookUpdateRequest.bookUpdateRequest(bookUpdateRequest, bookObtained);
            return bookRepository.save(book);
        }).switchIfEmpty(Mono.defer(()-> Mono.just(new Book())))
        .onErrorResume(err -> Mono.just(new Book()));
    }

    public Mono<Book> deleteBook(Long id) {
        return bookRepository.findById(id).flatMap(book ->
             bookRepository.deleteById(id).then(Mono.just(book))
        ).switchIfEmpty(Mono.defer(()-> Mono.just(new Book())))
        .onErrorResume(err -> Mono.just(new Book()));
    }

    public Flux<Book> getBooks() {
        return bookRepository.findAll();
    }
}
