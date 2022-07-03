package com.azarot.web.socket;

import com.azarot.web.socket.dto.Image;

import java.net.Socket;
import java.net.URL;
import java.util.List;

import static com.azarot.web.socket.SocketUtils.*;

public class NasaImageClient {
    public static void main(String[] args) {
        var nasaImagesPath = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=16&api_key=DEMO_KEY";
        var largestImage = findLargestImage(castToUrl(nasaImagesPath));

        printResult(largestImage);
    }

    public static Image findLargestImage(URL url) {
        Socket socket = openSslSocket(url);
        List<Image> images = getImages(socket, url);
        return findLargestImage(images);
    }

    private static void printResult(Image largestImage) {
        System.out.println("URL: " + largestImage.getUrl());
        System.out.println("Size: " + largestImage.getSize());
    }

    private static Image findLargestImage(List<Image> images) {
        return null;
    }

    private static List<Image> getImages(Socket socket, URL url) {
        String response = doGetRequest(socket, HttpContentType.BODY, url);
        System.out.println(response);
        //TODO: finish implementing this method
        return null;
    }

}
