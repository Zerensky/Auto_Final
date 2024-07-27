package Lesson3;

import Lesson3.location.Location;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.util.List;

import static io.restassured.RestAssured.given;

public class GetLocationTest extends AccuweatherAbstractTest{

    @Test
    void getLocation_autocomplete_returnKrasnodar() {

        List<Lesson3.location.Location> response = given()
                .queryParam("apikey", getApiKey())
                .queryParam("q", "Krasnodar")
                .when()
                .get(getBaseUrl()+"/locations/v1/cities/autocomplete")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000l))
                .extract()
                .body().jsonPath().getList(".", Location.class);

        Assertions.assertEquals(10,response.size());
        Assertions.assertEquals("Krasnodar", response.get(0).getLocalizedName());
    }
}