package com.example.scheduling.main.jobs;

import com.example.scheduling.main.ScheduleType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.structure.DTO.source.BaseDTO;
import org.example.structure.DTO.source.ConfigFileDTO;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.UUID;

@DisallowConcurrentExecution
public abstract class InvokeSourceJob implements Job {
    @Value("${core.api}")
    private String coreApiUrl;

    @Value("${config.core.path}")
    private String configPath;

    private final ObjectMapper mapper = new ObjectMapper();


    protected BaseDTO readDTOFromFile(UUID uuid, ScheduleType type) {
        ConfigFileDTO fileDTO = null;
        try {
            fileDTO =  mapper.readValue(new File(configPath + "\\"+ type.toString() +"\\" + uuid.toString()), ConfigFileDTO.class);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("There is no file with this id:" + uuid.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fileDTO;
    }
    protected void sendRequest(BaseDTO dto, String extension){
        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest httpRequest = null;
        try {
            httpRequest = HttpRequest.newBuilder()
                    .uri(new URL("http://" + coreApiUrl + "/config/" + extension.toLowerCase() + "/csv").toURI())
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(dto)))
                    .build();
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
