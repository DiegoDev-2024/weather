package com.pfc2.weather.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class WeatherApiResponse {

    @JsonProperty("weather")
    private List<WeatherDto> weather;
    @JsonProperty("main")
    private WeatherMainDto main;
    @JsonCreator
    public WeatherApiResponse(@JsonProperty("weather") final List<WeatherDto> weather,
                                    @JsonProperty("main") final WeatherMainDto main) {
        this.weather= weather;
        this.main= main;
    }
}
