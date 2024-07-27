package Lesson6.indices;


import Lesson6.AbstractAccuweatherTest;
import Lesson6.Indices.fiveDay.FiveDay;
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

public class FiveDaysForAGroupTest extends AbstractAccuweatherTest {

    @Test
    @DisplayName("FiveDaysForAGroupTest")
    @Description("5 Days of Daily Index Values for a Group of Indices")
    @Severity(SeverityLevel.NORMAL)
    @Story(value = "Request testing By ID 52, Group ID 8")
    void getForFiveDaysForAGroup() {

        List<FiveDay> response = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl()+"/indices/v1/daily/5day/52/groups/8")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000l))
                .extract()
                .body().jsonPath().getList(".", FiveDay.class);

        Assertions.assertEquals(15,response.size());
        Assertions.assertEquals("Fishing Forecast", response.get(0).getName());
    }
}
