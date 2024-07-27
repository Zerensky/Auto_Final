package Lesson6.alarm;


import Lesson6.AbstractAccuweatherTest;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;


public class FifteenDaysOfWeatherAlarmsTest extends AbstractAccuweatherTest {

    @Test
    @DisplayName("FiveDayWeatherAlarmsTest")
    @Description("GET Fifteen Day of Weather Alarms")
    @Severity(SeverityLevel.NORMAL)
    @Story(value = "Testing a 401 response code request")
    void getFifteenDaysOfWeatherAlarms() {

        given()
                .when()
                .get(getBaseUrl()+"/alarms/v1/15day//290421" +
                        "apikey=" +getApiKey())
                .then()
                .statusCode(401)
                .time(lessThan(2000L))
                .statusLine("HTTP/1.1 401 Unauthorized");

    }
}
