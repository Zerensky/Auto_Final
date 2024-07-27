package Lesson6.indices;


import Lesson6.AbstractAccuweatherTest;
import Lesson6.Indices.oneDay.OneDay;
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

public class OneDayValuesAllIndicesTest extends AbstractAccuweatherTest {

    @Test
    @DisplayName("OneDayValuesAllIndicesTest")
    @Description("GET 1 Day of Daily Index Values for All Indices")
    @Severity(SeverityLevel.NORMAL)
    @Story(value = "Request testing By Location key 5")
    void getOneDayValuesGroup() {

        List<OneDay> response = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl()+"/indices/v1/daily/1day/5")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000l))
                .extract()
                .body().jsonPath().getList(".", OneDay.class);

        Assertions.assertEquals(48,response.size());
        Assertions.assertEquals("Flight Delays", response.get(0).getName());
    }
}
