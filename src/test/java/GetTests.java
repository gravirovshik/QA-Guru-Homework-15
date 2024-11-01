import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;

public class GetTests {
    @Test
    @Disabled
    void getUserTest() {
        given()

                .when()
                .log().uri()
                .get("https://reqres.in/api/users/5")

                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data.id", is(5))
                .body("data.first_name", is("Charles"))
                .body("data.last_name", is("Morris"));
    }
    @Test
    @Disabled
    void userNotFoundTest() {
        given()

                .when()
                .log().uri()
                .get("https://reqres.in/api/users/123")

                .then()
                .log().status()
                .log().body()
                .statusCode(404);
    }

    @Test
    void successfulCheckListResourceTest() {

        given()
                .log().uri()
                .get("https://reqres.in/api/unknown")

                .then()
                .log().body()
                .assertThat()
                .body("data.id", hasItems(3, 4, 5))
                .body("data.name", hasItems("tigerlily", "aqua sky"))
                .body("data.year", hasItems(2001, 2000, 2003))
                .body("data.color", hasItems("#53B0AE", "#BF1932"))
                .body("data.pantone_value", hasItems("19-1664"));
    }
}
