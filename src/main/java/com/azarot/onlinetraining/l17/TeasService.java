package com.azarot.onlinetraining.l17;

import com.azarot.onlinetraining.l17.dto.User;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class TeasService {

    public User getMaxUser() {
        RestTemplate restTemplate = new RestTemplate();
        JsonNode response = restTemplate.getForObject("http://93.175.204.87:8080/training/stats", JsonNode.class);
//        List<Integer> re
//        for (var teams : response.get("team")) {
//
//        }
        return null;
    }
}
