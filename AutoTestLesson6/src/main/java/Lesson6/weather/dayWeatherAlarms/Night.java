package Lesson6.weather.dayWeatherAlarms;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "Metric",
    "Imperial"
})

public class Night {

    @JsonProperty("Metric")
    private Metric__2 metric;
    @JsonProperty("Imperial")
    private Imperial__2 imperial;

    @JsonProperty("Metric")
    public Metric__2 getMetric() {
        return metric;
    }

    @JsonProperty("Metric")
    public void setMetric(Metric__2 metric) {
        this.metric = metric;
    }

    @JsonProperty("Imperial")
    public Imperial__2 getImperial() {
        return imperial;
    }

    @JsonProperty("Imperial")
    public void setImperial(Imperial__2 imperial) {
        this.imperial = imperial;
    }

}
