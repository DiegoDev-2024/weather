package com.pfc2.weather.client;

import com.pfc2.weather.dto.WeatherApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "weather", url = "${feign.url-weather}")
public interface WeatherClient {
    @RequestMapping(method = RequestMethod.GET, value = "/weather", consumes = "application/json")
    WeatherApiResponse getWeatherApiResponse(@RequestParam Double lat,
                                             @RequestParam Double lon,
                                             @RequestParam String appid);
}
