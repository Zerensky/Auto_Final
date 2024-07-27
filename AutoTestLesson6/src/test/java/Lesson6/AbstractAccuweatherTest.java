package Lesson6;

import org.junit.jupiter.api.BeforeAll;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AbstractAccuweatherTest {

    private static Properties prop = new Properties();

    private static InputStream configFile;

    private static String apiKey;

    private static String baseUrl;

    @BeforeAll
    static void initConfig() throws IOException {
        configFile = new FileInputStream("src/test/resources/accuweather.properties");
        prop.load(configFile);

        apiKey = prop.getProperty("apikey");
        baseUrl = prop.getProperty("base_url");
    }

    public static String getApiKey() {
        return apiKey;
    }

    public static String getBaseUrl() {
        return baseUrl;
    }

}
