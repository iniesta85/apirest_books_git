package com.company.books.backend.services;

import org.springframework.http.ResponseEntity;

import com.company.books.backend.model.Categoria;
import com.company.books.backend.response.CategoryResponseRest;

public interface ICategoryService {

	public ResponseEntity<CategoryResponseRest> searchCategories();
	public ResponseEntity<CategoryResponseRest> searchId(Long id);
	public ResponseEntity<CategoryResponseRest> saveCategory(Categoria category);
	public ResponseEntity<CategoryResponseRest> updateCategory(Categoria category, Long id);
	public ResponseEntity<CategoryResponseRest> deleteCategory(Long id);
}
