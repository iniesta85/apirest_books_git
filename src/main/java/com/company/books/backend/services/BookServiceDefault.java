package com.company.books.backend.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.company.books.backend.dao.IBookDao;
import com.company.books.backend.dao.ICategoriaDao;
import com.company.books.backend.dto.BookDto;
import com.company.books.backend.model.Book;
import com.company.books.backend.model.Categoria;
import com.company.books.backend.response.BookResponseRest;

@Service
public class BookServiceDefault implements IBookService {

	private static final Logger log = LoggerFactory.getLogger(CategoryServiceDefault.class);

	@Autowired
	private IBookDao bookdao;
	
	@Autowired
	private ICategoriaDao categoryDao;


	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<BookResponseRest> searchBooks() {

		log.info("Inicio modo searchBooks()");
		BookResponseRest response = new BookResponseRest();

		try {
			List<Book> booksList = (List<Book>) bookdao.findAll();
			response.getBookResponse().setBookList(booksList);
			response.setMetaData("Respuesta ok", "00", "Respuesta exitosa");

		} catch (Exception e) {
			response.setMetaData("Respuesta no", "-1", "Error al consultar libros");
			log.info("Error al consultar libros: ", e.getMessage());
			e.getStackTrace();
			return new ResponseEntity<BookResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<BookResponseRest>(response, HttpStatus.OK);

	}

	@Override
	@Transactional
	public ResponseEntity<BookResponseRest> saveBook(BookDto bookDto) {
		log.info("Inicio modo saveBook()");
		BookResponseRest response = new BookResponseRest();
		try {
			Book book = new Book();
			List<Book> bookList = new ArrayList<Book>();
			Optional<Categoria> category = categoryDao.findById(bookDto.getCategory_id());
			
			book.setName(bookDto.getName());
			book.setDescription(bookDto.getDescription());

			if(category.isPresent()) {
				book.setCategory(category.get());
			}

			Book bookSave = bookdao.save(book);
			if (bookSave != null) {
				bookList.add(bookSave);
				response.getBookResponse().setBookList(bookList);
				response.setMetaData("Respuesta ok", "00", "Respuesta exitosa");
			} else {
				response.setMetaData("Respuesta nok", "-1", "book no guardado correctamente");
				log.info("Error al guardar book");
				return new ResponseEntity<BookResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			response.setMetaData("Respuesta no", "-1", "Error al guardar book");
			log.info("Error al guardar book: ", e.getMessage());
			e.getStackTrace();
			return new ResponseEntity<BookResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<BookResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<BookResponseRest> updateBook(BookDto bookDto, Long id) {

		log.info("Inicio modo updateBook()");
		BookResponseRest response = new BookResponseRest();
		List<Book> bookList = new ArrayList<Book>();

		try {
			Optional<Book> bookSearch = bookdao.findById(id);

			if (bookSearch.isPresent()) {
				bookSearch.get().setName(bookDto.getName());
				bookSearch.get().setDescription(bookDto.getDescription());
				
				Optional<Categoria> category = categoryDao.findById(bookDto.getCategory_id());
				if(category.isPresent()) {
					bookSearch.get().setCategory(category.get());
				}

				Book updatedBook = bookdao.save(bookSearch.get());

				if (updatedBook != null) {
					bookList.add(updatedBook);
					response.getBookResponse().setBookList(bookList);
					response.setMetaData("Respuesta ok", "00", "Book actualizado");
				} else {
					response.setMetaData("Respuesta nok", "-1", "boook no actualizado");
					log.info("Categoria no actualizada");
					return new ResponseEntity<BookResponseRest>(response, HttpStatus.BAD_REQUEST);
				}
			} else {
				response.setMetaData("Respuesta nok", "-1", "book no modificado correctamente");
				log.info("Error al modificar book");
				return new ResponseEntity<BookResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			response.setMetaData("Respuesta no", "-1", "Error al modificar book");
			log.info("Error al modificar book: ", e.getMessage());
			e.getStackTrace();
			return new ResponseEntity<BookResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<BookResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<BookResponseRest> searchBookById(Long id) {
		log.info("Inicio modo searchBookById()");
		BookResponseRest response = new BookResponseRest();
		List<Book> bookList = new ArrayList<Book>();
		try {
			Optional<Book> bookSearch = bookdao.findById(id);
			if (bookSearch.isPresent()) {
				bookList.add(bookSearch.get());
				response.getBookResponse().setBookList(bookList);
				response.setMetaData("Respuesta ok", "00", "Respuesta exitosa");
			} else {
				response.setMetaData("Respuesta nok", "-1", "boook no encontrado");
				log.info("Categoria no encontrado");
				return new ResponseEntity<BookResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			response.setMetaData("Respuesta no", "-1", "Error al modificar book");
			log.info("Error al modificar book: ", e.getMessage());
			e.getStackTrace();
			return new ResponseEntity<BookResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<BookResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<BookResponseRest> deleteBook(Long id) {
		log.info("Inicio modo deleteBook");
		BookResponseRest response = new BookResponseRest();
		try {
			bookdao.deleteById(id);
			response.setMetaData("Respuesta ok", "00", "Book eliminado");

		} catch (Exception e) {
			response.setMetaData("Respuesta no", "-1", "Error al eliminar book");
			log.info("Error al eliminar book: ", e.getMessage());
			e.getStackTrace();
			return new ResponseEntity<BookResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<BookResponseRest>(response, HttpStatus.OK);
	}

}
