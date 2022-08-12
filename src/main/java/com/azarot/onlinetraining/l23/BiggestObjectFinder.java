package com.azarot.onlinetraining.l23;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.StreamSupport;

public class BiggestObjectFinder {

    private final static RestTemplate template = new RestTemplate();

    public static void main(String[] args) {
        findBiggestObjectForToday();
    }

    private static void findBiggestObjectForToday() {
        String baseUrl = "https://api.nasa.gov/neo/rest/v1/feed";
        String apiKey = "DEMO_KEY";
        String today = "2022-08-08";
        var uri = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .queryParam("api_key", apiKey)
                .queryParam("start_date", today)
                .toUriString();

        JsonNode response = template.getForObject(uri, JsonNode.class);


        JsonNode objects = response.get("near_earth_objects").get("2022-08-12");


        StreamSupport.stream(objects.spliterator(), false)
                .max(Comparator.comparing(json -> json.get("estimated_diameter")
                        .get("meters")
                        .get("estimated_diameter_max")
                        .asDouble())
                )
                .map(json -> json.get("name").textValue())
                .ifPresent(System.out::println);

    }
}
