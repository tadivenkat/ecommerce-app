package com.venkat.ecommerce.productservice;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MongoDBContainer;

import io.restassured.RestAssured;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceApplicationTests {

	@ServiceConnection
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:latest");
	static {
		mongoDBContainer.start();
	}
	@LocalServerPort
	private int port;

	@BeforeEach
	void setUp() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	@Test
	void contextLoads() {
	}

	@Test
	void shouldCreateProduct() {
		String requestBody = """
				{
					"name": "Product 1",
					"description": "Description 1",
					"price": 10.99
				}
				""";

		RestAssured.given()
				.contentType("application/json")
				.body(requestBody)
				.when()
				.post("/api/v1/products")
				.then()
				.statusCode(201)
				.body("id", Matchers.notNullValue())
				.body("name", Matchers.is("Product 1"))
				.body("description", Matchers.is("Description 1"));
	}

	@Test
	void shouldGetAllProducts() {
		RestAssured.given()
				.when()
				.get("/api/v1/products")
				.then()
				.statusCode(200)
				.body("size()", Matchers.is(1));
	}
}
