package com.company.books.backend.response;

import java.util.List;

import com.company.books.backend.model.Book;

import lombok.Data;

@Data
public class BookResponse {

	private List<Book> bookList;
}
