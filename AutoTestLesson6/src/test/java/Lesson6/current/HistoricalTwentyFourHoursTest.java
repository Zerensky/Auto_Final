package Lesson6.current;


import Lesson6.AbstractAccuweatherTest;
import Lesson6.currentConditions.historical.Historical;
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

public class HistoricalTwentyFourHoursTest extends AbstractAccuweatherTest {

    @Test
    @DisplayName("HistoricalTwentyFourHoursTest")
    @Description("Historical Current Conditions (past 24 hours))")
    @Severity(SeverityLevel.NORMAL)
    @Story(value = "Request testing By ID 5")
    void getHistoricalTwentyFourHours() {

        List<Historical> response = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl()+"/currentconditions/v1/5/historical/24")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000l))
                .extract()
                .body().jsonPath().getList(".", Historical.class);

        Assertions.assertEquals(24,response.size());
        Assertions.assertEquals(
                "http://www.accuweather.com/en/gr/logos/2285860/current-weather/2285860?lang=en-us",
                response.get(0).getLink());
    }
}
