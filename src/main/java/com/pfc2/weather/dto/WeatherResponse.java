package com.pfc2.weather.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WeatherResponse {
    private String weather;
    private Double tempMin;
    private Double tempMax;
    private Double humidity;
}
