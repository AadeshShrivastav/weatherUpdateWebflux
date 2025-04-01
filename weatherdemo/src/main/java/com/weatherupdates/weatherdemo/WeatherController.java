package com.weatherupdates.weatherdemo;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/weather")
public class WeatherController {
    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    // ✅ API Endpoint: Streams real-time weather updates
//    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
//    public Flux<WeatherUpdate> streamWeather(@RequestParam String city) {
//        Flux<WeatherUpdate> weatherStream = weatherService.getWeatherStream(city);
//
//        // ✅ Subscribe inside the controller to trigger consumption
//        weatherService.consumeWeatherStream(weatherStream);
//
//        return weatherStream;
//    }

    // ✅ API Endpoint: Streams with backpressure
//    @GetMapping("/backpressure")
//    public String streamWithBackpressure(@RequestParam String city) {
//        Flux<WeatherUpdate> weatherStream = weatherService.getWeatherStream(city);
//        weatherService.subscribeWithBackpressure(weatherStream);
//        return "Backpressure applied. Check logs for output!";
//    }

    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<WeatherUpdate> streamWeather(@RequestParam String city) {
        return weatherService.getWeatherStream(city);
    }
}
