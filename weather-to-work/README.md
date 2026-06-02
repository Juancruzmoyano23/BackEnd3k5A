# Weather API

A Spring Boot REST API that provides current weather data and geocoding for any location worldwide, using [Open-Meteo](https://open-meteo.com/) as the external data source.

## External API: Open-Meteo

This project uses two Open-Meteo APIs:
- **Forecast API** — current weather conditions by coordinates
- **Geocoding API** — search locations by name to get coordinates

### Why Open-Meteo?

- **Free and open-source** - no API key or registration required
- **No rate limits** for non-commercial use
- **Global coverage** - weather data for any latitude/longitude worldwide
- **High accuracy** - combines multiple national weather services

### API Details

| API        | Base URL                                                  | Documentation                          |
|------------|-----------------------------------------------------------|----------------------------------------|
| Forecast   | `https://api.open-meteo.com/v1/forecast`                  | https://open-meteo.com/en/docs         |
| Geocoding  | `https://geocoding-api.open-meteo.com/v1/search`          | https://open-meteo.com/en/docs/geocoding-api |

Both APIs require **no authentication** and allow **10,000 requests/day** for non-commercial use.

### How it works

**Step 1:** Search for a location by name using the Geocoding API:

```
GET https://geocoding-api.open-meteo.com/v1/search?name=Buenos%20Aires&count=5
```

Response:

```json
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
```

**Step 2:** Use the coordinates to get weather data from the Forecast API:

```
GET https://api.open-meteo.com/v1/forecast?latitude=-34.61&longitude=-58.38&current_weather=true
```

Open-Meteo responds with:

```json
{
  "latitude": -34.61,
  "longitude": -58.38,
  "elevation": 25.0,
  "timezone": "GMT",
  "current_weather": {
    "temperature": 22.5,
    "windspeed": 15.2,
    "winddirection": 270.0,
    "weathercode": 2,
    "is_day": 1,
    "time": "2025-01-15T14:00"
  }
}
```

### Weather Codes Reference

| Code | Description          |
|------|----------------------|
| 0    | Clear sky            |
| 1-3  | Partly cloudy        |
| 45   | Fog                  |
| 51-55| Drizzle              |
| 61-65| Rain                 |
| 71-75| Snowfall             |
| 80-82| Rain showers         |
| 95   | Thunderstorm         |

Full list: https://open-meteo.com/en/docs#weathervariables

## Requirements

- Java 17+
- Maven

## Running the application

```bash
mvn spring-boot:run
```

The server starts on `http://localhost:8080`.

## API Endpoints

### Search locations

```
GET /api/locations?name={query}&count={max_results}
```

**Parameters:**

| Parameter | Type   | Required | Default | Description                        |
|-----------|--------|----------|---------|------------------------------------|
| `name`    | string | Yes      |         | City, town, region or country name |
| `count`   | int    | No       | 5       | Maximum number of results (1-20)   |

**Example request:**

```bash
curl "http://localhost:8080/api/locations?name=Cordoba"
```

**Example response:**

```json
{
  "results": [
    {
      "id": 3860259,
      "name": "Córdoba",
      "latitude": -31.4135,
      "longitude": -64.18105,
      "elevation": 395.0,
      "country_code": "AR",
      "country": "Argentina",
      "admin1": "Cordoba",
      "timezone": "America/Argentina/Cordoba",
      "population": 1317298
    }
  ]
}
```

### Get current weather

```
GET /api/weather?latitude={lat}&longitude={lon}
```

**Parameters:**

| Parameter   | Type   | Required | Description                     |
|-------------|--------|----------|---------------------------------|
| `latitude`  | double | Yes      | Latitude (-90 to 90)            |
| `longitude` | double | Yes      | Longitude (-180 to 180)         |

**Example request:**

```bash
curl "http://localhost:8080/api/weather?latitude=-34.61&longitude=-58.38"
```

**Example response:**

```json
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
```

### Get weather by location name

```
GET /api/weather/location?name={query}
```

Combines both APIs in a single call: resolves the location name to coordinates, then fetches the current weather.

**Parameters:**

| Parameter | Type   | Required | Description                        |
|-----------|--------|----------|------------------------------------|
| `name`    | string | Yes      | City, town, region or country name |

**Example request:**

```bash
curl "http://localhost:8080/api/weather/location?name=Buenos%20Aires"
```

**Example response:**

```json
{
  "name": "Buenos Aires",
  "country": "Argentina",
  "latitude": -34.61,
  "longitude": -58.38,
  "currentWeather": {
    "temperature": 22.5,
    "windspeed": 15.2,
    "winddirection": 270.0,
    "weathercode": 2,
    "is_day": 1,
    "time": "2025-01-15T14:00"
  }
}
```

Returns **404** if the location name cannot be found.

## Running tests

```bash
mvn test
```

## Project Structure

```
src/main/java/org/example/weather/
├── WeatherApplication.java              # Spring Boot entry point
├── controller/
│   ├── WeatherController.java           # Weather by coordinates
│   ├── GeocodingController.java         # Location search
│   └── LocationWeatherController.java   # Weather by location name
├── service/
│   ├── WeatherService.java              # Calls Open-Meteo Forecast API
│   ├── GeocodingService.java            # Calls Open-Meteo Geocoding API
│   └── LocationWeatherService.java      # Orchestrates both APIs
└── dto/
    ├── WeatherResponse.java             # Weather response mapping
    ├── GeocodingResponse.java           # Geocoding response mapping
    └── LocationWeatherResponse.java     # Combined response mapping
```
