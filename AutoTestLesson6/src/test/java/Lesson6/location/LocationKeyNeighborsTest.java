package Lesson6.location;


import Lesson6.AbstractAccuweatherTest;
import Lesson6.locations.locationKey.LocationKey;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static io.restassured.RestAssured.given;

public class LocationKeyNeighborsTest extends AbstractAccuweatherTest {

    @Test
    @DisplayName("LocationKeyNeighborsTest")
    @Description("GET City Neighbors by locationKey")
    @Severity(SeverityLevel.NORMAL)
    @Story(value = "Request testing By Location key 620")
    void getLocationKeyNeighbors() {
        List<LocationKey> response = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl()+"/locations/v1/cities/neighbors/620")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000l))
                .extract()
                .body().jsonPath().getList(".", LocationKey.class);

        Assertions.assertEquals(10,response.size());
        Assertions.assertEquals("Mantes-la-Jolie", response.get(0).getLocalizedName());
    }
}
