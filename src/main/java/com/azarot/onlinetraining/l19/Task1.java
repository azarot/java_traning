package com.azarot.onlinetraining.l19;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;


public class Task1 {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("93.175.204.87", 8080));
        var out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        out.write("POST /reactive/persons HTTP/1.1\n");
        out.write("Host: 93.175.204.87:8080\n");
        out.write("Content-Type: application/json\n");
        out.write("Content-Length: application/json\n");
        out.write("\n");
        out.write("\n\n");


//
//                Content-Length: 100
//
//                {
//                  "firstName": "Andrii",
//                  "lastName": "Cherepovskyi",
//                  "reactiveProgrammingLevel": 3,
//                  "hasSpringWebFluxExperience": false
//                }
//
//                """);
        out.flush();

        new BufferedReader(new InputStreamReader(socket.getInputStream())).lines().forEach(System.out::println);


    }
}
