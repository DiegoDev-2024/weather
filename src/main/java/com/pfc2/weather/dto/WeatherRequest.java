package com.pfc2.weather.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WeatherRequest {

    @NotNull(message = "El campo 'lat' es requerido")
    private Double lat;

    @NotNull(message = "El campo 'lon' es requerido")
    private Double lon;
}
