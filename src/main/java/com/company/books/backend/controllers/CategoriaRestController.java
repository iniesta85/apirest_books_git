package com.company.books.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.books.backend.model.Categoria;
import com.company.books.backend.response.CategoryResponseRest;
import com.company.books.backend.services.ICategoryService;

@RestController
@RequestMapping("/v1")
public class CategoriaRestController {

	@Autowired
	private ICategoryService categoryService;
	
	@GetMapping("/categorias")
	public ResponseEntity<CategoryResponseRest> consultarCategoria() {
		
		ResponseEntity<CategoryResponseRest> categoryResponseRest = categoryService.searchCategories();
		return categoryResponseRest;
	}
	
	@GetMapping("/categorias/{id}")
	public ResponseEntity<CategoryResponseRest> searchId(@PathVariable Long id) {
		
		ResponseEntity<CategoryResponseRest> categoryResponseRest = categoryService.searchId(id);
		return categoryResponseRest;
	}
	
	@PostMapping("/categorias/save") 
	public ResponseEntity<CategoryResponseRest> save(@RequestBody Categoria categoria) {
		
		ResponseEntity<CategoryResponseRest> categoryResponseRest = categoryService.saveCategory(categoria);
		return categoryResponseRest;
	}
	
	@PutMapping("/categorias/update/{id}")
	public ResponseEntity<CategoryResponseRest> Update(@RequestBody Categoria categoria, @PathVariable Long id) {
		
		ResponseEntity<CategoryResponseRest> categoryResponseRest = categoryService.updateCategory(categoria,id);
		return categoryResponseRest;
	}
	
	@DeleteMapping("/categorias/delete/{id}")
	public ResponseEntity<CategoryResponseRest> delete(@PathVariable Long id) {
		ResponseEntity<CategoryResponseRest> categoryResponseRest = categoryService.deleteCategory(id);
		return categoryResponseRest;
	}
}
