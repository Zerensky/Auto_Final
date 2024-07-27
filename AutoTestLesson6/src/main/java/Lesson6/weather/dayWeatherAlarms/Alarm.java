package Lesson6.weather.dayWeatherAlarms;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "AlarmType",
    "Value",
    "Day",
    "Night"
})

public class Alarm {

    @JsonProperty("AlarmType")
    private String alarmType;
    @JsonProperty("Value")
    private Value value;
    @JsonProperty("Day")
    private Day day;
    @JsonProperty("Night")
    private Night night;

    @JsonProperty("AlarmType")
    public String getAlarmType() {
        return alarmType;
    }

    @JsonProperty("AlarmType")
    public void setAlarmType(String alarmType) {
        this.alarmType = alarmType;
    }

    @JsonProperty("Value")
    public Value getValue() {
        return value;
    }

    @JsonProperty("Value")
    public void setValue(Value value) {
        this.value = value;
    }

    @JsonProperty("Day")
    public Day getDay() {
        return day;
    }

    @JsonProperty("Day")
    public void setDay(Day day) {
        this.day = day;
    }

    @JsonProperty("Night")
    public Night getNight() {
        return night;
    }

    @JsonProperty("Night")
    public void setNight(Night night) {
        this.night = night;
    }

}
