package com.example.tk_etlproc.HTTP;


import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@SpringBootTest
public class HttpConnectionTests {

    @Test
    public void testHttpConnection() throws IOException, InterruptedException {
        String url = "https://drive.google.com/uc?id=1IXQDp8Um3d-o7ysZLxkDyuvFj9gtlxqz&amp;export=download";
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());


    }

}
