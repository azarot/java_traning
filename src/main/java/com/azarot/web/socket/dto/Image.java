package com.azarot.web.socket.dto;

import java.net.URL;

public class Image {
    private final URL url;
    private long size;

    public Image(URL url) {
        this.url = url;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public URL getUrl() {
        return url;
    }
}
