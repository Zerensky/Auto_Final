package Lesson6.location;


import Lesson6.AbstractAccuweatherTest;
import Lesson6.locations.list.Country;
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

public class ListCountryTest extends AbstractAccuweatherTest {

    @Test
    @DisplayName("ListCountryTest")
    @Description("GET Admin Area List")
    @Severity(SeverityLevel.NORMAL)
    @Story(value = "Request testing By Location key ARC")
    void getListCountry() {

        List<Country> response = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl()+"/locations/v1/countries/ARC")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000l))
                .extract()
                .body().jsonPath().getList(".", Country.class);

        Assertions.assertEquals(2,response.size());
        Assertions.assertEquals("Greenland", response.get(0).getLocalizedName());
    }
}
