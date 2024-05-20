package com.example.postgrestestcontainer.dto;

import com.example.postgrestestcontainer.model.Book;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookCreateRequest {

    @NotBlank(message = "bookName cannot be blank!")
    String bookName;
    @NotBlank(message = "bookDescription cannot be blank!")
    String bookDescription;

    public static Book bookCreateRequest(BookCreateRequest bookCreateRequest) {
        Book book = new Book();
        book.setBookDescription(bookCreateRequest.getBookDescription());
        book.setBookName(bookCreateRequest.getBookName());
        return book;
    }
}
