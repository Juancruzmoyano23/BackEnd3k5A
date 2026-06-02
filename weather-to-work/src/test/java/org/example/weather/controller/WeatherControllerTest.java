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

@WebMvcTest(WeatherController.class)
class WeatherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WeatherService weatherService;

    @Test
    void getWeather_returnsWeatherData() throws Exception {
        var currentWeather = new WeatherResponse.CurrentWeather(
                25.3, 10.5, 180.0, 1, 1, "2025-01-15T12:00"
        );
        var response = new WeatherResponse(
                -34.61, -58.38, 0.05, 0,
                "GMT", "GMT", 25.0, currentWeather
        );
        when(weatherService.getWeather(-34.61, -58.38)).thenReturn(response);

        mockMvc.perform(get("/api/weather")
                        .param("latitude", "-34.61")
                        .param("longitude", "-58.38"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.latitude").value(-34.61))
                .andExpect(jsonPath("$.longitude").value(-58.38))
                .andExpect(jsonPath("$.current_weather.temperature").value(25.3))
                .andExpect(jsonPath("$.current_weather.windspeed").value(10.5));
    }

    @Test
    void getWeather_missingLatitude_returnsBadRequest() throws Exception {
        mockMvc.perform(get("/api/weather")
                        .param("longitude", "-58.38"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getWeather_missingLongitude_returnsBadRequest() throws Exception {
        mockMvc.perform(get("/api/weather")
                        .param("latitude", "-34.61"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getWeather_noParams_returnsBadRequest() throws Exception {
        mockMvc.perform(get("/api/weather"))
                .andExpect(status().isBadRequest());
    }
}
