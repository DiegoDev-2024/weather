package com.pfc2.weather.controller;

import com.pfc2.weather.dto.WeatherRequest;
import com.pfc2.weather.dto.WeatherResponse;
import com.pfc2.weather.exception.ResponseError;
import com.pfc2.weather.exception.ResponseErrors;
import com.pfc2.weather.service.WeatherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Clima Controller", description = "Controlador para obtner datos del clima.")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }
    @PostMapping("/weather")
    @Operation(summary = "Obtener Clima",
            description = "Devuelve datos climatológicos de una ubicación basada en latitud y longitud.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "400", description = "Bad Request - Parámetros incorrectos",
            content = @Content(schema = @Schema(implementation = ResponseErrors.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Solicitud sin autorización", content = @io.swagger.v3.oas.annotations.media.Content),
            @ApiResponse(responseCode = "404", description = "Not Found - Datos o recursos no encontrados",
                    content = @Content(schema = @Schema(implementation = ResponseError.class))),
            @ApiResponse(responseCode = "503", description = "Service Unavailable - Excepciones no controladas",
                    content = @Content(schema = @Schema(implementation = ResponseError.class)))
    })
    public ResponseEntity<WeatherResponse> getWeather(@Valid @RequestBody WeatherRequest request) {
        WeatherResponse response = weatherService.getWeatherByLatAndLog(request);
        return ResponseEntity.ok().body(response);
    }
    @GetMapping("/weather/history")
    @Operation(summary = "Obtener Lista de datos en base de datos",
            description = "Devuelve datos climatológicos consultados y guardados en Base de Datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "400", description = "Bad Request - Parámetros incorrectos",
                    content = @Content(schema = @Schema(implementation = ResponseErrors.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Solicitud sin autorización", content = @io.swagger.v3.oas.annotations.media.Content),
            @ApiResponse(responseCode = "404", description = "Not Found - Datos o recursos no encontrados",
                    content = @Content(schema = @Schema(implementation = ResponseError.class))),
            @ApiResponse(responseCode = "503", description = "Service Unavailable - Excepciones no controladas",
                    content = @Content(schema = @Schema(implementation = ResponseError.class)))
    })
    public ResponseEntity<List<WeatherResponse>> getWeathers() {
        List<WeatherResponse> response = weatherService.getWeathersAll();
        return ResponseEntity.ok().body(response);
    }

}
