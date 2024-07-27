package Lesson6.indices;


import Lesson6.AbstractAccuweatherTest;
import Lesson6.Indices.metadata.Metadata;
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

public class ListInASpecificGroupTest extends AbstractAccuweatherTest {

    @Test
    @DisplayName("ListInASpecificGroupTest")
    @Description("GET List of Indices in a Specific Group")
    @Severity(SeverityLevel.NORMAL)
    @Story(value = "Request testing By Index Group ID 8")
    void getListInASpecificGroup() {

        List<Metadata> response = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl()+"/indices/v1/daily/groups/8")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000l))
                .extract()
                .body().jsonPath().getList(".", Metadata.class);

        Assertions.assertEquals(3,response.size());
        Assertions.assertEquals("Fishing Forecast", response.get(0).getName());
    }
}
