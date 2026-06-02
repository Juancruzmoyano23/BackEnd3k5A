package org.example.weather.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LocationWeatherServiceTest {

    @Mock
    private GeocodingService geocodingService;

    @Mock
    private WeatherService weatherService;

    @InjectMocks
    private LocationWeatherService locationWeatherService;

    @Test
    void getWeatherByLocationName_returnsWeatherForFirstResult() {
        var location = new GeocodingResponse.Location(
                3435910, "Buenos Aires", -34.61, -58.38, 25.0,
                "AR", "Argentina", "Buenos Aires", "America/Argentina/Buenos_Aires", 13076300
        );
        when(geocodingService.search("Buenos Aires", 1))
                .thenReturn(new GeocodingResponse(List.of(location)));

        var currentWeather = new WeatherResponse.CurrentWeather(
                22.5, 15.2, 270.0, 2, 1, "2025-01-15T14:00"
        );
        var weatherResponse = new WeatherResponse(
                -34.61, -58.38, 0.05, 0, "GMT", "GMT", 25.0, currentWeather
        );
        when(weatherService.getWeather(-34.61, -58.38)).thenReturn(weatherResponse);

        LocationWeatherResponse result = locationWeatherService.getWeatherByLocationName("Buenos Aires");

        assertThat(result.name()).isEqualTo("Buenos Aires");
        assertThat(result.country()).isEqualTo("Argentina");
        assertThat(result.latitude()).isEqualTo(-34.61);
        assertThat(result.longitude()).isEqualTo(-58.38);
        assertThat(result.currentWeather().temperature()).isEqualTo(22.5);
        assertThat(result.currentWeather().windSpeed()).isEqualTo(15.2);
    }

    @Test
    void getWeatherByLocationName_noResults_throwsException() {
        when(geocodingService.search("NonExistentPlace", 1))
                .thenReturn(new GeocodingResponse(Collections.emptyList()));

        assertThatThrownBy(() -> locationWeatherService.getWeatherByLocationName("NonExistentPlace"))
                .isInstanceOf(LocationWeatherService.LocationNotFoundException.class)
                .hasMessageContaining("NonExistentPlace");
    }

    @Test
    void getWeatherByLocationName_nullResults_throwsException() {
        when(geocodingService.search("Unknown", 1))
                .thenReturn(new GeocodingResponse(null));

        assertThatThrownBy(() -> locationWeatherService.getWeatherByLocationName("Unknown"))
                .isInstanceOf(LocationWeatherService.LocationNotFoundException.class);
    }
}