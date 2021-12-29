package dev.devloup.expostion;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import static io.restassured.RestAssured.given;

@QuarkusTest
final class UserControllerTest {
  @Test
  void testPost() {
    given()
        .when().post("/register")
        .then().statusCode(200);
  }
}
