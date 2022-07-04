package com.azarot.onlinetraining.l15;

import com.azarot.onlinetraining.l15.dto.Person;
import com.azarot.onlinetraining.l15.dto.PersonWithTeam;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class Task1 {
    public static void main(String[] args) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String participantsUrl = "http://93.175.204.87:8080/participants";
        var me = new PersonWithTeam("Andrii", "Cherepovskyi", "Svydovets", 100);
        String s = new ObjectMapper().writeValueAsString(me);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity =
                new HttpEntity<String>(s, headers);
        ResponseEntity<Object> response = restTemplate.postForEntity(participantsUrl, entity, null);
        System.out.println("Finished with: " + response.getStatusCode());
    }
}
