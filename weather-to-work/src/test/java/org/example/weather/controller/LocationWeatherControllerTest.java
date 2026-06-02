package org.example.weather.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LocationWeatherController.class)
class LocationWeatherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LocationWeatherService locationWeatherService;

    @Test
    void getWeatherByLocation_returnsLocationWeather() throws Exception {
        var currentWeather = new WeatherResponse.CurrentWeather(
                22.5, 15.2, 270.0, 2, 1, "2025-01-15T14:00"
        );
        var response = new LocationWeatherResponse(
                "Buenos Aires", "Argentina", -34.61, -58.38, currentWeather
        );
        when(locationWeatherService.getWeatherByLocationName("Buenos Aires")).thenReturn(response);

        mockMvc.perform(get("/api/weather/location")
                        .param("name", "Buenos Aires"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Buenos Aires"))
                .andExpect(jsonPath("$.country").value("Argentina"))
                .andExpect(jsonPath("$.currentWeather.temperature").value(22.5));
    }

    @Test
    void getWeatherByLocation_notFound_returns404() throws Exception {
        when(locationWeatherService.getWeatherByLocationName("NonExistent"))
                .thenThrow(new LocationWeatherService.LocationNotFoundException("No location found for: NonExistent"));

        mockMvc.perform(get("/api/weather/location")
                        .param("name", "NonExistent"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getWeatherByLocation_missingName_returnsBadRequest() throws Exception {
        mockMvc.perform(get("/api/weather/location"))
                .andExpect(status().isBadRequest());
    }
}