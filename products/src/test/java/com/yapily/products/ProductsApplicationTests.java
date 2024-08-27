package com.yapily.products;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.request;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.util.Map;


class ProductsApplicationTests {
	public static final String baseUrl = "http://localhost:8081/products";
	static String requestBody;
	static Response response;
	@BeforeAll
	static void init() throws IOException {
		requestBody = JsonParser.readFileAsString("src/test/java/com/yapily/products/json/product.json");
	}
	@Order(1)
	@Test
	void addNewProduct(){
		response = given().contentType("application/json").body(requestBody).post(baseUrl);
		assertThat(response.getStatusCode(), equalTo(201));
	}

	@Order(2)
	@Test
	void deleteProduct(){
		response = given().contentType("application/json").delete(baseUrl+"/1");
		assertThat(response.getStatusCode(), equalTo(204));
	}

	@Order(3)
	@Test
	void getAllProducts(){
		response = given().contentType("application/json").get(baseUrl);
		assertThat(response.getStatusCode(), equalTo(200));
	}

	@Order(4)
	@Test
	void noLabelOnProduct() throws IOException {
		requestBody = JsonParser.readFileAsString("src/test/java/com/yapily/products/json/nolabels.json");
		response = given().contentType("application/json").body(requestBody).post(baseUrl);
		assertThat(response.getStatusCode(), equalTo(400));
	}

	@Order(5)
	@Test
	void characterLimit() throws IOException {
		requestBody = JsonParser.readFileAsString("src/test/java/com/yapily/products/json/characterlimit.json");
		response = given().contentType("application/json").body(requestBody).post(baseUrl);
		assertThat(response.getStatusCode(), equalTo(400));
	}

	@Order(6)
	@Test
	void listOneProduct() {
		response = given().contentType("application/json").get(baseUrl +"/2");
		assertThat(response.body().jsonPath().get("name"), equalTo("Special Smelly Cheesess"));
	}
}
