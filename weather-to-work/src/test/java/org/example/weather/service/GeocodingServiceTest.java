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

@RestClientTest(GeocodingService.class)
@TestPropertySource(properties = {
        "geocoding.api.base-url=https://geocoding-api.open-meteo.com/v1/search",
        "weather.api.base-url=https://api.open-meteo.com/v1/forecast"
})
class GeocodingServiceTest {

    @Autowired
    private GeocodingService geocodingService;

    @Autowired
    private MockRestServiceServer server;

    private static final String GEOCODING_JSON = """
            {
                "results": [
                    {
                        "id": 3435910,
                        "name": "Buenos Aires",
                        "latitude": -34.61315,
                        "longitude": -58.37723,
                        "elevation": 25.0,
                        "country_code": "AR",
                        "country": "Argentina",
                        "admin1": "Buenos Aires",
                        "timezone": "America/Argentina/Buenos_Aires",
                        "population": 13076300
                    }
                ]
            }
            """;

    @Test
    void search_callsGeocodingApiAndReturnsLocations() {
        server.expect(requestTo(
                        "https://geocoding-api.open-meteo.com/v1/search?name=Buenos%20Aires&count=5"))
                .andRespond(withSuccess(GEOCODING_JSON, MediaType.APPLICATION_JSON));

        GeocodingResponse response = geocodingService.search("Buenos Aires", 5);

        assertThat(response.results()).hasSize(1);
        assertThat(response.results().get(0).name()).isEqualTo("Buenos Aires");
        assertThat(response.results().get(0).latitude()).isEqualTo(-34.61315);
        assertThat(response.results().get(0).longitude()).isEqualTo(-58.37723);
        assertThat(response.results().get(0).country()).isEqualTo("Argentina");
        assertThat(response.results().get(0).countryCode()).isEqualTo("AR");
        server.verify();
    }

    @Test
    void search_withDifferentParams_sendsCorrectRequest() {
        server.expect(requestTo(
                        "https://geocoding-api.open-meteo.com/v1/search?name=Tokyo&count=3"))
                .andRespond(withSuccess("""
                        {
                            "results": [
                                {
                                    "id": 1850147,
                                    "name": "Tokyo",
                                    "latitude": 35.6895,
                                    "longitude": 139.69171,
                                    "elevation": 40.0,
                                    "country_code": "JP",
                                    "country": "Japan",
                                    "admin1": "Tokyo",
                                    "timezone": "Asia/Tokyo",
                                    "population": 8336599
                                }
                            ]
                        }
                        """, MediaType.APPLICATION_JSON));

        GeocodingResponse response = geocodingService.search("Tokyo", 3);

        assertThat(response.results().get(0).name()).isEqualTo("Tokyo");
        assertThat(response.results().get(0).country()).isEqualTo("Japan");
        server.verify();
    }
}