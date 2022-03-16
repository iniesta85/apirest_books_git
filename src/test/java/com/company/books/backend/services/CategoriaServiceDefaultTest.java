package com.company.books.backend.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.company.books.backend.dao.ICategoriaDao;
import com.company.books.backend.model.Categoria;
import com.company.books.backend.response.CategoryResponseRest;

public class CategoriaServiceDefaultTest {

	@InjectMocks
	CategoryServiceDefault categoryService;
	
	@Mock
	ICategoriaDao categoriaDao;
	
	List<Categoria> categoriaList = new ArrayList<Categoria>();
	
	@BeforeEach
	public void  init() {
		MockitoAnnotations.openMocks(this);
		this.loadCategories();
	}
	
	@Test
	public void searchCategoriesTest() {
		
		when(categoriaDao.findAll()).thenReturn(categoriaList);
		
		ResponseEntity<CategoryResponseRest> response = categoryService.searchCategories();
		
		assertEquals(4, response.getBody().getCategoryResponse().getCategoryList().size());
		
		verify(categoriaDao, times(1)).findAll();
	}
	
	
	public void loadCategories() {
		
		Categoria categoria1 = new Categoria(Long.valueOf(1),"Comedia", "Peliculas de comedia");		
		Categoria categoria2 = new Categoria(Long.valueOf(2),"Acción", "Peliculas de acción");
		Categoria categoria3 = new Categoria(Long.valueOf(3),"Suspense", "Peliculas de suspense");
		Categoria categoria4 = new Categoria(Long.valueOf(4),"Drama", "Peliculas Dramaticas ");
		
		categoriaList.add(categoria1);
		categoriaList.add(categoria2);
		categoriaList.add(categoria3);
		categoriaList.add(categoria4);
		
	}
}
