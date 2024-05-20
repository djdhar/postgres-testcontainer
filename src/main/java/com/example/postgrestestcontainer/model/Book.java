package com.example.postgrestestcontainer.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@Table(name = "book")
@JsonInclude(NON_NULL)
public class Book {

    @Id
    @Column("id")
    Long id;

    @Column("book_name")
    String bookName;

    @Column("book_description")
    String bookDescription;
}
