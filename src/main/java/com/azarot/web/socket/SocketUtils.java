package com.azarot.web.socket;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.security.NoSuchAlgorithmException;

public class SocketUtils {

    private static final String GET_REQUEST_TEMPLATE = """
            GET %s HTTP/1.1
            Host: %s
            Connection: close

            """;
    public static Socket openSslSocket(URL url)  {
        try {
            SSLSocketFactory socketFactory = SSLContext.getDefault().getSocketFactory();
            SSLSocket socket = (SSLSocket) socketFactory.createSocket(url.getHost(), getPort(url));
            socket.startHandshake();

            return socket;
        } catch (NoSuchAlgorithmException | IOException e) {
            throw new RuntimeException("Can't open SSL socket", e);
        }
    }

    private static int getPort(URL url) {
        int port = url.getPort();
        return port == -1 ? url.getDefaultPort(): port;
    }

    public static URL castToUrl(String path) {
        try {
            return new URL(path);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Can't create url from string: " + path);
        }
    }

    public static String doGetRequest(Socket socket, HttpContentType type, URL url) {

        OutputStream outputStream = null;
        InputStream inputStream = null;
        try {
            outputStream = socket.getOutputStream();
            inputStream = socket.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        writeGetRequest(outputStream, url);

        try (var reader = new BufferedReader(new InputStreamReader(inputStream))){
            return type == HttpContentType.BODY ? readBody(reader) : readHeader(reader);
        } catch (IOException e) {
            throw new IllegalStateException("Can't read from socket", e);
        }
    }

    private static String readBody(BufferedReader reader) throws IOException {
        String line;
        var jsonBuilder = new StringBuilder();

        while ((line = reader.readLine()) != null) {
            if (line.contains("{") || line.contains("}")) {
                jsonBuilder.append(line);
            }
        }
        return jsonBuilder.toString();
    }

    private static String readHeader(BufferedReader reader) throws IOException {
        String line;
        var jsonBuilder = new StringBuilder();

        while ((line = reader.readLine()) != null) {
            if (line.isEmpty()) {
                jsonBuilder.append(line);
            }
        }
        return jsonBuilder.toString();
    }

    private static void writeGetRequest(OutputStream outputStream, URL url) {
        var out = new PrintWriter(outputStream, true);
        out.println(constructGetRequest(url));
    }

    private static String constructGetRequest(URL url) {
        return String.format(GET_REQUEST_TEMPLATE, url.toString(), url.getHost());
    }
}
