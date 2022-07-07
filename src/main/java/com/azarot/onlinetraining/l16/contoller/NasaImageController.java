package com.azarot.onlinetraining.l16.contoller;

import com.azarot.onlinetraining.l16.service.NasaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController()
public class NasaImageController {
    private final NasaService nasaService;

    public NasaImageController(NasaService nasaService) {
        this.nasaService = nasaService;
    }

    @GetMapping("/pictures/{sol}/largest")
    public void findLargestImage(@PathVariable(value="sol") Integer sol, HttpServletResponse httpServletResponse) {
        String largestImage = nasaService.findLargestImage(sol);
        httpServletResponse.setStatus(302);
        httpServletResponse.setHeader("Location", largestImage);

    }
}
