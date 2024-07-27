package Lesson6.weather.fiveDay;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "Metric",
    "Imperial"
})

public class Day {

    @JsonProperty("Metric")
    private Metric__1 metric;
    @JsonProperty("Imperial")
    private Imperial__1 imperial;

    @JsonProperty("Metric")
    public Metric__1 getMetric() {
        return metric;
    }

    @JsonProperty("Metric")
    public void setMetric(Metric__1 metric) {
        this.metric = metric;
    }

    @JsonProperty("Imperial")
    public Imperial__1 getImperial() {
        return imperial;
    }

    @JsonProperty("Imperial")
    public void setImperial(Imperial__1 imperial) {
        this.imperial = imperial;
    }

}
