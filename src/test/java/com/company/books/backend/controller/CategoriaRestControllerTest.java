package com.company.books.backend.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.company.books.backend.controllers.CategoriaRestController;
import com.company.books.backend.model.Categoria;
import com.company.books.backend.response.CategoryResponseRest;
import com.company.books.backend.services.ICategoryService;

public class CategoriaRestControllerTest {

	@InjectMocks
	CategoriaRestController categoriaRestController;
 
	@Mock
    ICategoryService categoryService;
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void crear() {

		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		
		Categoria categoria = new Categoria(5L,"Comedia", "Peliculas de comedia");

		when(categoryService.saveCategory(any(Categoria.class))).thenReturn(new ResponseEntity<CategoryResponseRest>(HttpStatus.OK));
		ResponseEntity<CategoryResponseRest> response = categoriaRestController.save(categoria);
		
		assertThat(response.getStatusCodeValue()).isEqualTo(200);
		
	}
	
	@Test
	public void update() {

		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		
		Categoria categoria = new Categoria(5L,"Comedia", "Peliculas de comedia");

		when(categoryService.updateCategory(any(Categoria.class), any(Long.class))).thenReturn(new ResponseEntity<CategoryResponseRest>(HttpStatus.OK));
		ResponseEntity<CategoryResponseRest> response = categoriaRestController.update(categoria, 5L);
		
		assertThat(response.getStatusCodeValue()).isEqualTo(200);
		
	}
}
