package com.pfc2.weather.repository;

import com.pfc2.weather.model.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface WeatherRepository extends JpaRepository<Weather, Long> {
    @Query("SELECT w FROM Weather w WHERE w.lat = :lat AND w.lon = :lon AND w.createdAt >= :timestamp")
    Optional<Weather> findByLatAndLonAndRecent(@Param("lat") Double lat, @Param("lon") Double lon, @Param("timestamp") LocalDateTime timestamp);
}
