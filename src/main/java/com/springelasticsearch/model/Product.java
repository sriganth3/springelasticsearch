package com.springelasticsearch.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
	"_id",
	"name",
	"price",
	"in_stock",
	"sold",
	"tags",
	"description",
	"is_active",
	"created"
})
public class Product {

	@JsonProperty("name")
	private String name;
	@JsonProperty("price")
	private Integer price;
	@JsonProperty("in_stock")
	private Integer inStock;
	@JsonProperty("sold")
	private Integer sold;
	@JsonProperty("tags")
	private List<String> tags;
	@JsonProperty("description")
	private String description;
	@JsonProperty("is_active")
	private Boolean isActive;
	@JsonProperty("created")
	private String created;

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("price")
	public Integer getPrice() {
		return price;
	}

	@JsonProperty("price")
	public void setPrice(Integer price) {
		this.price = price;
	}

	@JsonProperty("in_stock")
	public Integer getInStock() {
		return inStock;
	}

	@JsonProperty("in_stock")
	public void setInStock(Integer inStock) {
		this.inStock = inStock;
	}

	@JsonProperty("sold")
	public Integer getSold() {
		return sold;
	}

	@JsonProperty("sold")
	public void setSold(Integer sold) {
		this.sold = sold;
	}

	@JsonProperty("tags")
	public List<String> getTags() {
		return tags;
	}

	@JsonProperty("tags")
	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	@JsonProperty("description")
	public String getDescription() {
		return description;
	}

	@JsonProperty("description")
	public void setDescription(String description) {
		this.description = description;
	}

	@JsonProperty("is_active")
	public Boolean getIsActive() {
		return isActive;
	}

	@JsonProperty("is_active")
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@JsonProperty("created")
	public String getCreated() {
		return created;
	}

	@JsonProperty("created")
	public void setCreated(String created) {
		this.created = created;
	}


}