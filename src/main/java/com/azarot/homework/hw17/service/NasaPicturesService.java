package com.azarot.homework.hw17.service;

import ch.qos.logback.core.util.TimeUtil;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class NasaPicturesService {

    private final RestTemplate restTemplate;

    private static final String NASA_PICTURES_TEMPLATE_URL =
            "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=%d&api_key=DEMO_KEY";

    public NasaPicturesService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Cacheable("largestPicture")
    public Optional<String> getLargestPicture(int sol) {
        var nasaPicturesUrl = String.format(NASA_PICTURES_TEMPLATE_URL, sol);
        var forObject = restTemplate.getForObject(nasaPicturesUrl, JsonNode.class);

        return forObject.get("photos")
                .findValuesAsText("img_src")
                .parallelStream()
                .map(pictureUrl -> {
                    var response = restTemplate.getForEntity(pictureUrl, null);
                    return new PictureInfo(pictureUrl, response.getHeaders().getContentLength());
                })
                .max(Comparator.comparing(PictureInfo::size))
                .map(PictureInfo::url);
    }

    @Scheduled(fixedDelay =  24 * 60 * 60 * 1000)
    @CacheEvict("largestPicture")
    public void evictCache() {
    }
}

record PictureInfo(String url, long size) {

}
