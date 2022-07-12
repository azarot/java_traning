package com.azarot.onlinetraining.l17;

import com.azarot.onlinetraining.l17.dto.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeamController {

    @GetMapping("/people/max")
    public User getMax() {

    }
}
