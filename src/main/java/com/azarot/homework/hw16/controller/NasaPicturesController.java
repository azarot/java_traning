package com.azarot.homework.hw16.controller;

import com.azarot.homework.hw16.picture.PictureService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("pictures")
public class NasaPicturesController {

    private final PictureService pictureService;

    public NasaPicturesController(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    @GetMapping("/{sol}/largest")
    public ResponseEntity<Object> getLargestPicture(@PathVariable int sol) throws URISyntaxException {
        var redirectUrl = pictureService.getLargestPicture(sol);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(new URI(redirectUrl));
        return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);

    }
}
