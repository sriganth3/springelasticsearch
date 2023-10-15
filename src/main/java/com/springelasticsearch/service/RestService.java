package com.springelasticsearch.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springelasticsearch.logic.RequestProcessor;
import com.springelasticsearch.model.Product;

import co.elastic.clients.elasticsearch._types.ElasticsearchException;

@Controller
@RequestMapping("/service/elastic")
public class RestService {

	private Logger log = LoggerFactory.getLogger(RestService.class);
	
	@Autowired
	private RequestProcessor processor;
	
	@PostMapping("/upsertProduct")
	public ResponseEntity upsertProduct(@RequestBody Product product) throws ElasticsearchException, IOException {
		Map<String, String> map = new HashMap<String, String>();
		if(product == null) {
			map.put("message", "productName is mandatory");
			return ResponseEntity.status(400).body(map).badRequest().build();
		}
		if(product.getName() == null) {
			map.put("message", "productName is mandatory");
			return ResponseEntity.status(400).body(map).badRequest().build();
		}
		
		String response = processor.upsertProduct(product);
		
		map.put("message", response);
		
		return ResponseEntity.status(200).body(map).ok().build();
		
	}
}
