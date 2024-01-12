package com.pfc2.weather.controller;

import com.pfc2.weather.exception.ResponseError;
import com.pfc2.weather.exception.ResponseErrors;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys/status")
@Tag(name = "Status Controller", description = "Controlador para testear disponibilidad del servicio.")
public class StatusController {

    @GetMapping()
    @Operation(summary = "Obtener Status del microservicio",
            description = "Devuelve respuesta de la disponibilidad del servicio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa", content = @io.swagger.v3.oas.annotations.media.Content),
            @ApiResponse(responseCode = "400", description = "Bad Request - Parámetros incorrectos",
                    content = @Content(schema = @Schema(implementation = ResponseErrors.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Solicitud sin autorización", content = @io.swagger.v3.oas.annotations.media.Content),
            @ApiResponse(responseCode = "404", description = "Not Found - Datos o recursos no encontrados"
                    , content = @io.swagger.v3.oas.annotations.media.Content),
            @ApiResponse(responseCode = "503", description = "Service Unavailable - Excepciones no controladas"
                    , content = @io.swagger.v3.oas.annotations.media.Content)
    })
    public ResponseEntity<?> sysStatus() {
        return ResponseEntity.ok().build();
    }
}
