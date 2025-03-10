package com.example.tk_etlproc.Converting;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.opencsv.CSVWriter;
import org.junit.Test;

import java.io.*;
import java.util.Iterator;
import java.util.Map;

public class ConvertTest {

    @Test
    public void testConvertJsonToCSV() throws IOException {
        String s = "";
        JsonNode jsonTree = new ObjectMapper().readTree(new File("test.json"));

        // Tworzenie schematu CSV z nagłówkami na podstawie pól w pierwszym obiekcie
        CsvSchema.Builder csvSchemaBuilder = CsvSchema.builder();
        JsonNode firstObject = jsonTree.elements().next();
        firstObject.fieldNames().forEachRemaining(fieldName -> {
            csvSchemaBuilder.addColumn(fieldName);
        });
        CsvSchema csvSchema = csvSchemaBuilder.build().withHeader();

        // Tworzenie CsvMapper do konwersji JSON na CSV
        CsvMapper csvMapper = new CsvMapper();

        // Zapisz wynik do Stringa
        s = csvMapper.writerFor(JsonNode.class)
                .with(csvSchema)
                .writeValueAsString(jsonTree);  // Przekazujemy jsonTree jako dane do konwersji

        // Wypisz wynikowy CSV
        StringBuilder stringBuilder = new StringBuilder(s);
        System.out.println(stringBuilder);

    }
}
