package com.company.books.backend.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BookResponseRest extends ResponseRest{
	
	private BookResponse bookResponse = new BookResponse();

}
