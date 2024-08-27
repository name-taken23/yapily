package com.yapily.carts;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


class CartApplicationTests {

	public static final String baseUrl = "http://localhost:8082/carts";
	static String requestBody;
	static Response response;
	@BeforeAll
	static void init() throws IOException {
		requestBody = JsonParser.readFileAsString("src/test/java/com/yapily/carts/json/cart.json");
	}
	@Order(1)
	@Test
	void addNewCart(){
		response = given().contentType("application/json").post(baseUrl);
		assertThat(response.getStatusCode(), equalTo(201));
	}

	@Order(2)
	@Test
	void getAllCarts(){
		response = given().contentType("application/json").get(baseUrl);
		assertThat(response.getStatusCode(), equalTo(200));
	}

	@Order(3)
	@Test
	void modifyACart() {
		response = given().contentType("application/json").body(requestBody).put(baseUrl+"/3");
		assertThat(response.getStatusCode(), equalTo(200));
	}

	@Order(4)
	@Test
	void checkoutACart() throws IOException {
		response = given().contentType("application/json").body(requestBody).post(baseUrl+"/3/checkout");
		assertThat(response.getStatusCode(), equalTo(200));
	}


}
