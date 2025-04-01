package com.weatherupdates.weatherdemo;

import org.reactivestreams.Subscription;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class WeatherService {
//    private final Random random = new Random();
//    private final List<String> conditions = List.of("Sunny", "Cloudy", "Rainy", "Stormy", "Windy");

    private final WebClient webClient;

    public WeatherService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://api.openweathermap.org/data/2.5").build();
    }

    public Flux<WeatherUpdate> getWeatherStream(String city) {
        return Flux.interval(Duration.ofSeconds(5)) // Fetch new data every 5 seconds
                .flatMap(i -> fetchWeather(city));
    }

    private Mono<WeatherUpdate> fetchWeather(String city) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/weather")
                        .queryParam("q", city)
                        .queryParam("appid", "c5654e3154a3d3728bb9e023da2e578b") // Replace with actual API key
                        .queryParam("units", "metric") // Get temperature in Celsius
                        .build())
                .retrieve()
                .bodyToMono(OpenWeatherResponse.class)
                .map(this::convertToWeatherUpdate);
    }

    private WeatherUpdate convertToWeatherUpdate(OpenWeatherResponse response) {
        return new WeatherUpdate(
                response.getId(),
                response.getName(),
                response.getMain().getTemp(),
                response.getWeather().get(0).getDescription(),
                Instant.now()
        );
    }


    // ✅ Produces a continuous stream of weather updates
//    public Flux<WeatherUpdate> getWeatherStream(String city) {
//        return Flux.interval(Duration.ofSeconds(1))
//                .map(i -> generateWeatherUpdate(city))
//                .filter(update -> update.getCity().equalsIgnoreCase(city))
//                .doOnNext(update -> System.out.println("Producer emitted: " + update));
//    }
//
//    // ✅ Consumer: Handles the subscription process
//    public void consumeWeatherStream(Flux<WeatherUpdate> weatherStream) {
//        weatherStream.subscribe(
//                weather -> System.out.println("Consumer received: " + weather),
//                error -> System.err.println("Error occurred: " + error),
//                () -> System.out.println("Stream Completed")
//        );
//    }
//
//    // ✅ Handles Backpressure: Limits the number of items requested
//    public void subscribeWithBackpressure(Flux<WeatherUpdate> weatherStream) {
//        weatherStream.subscribe(new org.reactivestreams.Subscriber<>() {
//            private Subscription subscription;
//            private int count = 0;
//            private final int MAX_COUNT = 5; // Consume only 5 items
//
//            @Override
//            public void onSubscribe(Subscription subscription) {
//                this.subscription = subscription;
//                subscription.request(1); // Request 1 item at a time
//            }
//
//            @Override
//            public void onNext(WeatherUpdate weatherUpdate) {
//                System.out.println("Received with backpressure: " + weatherUpdate);
//                count++;
//                if (count < MAX_COUNT) {
//                    subscription.request(1); // Request next item
//                } else {
//                    subscription.cancel(); // Stop after 5 items
//                }
//            }
//
//            @Override
//            public void onError(Throwable t) {
//                System.err.println("Error occurred: " + t);
//            }
//
//            @Override
//            public void onComplete() {
//                System.out.println("Stream completed");
//            }
//        });
//    }

    // ✅ Generates random weather updates
//    private WeatherUpdate generateWeatherUpdate(String city) {
//        return new WeatherUpdate(
//                UUID.randomUUID().toString(),
//                city,
//                15 + random.nextDouble() * 10,
//                conditions.get(random.nextInt(conditions.size())),
//                Instant.now()
//        );
//    }
}
