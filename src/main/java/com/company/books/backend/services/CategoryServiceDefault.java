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

import com.company.books.backend.dao.ICategoriaDao;
import com.company.books.backend.model.Categoria;
import com.company.books.backend.response.CategoryResponseRest;

@Service
public class CategoryServiceDefault implements ICategoryService {

	private static final Logger log = LoggerFactory.getLogger(CategoryServiceDefault.class);

	@Autowired
	private ICategoriaDao categoriaDao;

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<CategoryResponseRest> searchCategories() {

		log.info("Inicio modo buscaCategorias()");
		CategoryResponseRest response = new CategoryResponseRest();

		try {
			List<Categoria> categoryList = (List<Categoria>) categoriaDao.findAll();
			response.getCategoryResponse().setCategoryList(categoryList);
			response.setMetaData("Respuesta ok", "00", "Respuesta exitosa");

		} catch (Exception e) {
			response.setMetaData("Respuesta no", "-1", "Error al consultar categorias");
			log.info("Error al consultar categorias: ", e.getMessage());
			e.getStackTrace();
			return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<CategoryResponseRest> searchId(Long id) {
		log.info("Inicio modo searchId()");
		CategoryResponseRest response = new CategoryResponseRest();
		List<Categoria> categoryList = new ArrayList<>();

		try {
			Optional<Categoria> category = categoriaDao.findById(id);

			if (category.isPresent()) {
				categoryList.add(category.get());
				response.getCategoryResponse().setCategoryList(categoryList);
				response.setMetaData("Respuesta ok", "00", "Respuesta exitosa");
			} else {
				response.setMetaData("Respuesta nok", "-1", "categoria no encontrada");
				log.info("Error al consultar categoria por id");
				return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			response.setMetaData("Respuesta no", "-1", "Error al consultar categoria por id");
			log.info("Error al consultar categoria por Id: ", e.getMessage());
			e.getStackTrace();
			return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<CategoryResponseRest> saveCategory(Categoria category) {
		log.info("Inicio modo saveCategory()");
		CategoryResponseRest response = new CategoryResponseRest();
		List<Categoria> categoryList = new ArrayList<>();

		try {
			Categoria categoriaGuardada = categoriaDao.save(category);

			if (categoriaGuardada != null) {
				categoryList.add(categoriaGuardada);
				response.getCategoryResponse().setCategoryList(categoryList);
				response.setMetaData("Respuesta ok", "00", "Respuesta exitosa");
			} else {
				response.setMetaData("Respuesta nok", "-1", "categoria no guardada correctamente");
				log.info("Error al guardar categoria");
				return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			response.setMetaData("Respuesta no", "-1", "Error al guardar categoria");
			log.info("Error al guardar categoria: ", e.getMessage());
			e.getStackTrace();
			return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<CategoryResponseRest> updateCategory(Categoria category, Long id) {
		log.info("Inicio modo updateCategory()");
		CategoryResponseRest response = new CategoryResponseRest();
		List<Categoria> categoryList = new ArrayList<>();

		try {
			Optional<Categoria> categoriaBuscada = categoriaDao.findById(id);

			if (categoriaBuscada.isPresent()) {
				categoriaBuscada.get().setName(category.getName());
				categoriaBuscada.get().setDescription(category.getDescription());

				Categoria categoriaActualizada = categoriaDao.save(categoriaBuscada.get());

				if (categoriaActualizada != null) {
					categoryList.add(categoriaActualizada);
					response.getCategoryResponse().setCategoryList(categoryList);
					response.setMetaData("Respuesta ok", "00", "Categoria actualizada");
				} else {
					response.setMetaData("Respuesta nok", "-1", "categoria no actualizada");
					log.info("Categoria no actualizada");
					return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.BAD_REQUEST);
				}
			} else {
				response.setMetaData("Respuesta nok", "-1", "categoria no actualizada");
				log.info("Error en actualizar categoria");
				return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			response.setMetaData("Respuesta no", "-1", "Error al actualizar categoria");
			log.info("Error al actualizar categoria: ", e.getMessage());
			e.getStackTrace();
			return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<CategoryResponseRest> deleteCategory(Long id) {
		log.info("Inicio modo deleteCategory()");
		CategoryResponseRest response = new CategoryResponseRest();

		try {
			categoriaDao.deleteById(id);
			response.setMetaData("Respuesta ok", "00", "Categoria eliminada");
		} catch (Exception e) {
			response.setMetaData("Respuesta no", "-1", "Error al eliminar categoria");
			log.info("Error al eliminar category: ", e.getMessage());
			e.getStackTrace();
			return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
	}

}
