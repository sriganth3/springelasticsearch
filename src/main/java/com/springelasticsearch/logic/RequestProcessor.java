package com.springelasticsearch.logic;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springelasticsearch.elk.data.ElasticRepository;
import com.springelasticsearch.model.Product;

import co.elastic.clients.elasticsearch._types.ElasticsearchException;

@Component
public class RequestProcessor {

	@Autowired
	private ElasticRepository elasticRepo;
	
	private Logger log = LoggerFactory.getLogger(RequestProcessor.class);
	
	public String upsertProduct(Product product) throws ElasticsearchException, IOException {
		
		String responseStr;
		String id = elasticRepo.getProductByName(product.getName());
		if(id == null) {
			log.info("Inserting a product with Name: {}", product.getName() );
			String Id = elasticRepo.insertProduct(product);
			log.info("Inserted a product with Id: {}", Id );
			responseStr = "Inserted a product with Id: " + Id;
		}else {
			log.info("Updating a product with Id: {}", id );
			elasticRepo.updateProduct(product,id);
			responseStr = "Updated a product with Id: " + id;
		}
		return responseStr;
	}
}
