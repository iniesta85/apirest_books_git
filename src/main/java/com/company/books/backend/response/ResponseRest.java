package com.company.books.backend.response;

import java.util.ArrayList;
import java.util.HashMap;

import lombok.Data;

@Data
public class ResponseRest {

	private ArrayList<HashMap<String, String>> metadata = new ArrayList<>();
	
	public void setMetaData(String type, String code, String data) {
		HashMap<String, String> mapa = new HashMap<String, String>();
		
		mapa.put("type", type);
		mapa.put("code", code);
		mapa.put("data", data);
		
		metadata.add(mapa);
	}
}
