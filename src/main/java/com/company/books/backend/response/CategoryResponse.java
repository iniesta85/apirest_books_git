package com.company.books.backend.response;


import java.util.List;
import com.company.books.backend.model.Categoria;

import lombok.Data;

@Data
public class CategoryResponse {

	private List<Categoria> categoryList;
}
