package com.example.tketl.reading.sources.http;

import com.example.tketl.DTO.source.ConfigHttpDTO;
import com.example.tketl.DTO.source.FileType;
import com.example.tketl.archive.manager.ArchiveManager;
import com.example.tketl.exceptions.InvaildFormatException;
import com.example.tketl.exceptions.StepNotFoundException;
import com.example.tketl.processing.OutputFromStep;
import com.example.tketl.reading.InputHandler;
import com.example.tketl.reading.sources.Source;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.SQLException;
import java.util.List;

@Service
public class HTTPSource implements Source {

    private InputHandler inputHandler;

    private ArchiveManager archiveManager;
    @Autowired
    public HTTPSource(InputHandler inputHandler, ArchiveManager archiveManager) {
        this.inputHandler = inputHandler;
        this.archiveManager = archiveManager;
    }

    public List<OutputFromStep> read(ConfigHttpDTO configHttpDTO) throws StepNotFoundException, SQLException, IOException, ClassNotFoundException {
        archiveManager.saveArchive(configHttpDTO);
        StringBuilder output = null;

        try {
            output = sendGetRequest(configHttpDTO.getUrl(), configHttpDTO.getType());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (InvaildFormatException e) {
            throw new RuntimeException(e);
        }


        return inputHandler.handle_data(output, configHttpDTO.getDelimiter() != null ? configHttpDTO.getDelimiter() : ",", true, configHttpDTO);

    }

        public  StringBuilder sendGetRequest (String url, FileType type) throws IOException, InterruptedException, InvaildFormatException {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());


            return prepareDataFromRequest(response.body(), type);
        }

        public StringBuilder prepareDataFromRequest(String body, FileType type) throws JsonProcessingException, InvaildFormatException {
            switch (type){
                case JSON:
                    String s = "";
                    JsonNode jsonTree = new ObjectMapper().readTree(body);
                    CsvSchema.Builder csvSchemaBuilder = CsvSchema.builder();
                    JsonNode firstObject = jsonTree.elements().next();
                    firstObject.fieldNames().forEachRemaining(fieldName -> {
                        csvSchemaBuilder.addColumn(fieldName);
                    });
                    CsvSchema csvSchema = csvSchemaBuilder.build().withHeader();

                    CsvMapper csvMapper = new CsvMapper();
                    s = csvMapper.writerFor(JsonNode.class)
                            .with(csvSchema)
                            .writeValueAsString(jsonTree);

                    StringBuilder stringBuilder = new StringBuilder(s);
                    return stringBuilder;
                case CSV:
                    return new StringBuilder(body);
                default:
                    throw new InvaildFormatException("You put invalid format");
            }

        }

    }


