package com.company.books.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.company.books.backend.dto.BookDto;
import com.company.books.backend.response.BookResponseRest;
import com.company.books.backend.services.IBookService;

@RestController
@RequestMapping("/rest/books")
public class BooksRestController {

	@Autowired
	private IBookService bookService;

	@GetMapping("/search")
	public ResponseEntity<BookResponseRest> searchBooks() {

		ResponseEntity<BookResponseRest> bookResponseRest = bookService.searchBooks();
		return bookResponseRest;
	}
	

	@GetMapping("/{id}")
	public ResponseEntity<BookResponseRest> getBookById(@PathVariable Long id) {
	    ResponseEntity<BookResponseRest> response = bookService.searchBookById(id);
		return response;
	}

	@PostMapping("/save")
	public ResponseEntity<BookResponseRest> saveBook(@RequestBody BookDto bookdto){	
		ResponseEntity<BookResponseRest> response = bookService.saveBook(bookdto);
		return response;
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<BookResponseRest> updateBook(@RequestBody BookDto bookdto, @PathVariable Long id){
		ResponseEntity<BookResponseRest> response = bookService.updateBook(bookdto,id);
		return response;
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<BookResponseRest> deleteBook( @PathVariable Long id){
		ResponseEntity<BookResponseRest> response = bookService.deleteBook(id);
		return response;
	}
}
