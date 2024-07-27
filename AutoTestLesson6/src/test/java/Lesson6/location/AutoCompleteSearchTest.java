package Lesson6.location;


import Lesson6.AbstractAccuweatherTest;
import Lesson6.locations.autocomplete.AutocompleteSearch;
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

public class AutoCompleteSearchTest extends AbstractAccuweatherTest {

    @Test
    @DisplayName("AutoCompleteSearchTest")
    @Description("GET Autocomplete search")
    @Severity(SeverityLevel.NORMAL)
    @Story(value = "Request testing By Location key Berlin")
    void getAutoCompleteSearch() {

        List<AutocompleteSearch> response = given()
                .queryParam("apikey", getApiKey())
                .param("q", "Berlin")
                .when()
                .get(getBaseUrl()+"/locations/v1/cities/autocomplete")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000l))
                .extract()
                .body().jsonPath().getList(".", AutocompleteSearch.class);

        Assertions.assertEquals(10,response.size());
        Assertions.assertEquals("Berlin", response.get(0).getLocalizedName());
    }
}
