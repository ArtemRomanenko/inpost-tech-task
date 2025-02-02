package apiclient;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import utils.PropertyReader;

import static io.restassured.RestAssured.given;

public class ApiClient {

    public ValidatableResponse response;
    private final PropertyReader reader = new PropertyReader();
    private final String baseUri = reader.getProperty("base.uri");

    public ValidatableResponse sendSearchLockerRequest(String city){
        return given()
                .spec(requestSpecification())
                .queryParam("city", city)
                .get()
                .then().statusCode(200);
    }

    private RequestSpecification requestSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .setBasePath("points")
                .setContentType(ContentType.JSON)
                .build();
    }
}
