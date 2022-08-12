package com.azarot.homework.hw17.controller;

import com.azarot.homework.hw17.service.NasaPicturesService;
import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

import java.net.URI;

import static org.springframework.http.HttpStatus.PERMANENT_REDIRECT;

@RestController
@RequestMapping("pictures")
public class NasaPicturesController {

    private final NasaPicturesService nasaPicturesService;

    public NasaPicturesController(NasaPicturesService nasaPicturesService) {
        this.nasaPicturesService = nasaPicturesService;
    }

    @GetMapping("/{sol}/largest")
    @SneakyThrows
    public ResponseEntity<Object> getLargestPicture(@PathVariable int sol) {
        var redirectUrl = nasaPicturesService.getLargestPicture(sol);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(new URI(redirectUrl.get()));
        return new ResponseEntity<>(httpHeaders, HttpStatus.FOUND);
    }
}
