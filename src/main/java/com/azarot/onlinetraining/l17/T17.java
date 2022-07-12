package com.azarot.onlinetraining.l17;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class T17 {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        String body = getBody();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> stringHttpEntity = new HttpEntity<>(body, httpHeaders);
        JsonNode jsonNode = restTemplate.postForObject("http://93.175.204.87:8080/training/stats", stringHttpEntity, JsonNode.class);

    }

    private static String getBody() {
        return "{\n" +
                "  \"firstName\": \"Andrii\",\n" +
                "  \"lastName\": \"Cherepovskyi\",\n" +
                "  \"team\": \"Svydovets\",\n" +
                "  \"trainingDaysPerWeek\": 5,\n" +
                "  \"minutesPerTrainingDay\": 60\n" +
                "}";
    }
}
