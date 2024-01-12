package com.pfc2.weather.service;

import com.pfc2.weather.client.WeatherClient;
import com.pfc2.weather.dto.WeatherApiResponse;
import com.pfc2.weather.dto.WeatherRequest;
import com.pfc2.weather.dto.WeatherResponse;
import com.pfc2.weather.exception.ApiResourceException;
import com.pfc2.weather.model.Weather;
import com.pfc2.weather.repository.WeatherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación de la interfaz WeatherService que proporciona funcionalidades relacionadas con el clima.
 */
@Service
public class WeatherServiceImpl implements WeatherService{
    private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);

    @Value("${feign.api-key}")
    private String apiWeatherApi;

    @Value("${myapp.minutesHold}")
    private Integer minutesHold;

    private final WeatherClient weatherClient;
    private final WeatherRepository weatherRepository;

    /**
     * Constructor de la clase WeatherServiceImpl.
     *
     * @param weatherClient       Cliente para acceder a la API del clima.
     * @param weatherRepository   Repositorio para interactuar con la base de datos de clima.
     */
    public WeatherServiceImpl(WeatherClient weatherClient, WeatherRepository weatherRepository) {
        this.weatherClient = weatherClient;
        this.weatherRepository = weatherRepository;
    }

    /**
     * Obtiene la información del clima por latitud y longitud.
     *
     * @param request   Objeto que contiene la latitud y longitud.
     * @return          Respuesta con la información del clima.
     */
    @Override
    public WeatherResponse getWeatherByLatAndLog(WeatherRequest request) {
        logger.info("Iniciando getWeatherByLatAndLog para lat={}, lon={}", request.getLat(), request.getLon());
        LocalDateTime timestamp = LocalDateTime.now().minusMinutes(minutesHold);

        return weatherRepository.findByLatAndLonAndRecent(request.getLat(), request.getLon(), timestamp)
                .map(this::weatherResponseFromEntity)
                .orElseGet(() -> {
                    WeatherResponse responseApi = fetchWeatherFromApi(request.getLat(), request.getLon());
                    saveWeatherFromApi(responseApi, request);
                    logger.info("getWeatherByLatAndLog completado para lat={}, lon={}", request.getLat(), request.getLon());
                    return responseApi;
                });
    }

    /**
     * Obtiene la información del clima para todas las ubicaciones.
     *
     * @return Lista de respuestas con la información del clima.
     */
    @Override
    public List<WeatherResponse> getWeathersAll() {
        logger.info("Iniciando getWeathersAll");
        List<Weather> listResult = weatherRepository.findAll();
        List<WeatherResponse> response = listResult.stream()
                .map(this::weatherResponseFromEntity)
                .collect(Collectors.toList());

        logger.info("getWeathersAll completado");
        return response;
    }

    private void saveWeatherFromApi(WeatherResponse responseApi, WeatherRequest request){
        logger.info("Guardando respuesta de API Externa para lat={}, lon={}", request.getLat(), request.getLon());
        Weather weather = new Weather();
        weather.setLat(request.getLat());
        weather.setLon(request.getLon());
        weather.setWeather(responseApi.getWeather());
        weather.setHumidity(responseApi.getHumidity());
        weather.setTempMax(responseApi.getTempMax());
        weather.setTempMin(responseApi.getTempMin());
        weather.setCreatedAt(LocalDateTime.now());
        weatherRepository.save(weather);
    }

    private WeatherResponse fetchWeatherFromApi(Double lat, Double lon){
        try {
            WeatherApiResponse responseClient = this.weatherClient.getWeatherApiResponse(lat, lon, apiWeatherApi);
            logger.info("Obteniendo respuesta de API Externa...");
            return WeatherResponse.builder()
                    .humidity(responseClient.getMain().getHumidity())
                    .tempMax(responseClient.getMain().getTempMax())
                    .tempMin(responseClient.getMain().getTempMin())
                    .weather(responseClient.getWeather().get(0).getMain())
                    .build();
        } catch (Exception e) {
            logger.error("Error al obtener respuesta de la API externa para lat={}, lon={}", lat, lon, e);
            throw new ApiResourceException(e.getMessage());
        }
    }

    private WeatherResponse weatherResponseFromEntity(Weather weather){
        return WeatherResponse.builder()
                .weather(weather.getWeather())
                .tempMin(weather.getTempMin())
                .tempMax(weather.getTempMax())
                .humidity(weather.getHumidity())
                .build();
    }
}
