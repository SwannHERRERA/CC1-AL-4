package dev.devloup.adapter.in.web;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class RegistrationControllerTest {
  @Test
  public void testPost() {
    given()
        .when().post("/register")
        .then().statusCode(200);
  }
}
