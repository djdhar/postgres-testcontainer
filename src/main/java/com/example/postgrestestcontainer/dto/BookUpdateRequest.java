package com.example.postgrestestcontainer.dto;

import com.example.postgrestestcontainer.model.Book;
import io.netty.util.internal.StringUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookUpdateRequest {

    String bookName;
    String bookDescription;

    public static Book bookUpdateRequest(BookUpdateRequest bookUpdateRequest, Book book) {
        if(Objects.isNull(bookUpdateRequest) || Objects.isNull(book)) return null;
        else {
            if(!StringUtil.isNullOrEmpty(bookUpdateRequest.getBookName())) book.setBookName(bookUpdateRequest.getBookName());
            if(!StringUtil.isNullOrEmpty(bookUpdateRequest.getBookDescription()))
                book.setBookDescription(bookUpdateRequest.getBookDescription());
        }
        return book;
    }
}
