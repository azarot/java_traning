package com.azarot.onlinetraining.l16.task2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class UserRequest {
    public static void main(String[] args) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        User user = new User("Dart", "Veider");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        while (true) {
            HttpEntity<String> requestEntity = new HttpEntity<>(new ObjectMapper().writeValueAsString(user), headers);
            restTemplate.postForObject("http://93.175.204.87:8080/users", requestEntity, JsonNode.class);
        }
    }
}

record User(String firstName, String lastName){

}
