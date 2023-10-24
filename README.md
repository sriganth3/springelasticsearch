# springelasticsearch

Prerequsites:
JDK, Maven(To run application directly from cmd prompt) or any IDE, elasticsearch

# Steps to start the application:

# Open elasticsearch folder and start the elasticsearch:

bin\elasticsearch.bat


# create product index in elk:
PUT https://localhost:9200/product 
(or) 
 PUT http://localhost:9200/product

# Open the project folder: 
mvn clean install

mvn spring-boot:run -Drun.arguments=--customArgument=custom

#Sample Request:
localhost:8080/service/elastic/upsertProduct

{
	"name": "1000 Years Old Wine",
	"price": 152,
	"in_stock": 38,
	"sold": 47,
	"tags": [
		"Beverage",
		"Alcohol",
		"Wine"
	],
	"description": "Aliquam augue quam, sollicitudin vitae, consectetuer eget, rutrum at, lorem. Integer tincidunt ante vel ipsum. Praesent blandit lacinia erat. Vestibulum sed magna at nunc commodo placerat. Praesent blandit. Nam nulla. Integer pede justo, lacinia eget, tincidunt eget, tempus vel, pede. Morbi porttitor lorem id ligula.",
	"is_active": true,
	"created": "2004\/05\/13"
}