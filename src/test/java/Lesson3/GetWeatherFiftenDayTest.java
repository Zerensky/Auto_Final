package Lesson3;

import io.restassured.http.Method;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;



public class GetWeatherFiftenDayTest extends AccuweatherAbstractTest{
    @Test
    void get_ten_day_return_401() {

        given()
                .queryParam("apikey", getApiKey())
                .pathParam("version", "v1")
                .pathParam("location", 293686)
                .when()
                .request(Method.GET,getBaseUrl()+"/forecasts/{version}/daily/15day/{location}")
                .then()
                .statusCode(401);
    }
}