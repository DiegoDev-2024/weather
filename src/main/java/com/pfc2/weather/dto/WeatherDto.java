package com.pfc2.weather.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherDto {

    private Integer id;
    private String main;
    private String description;
    private String icon;

}
