package com.company.books.backend.services;

import org.springframework.http.ResponseEntity;

import com.company.books.backend.dto.BookDto;
import com.company.books.backend.response.BookResponseRest;

public interface IBookService {

	public ResponseEntity<BookResponseRest> searchBooks();
	public ResponseEntity<BookResponseRest> saveBook(BookDto bookDto);
	public ResponseEntity<BookResponseRest> updateBook(BookDto bookDto, Long id);
	public ResponseEntity<BookResponseRest> searchBookById(Long id);
	public ResponseEntity<BookResponseRest> deleteBook(Long id);
}
