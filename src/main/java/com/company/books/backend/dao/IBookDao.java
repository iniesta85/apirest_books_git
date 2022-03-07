package com.company.books.backend.dao;

import org.springframework.data.repository.CrudRepository;

import com.company.books.backend.model.Book;

public interface IBookDao extends CrudRepository<Book, Long> {
}
