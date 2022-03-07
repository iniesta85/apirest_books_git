package com.company.books.backend.dto;

import lombok.Data;

@Data
public class BookDto {
	
	private String name;
	private String description;
	private Long category_id;
	
}
