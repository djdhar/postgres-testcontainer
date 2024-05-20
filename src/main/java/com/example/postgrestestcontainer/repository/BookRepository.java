package com.example.postgrestestcontainer.repository;

import com.example.postgrestestcontainer.model.Book;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends R2dbcRepository<Book, Long> {
}
