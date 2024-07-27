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

public class ListDailyIndicesTest extends AbstractAccuweatherTest {

    @Test
    @DisplayName("ListDailyIndicesTest")
    @Description("GET List of Daily Indices")
    @Severity(SeverityLevel.NORMAL)
    void getListDailyIndices() {

        List<Metadata> response = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl()+"/indices/v1/daily")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000l))
                .extract()
                .body().jsonPath().getList(".", Metadata.class);

        Assertions.assertEquals(117,response.size());
        Assertions.assertEquals("Air Conditioning Index", response.get(0).getName());
    }
}
