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

public class ListDailyGroupTest extends AbstractAccuweatherTest {

    @Test
    @DisplayName("ListDailyGroupTest")
    @Description("GET List of Index Groups")
    @Severity(SeverityLevel.NORMAL)
    void getListDailyGroupIndices() {

        List<Metadata> response = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl()+"/indices/v1/daily/groups")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000l))
                .extract()
                .body().jsonPath().getList(".", Metadata.class);

        Assertions.assertEquals(47,response.size());
        Assertions.assertEquals("All API", response.get(0).getName());
    }
}
