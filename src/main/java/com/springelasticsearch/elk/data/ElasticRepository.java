package com.springelasticsearch.elk.data;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springelasticsearch.model.Product;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.ElasticsearchException;
import co.elastic.clients.elasticsearch._types.Script;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.core.SearchResponse;

@Repository
public class ElasticRepository {


	@Autowired
	ElasticsearchClient elasticSearchClient;

	public String getProductByName(String name) throws ElasticsearchException, IOException {	

		SearchResponse<Product> response = elasticSearchClient.search(s -> s
				.index("products")
				.query(q -> q
						.match(t -> t
								.field("name")
								.query(name)
								)
						),
				Product.class);

		String id = null;
		if(response != null && response.hits() != null && response.hits().hits() != null)
			if(response.hits().hits().size() > 0) {
				id = response.hits().hits().get(0).id();
			}
		return id;
	}

	public String insertProduct(Product product) throws ElasticsearchException, IOException {
		// TODO Auto-generated method stub
		IndexResponse response = elasticSearchClient.index(i -> i
				.index("products")
				.document(product)
				);

		return response.id();
	}

	public void updateProduct(Product product, String id) throws ElasticsearchException, IOException {
		// TODO Auto-generated method stub
		elasticSearchClient.update(u -> u
				.index("products")
				.id(id).doc(product)
				.upsert(product),
				Product.class
				);	
	}
}
