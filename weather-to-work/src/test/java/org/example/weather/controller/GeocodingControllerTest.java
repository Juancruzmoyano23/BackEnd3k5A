package org.example.weather.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GeocodingController.class)
class GeocodingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GeocodingService geocodingService;

    @Test
    void searchLocations_returnsResults() throws Exception {
        var location = new GeocodingResponse.Location(
                3435910, "Buenos Aires", -34.61, -58.38, 25.0,
                "AR", "Argentina", "Buenos Aires", "America/Argentina/Buenos_Aires", 13076300
        );
        when(geocodingService.search("Buenos Aires", 5))
                .thenReturn(new GeocodingResponse(List.of(location)));

        mockMvc.perform(get("/api/locations")
                        .param("name", "Buenos Aires"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.results[0].name").value("Buenos Aires"))
                .andExpect(jsonPath("$.results[0].latitude").value(-34.61))
                .andExpect(jsonPath("$.results[0].country").value("Argentina"));
    }

    @Test
    void searchLocations_withCustomCount_passesCount() throws Exception {
        when(geocodingService.search("London", 2))
                .thenReturn(new GeocodingResponse(List.of()));

        mockMvc.perform(get("/api/locations")
                        .param("name", "London")
                        .param("count", "2"))
                .andExpect(status().isOk());
    }

    @Test
    void searchLocations_missingName_returnsBadRequest() throws Exception {
        mockMvc.perform(get("/api/locations"))
                .andExpect(status().isBadRequest());
    }
}