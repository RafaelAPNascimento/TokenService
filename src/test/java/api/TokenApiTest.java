package api;

import annotations.IntegrationTest;
import com.example.model.Credentials;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_OK;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TokenApiTest {

    private static final String BASE_URI = "http://localhost:8181/api";

    @IntegrationTest
    @DisplayName("should return 200 OK")
    public void shouldPass() {

        Credentials credentials = new Credentials("house", "HOUSE");

        given().baseUri(BASE_URI)
                .basePath("/token/issue")
                .contentType(JSON)
                .accept(JSON)
                .request().body(credentials)
                .log().all()
                .when()
                .post()
                .then().assertThat().statusCode(SC_OK)
                .log().all();
    }

    @IntegrationTest
    @DisplayName("should return 400")
    public void shouldNotPass() {

        Credentials credentials = new Credentials("house", "House");

        given().baseUri(BASE_URI)
                .basePath("/token/issue")
                .contentType(JSON)
                .accept(JSON)
                .request().body(credentials)
                .log().all()
                .when()
                .post()
                .then().assertThat().statusCode(SC_BAD_REQUEST)
                .log().all();
    }

    @IntegrationTest
    @DisplayName("should return 400")
    public void shouldNotPassByNull() {

        Credentials credentials = new Credentials("house", null);

        given().baseUri(BASE_URI)
                .basePath("/token/issue")
                .contentType(JSON)
                .accept(JSON)
                .request().body(credentials)
                .log().all()
                .when()
                .post()
                .then().assertThat().statusCode(SC_BAD_REQUEST)
                .log().all();
    }
}
