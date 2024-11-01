import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class PostTests {

    @Test
    void createUserTest() {
        String data = "{\"name\": \"alex\",\"job\": \"qa engineer\"}";

        given()
                .body(data)
                .contentType(JSON)

                .when()
                .log().uri()
                .post("https://reqres.in/api/users")

                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("alex"))
                .body("job", is("qa engineer"));
    }

    @Test
    void successfulRegistrationTest() {
        String data = "{\"email\": \"eve.holt@reqres.in\",\"password\": \"pistol\"}";

        given()
                .body(data)
                .contentType(JSON)

                .when()
                .log().uri()
                .post("https://reqres.in/api/register")

                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("id", is(4))
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    void unsuccessfulRegistrationMissingPasswordTest() {
        String data = "{\"email\": \"eve.holt@reqres.in\",\"password\": \"\"}";

        given()
                .body(data)
                .contentType(JSON)

                .when()
                .log().uri()
                .post("https://reqres.in/api/register")

                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing password"));
    }

    @Test
    void unsuccessfulRegistrationMissingEmailTest() {
        String data = "{\"email\": \"\",\"password\": \"pistol\"}";

        given()
                .body(data)
                .contentType(JSON)

                .when()
                .log().uri()
                .post("https://reqres.in/api/register")

                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing email or username"));
    }
}