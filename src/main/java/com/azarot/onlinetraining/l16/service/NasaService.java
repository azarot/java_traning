package com.azarot.onlinetraining.l16.service;

import com.azarot.onlinetraining.l16.dto.Image;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.net.http.HttpClient;

@Service
public class NasaService {
    private static final String imageUrl = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=%s&api_key=DEMO_KEY";
    private static final RestTemplate restTemplate = new RestTemplate();

    @PostConstruct
    public void config() {
        final HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        final CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setRedirectStrategy(new LaxRedirectStrategy())
                .build();
        factory.setHttpClient(httpClient);
        restTemplate.setRequestFactory(factory);
    }

    public String findLargestImage(int sol) {
        long largestImgSize = 0;
        String largestImg  = null;
        JsonNode response = restTemplate.getForObject(String.format(imageUrl, sol), JsonNode.class);
        for (JsonNode p : response.get("photos")) {
            String imgUrl = p.get("img_src").textValue();
            ResponseEntity<Object> img = restTemplate.getForEntity(imgUrl, null);

            long imgSize = img.getHeaders().getContentLength();
            if (imgSize > largestImgSize) {
                largestImg = imgUrl;
                largestImgSize = imgSize;
            }

        }

        return largestImg;
    }
}
