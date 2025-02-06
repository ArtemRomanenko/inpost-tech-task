package apiclient;

import config.Config;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class ApiClient {

    public Response response;
    private final String baseUri = Config.BASE_URI;

    public Response sendSearchLockerRequest(String city){
        return given()
                .spec(requestSpecification())
                .queryParam("city", city)
                .get();
    }

    private RequestSpecification requestSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .setBasePath("points")
                .setContentType(ContentType.JSON)
                .build();
    }
}
