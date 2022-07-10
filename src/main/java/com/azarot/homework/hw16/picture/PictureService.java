package com.azarot.homework.hw16.picture;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PictureService {

    private final RestTemplate restTemplate;

    private static final String IMAGES_URL =
            "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=%d&api_key=DEMO_KEY";

    public PictureService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getLargestPicture(int sol) {
        var url = String.format(IMAGES_URL, sol);
        var response = restTemplate.getForObject(url, JsonNode.class);

        long maxSize = 0;
        String largestPicture = null;

        assert response != null;
        for (var picture : response.get("photos")) {
            var pictureUrl = picture.get("img_src").textValue();
            var pictureEntry = restTemplate.getForEntity(pictureUrl, byte[].class);
            long size = pictureEntry.getHeaders().getContentLength();
            if (size > maxSize) {
                maxSize = size;
                largestPicture = pictureUrl;
            }
        }
        return largestPicture;
    }
}
