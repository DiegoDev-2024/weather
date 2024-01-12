package com.pfc2.weather.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherMainDto implements Serializable {
    private static final long serialVersionUID = 4433253028251307185L;
    @JsonProperty("temp")
    private Double temp;
    @JsonProperty("feels_like")
    private Double feelsLike;
    @JsonProperty("temp_min")
    private Double tempMin;
    @JsonProperty("temp_max")
    private Double tempMax;
    @JsonProperty("pressure")
    private Double pressure;
    @JsonProperty("humidity")
    private Double humidity;
    @JsonProperty("seaLevel")
    private Double sea_level;
    @JsonProperty("grndLevel")
    private Double grnd_level;

}
