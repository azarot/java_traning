package com.azarot.onlinetraining.l15;

import com.azarot.onlinetraining.l15.dto.Person;
import com.azarot.onlinetraining.l15.dto.Team;
import com.azarot.onlinetraining.l15.dto.Teams;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

public class Task2 {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();

        Teams teams = restTemplate.getForObject("http://93.175.204.87:8080/participants", Teams.class);
        Set<Person> persons = Arrays.stream(teams.teams())
                .map(Team::teamMembers)
                .flatMap(Arrays::stream)
                .collect(Collectors.toSet());

        Optional<Person> personWithMaxMotivation = persons.stream().max(Comparator.comparing(Person::motivation));
        Optional<Person> personWithMinMotivation = persons.stream().min(Comparator.comparing(Person::motivation));

    }
}
