package org.example.weather.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest(WeatherService.class)
@TestPropertySource(properties = "weather.api.base-url=https://api.open-meteo.com/v1/forecast")
class WeatherServiceTest {

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private MockRestServiceServer server;

    private static final String WEATHER_JSON = """
            {
                "latitude": -34.61,
                "longitude": -58.38,
                "generationtime_ms": 0.049,
                "utc_offset_seconds": 0,
                "timezone": "GMT",
                "timezone_abbreviation": "GMT",
                "elevation": 25.0,
                "current_weather": {
                    "temperature": 22.5,
                    "windspeed": 15.2,
                    "winddirection": 270.0,
                    "weathercode": 2,
                    "is_day": 1,
                    "time": "2025-01-15T14:00"
                }
            }
            """;

    @Test
    void getWeather_callsOpenMeteoAndReturnsResponse() {
        server.expect(requestTo(
                        "https://api.open-meteo.com/v1/forecast?latitude=-34.61&longitude=-58.38&current_weather=true"))
                .andRespond(withSuccess(WEATHER_JSON, MediaType.APPLICATION_JSON));

        WeatherResponse response = weatherService.getWeather(-34.61, -58.38);

        assertThat(response.latitude()).isEqualTo(-34.61);
        assertThat(response.longitude()).isEqualTo(-58.38);
        assertThat(response.timezone()).isEqualTo("GMT");
        assertThat(response.elevation()).isEqualTo(25.0);
        assertThat(response.currentWeather().temperature()).isEqualTo(22.5);
        assertThat(response.currentWeather().windSpeed()).isEqualTo(15.2);
        assertThat(response.currentWeather().windDirection()).isEqualTo(270.0);
        assertThat(response.currentWeather().weatherCode()).isEqualTo(2);
        assertThat(response.currentWeather().isDay()).isEqualTo(1);
        server.verify();
    }

    @Test
    void getWeather_withDifferentCoordinates_sendsCorrectRequest() {
        server.expect(requestTo(
                        "https://api.open-meteo.com/v1/forecast?latitude=40.71&longitude=-74.01&current_weather=true"))
                .andRespond(withSuccess("""
                        {
                            "latitude": 40.71,
                            "longitude": -74.01,
                            "generationtime_ms": 0.03,
                            "utc_offset_seconds": 0,
                            "timezone": "GMT",
                            "timezone_abbreviation": "GMT",
                            "elevation": 10.0,
                            "current_weather": {
                                "temperature": 5.0,
                                "windspeed": 20.0,
                                "winddirection": 90.0,
                                "weathercode": 61,
                                "is_day": 0,
                                "time": "2025-01-15T22:00"
                            }
                        }
                        """, MediaType.APPLICATION_JSON));

        WeatherResponse response = weatherService.getWeather(40.71, -74.01);

        assertThat(response.latitude()).isEqualTo(40.71);
        assertThat(response.currentWeather().temperature()).isEqualTo(5.0);
        assertThat(response.currentWeather().isDay()).isEqualTo(0);
        server.verify();
    }
}
