package com.pfc2.weather.service;

import com.pfc2.weather.dto.WeatherRequest;
import com.pfc2.weather.dto.WeatherResponse;

import java.util.List;

public interface WeatherService {
    WeatherResponse getWeatherByLatAndLog(WeatherRequest request);
    List<WeatherResponse> getWeathersAll();
}
