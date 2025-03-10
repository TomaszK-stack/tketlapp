package com.example.tketl.api.controllers.onetime;

import com.example.tketl.DTO.source.ConfigHttpDTO;
import com.example.tketl.exceptions.InvalidColumnNameException;
import com.example.tketl.exceptions.StepNotFoundException;
import com.example.tketl.processing.OutputFromStep;
import com.example.tketl.reading.sources.http.HTTPSource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("config")
public class ReadingHttpController {
    public HTTPSource httpSource;

    public ReadingHttpController(HTTPSource httpSource) {
        this.httpSource = httpSource;
    }

    @PostMapping("/http")
    public ResponseEntity<List<OutputFromStep>> sqlServerGetData(@RequestBody ConfigHttpDTO configDTO) throws ClassNotFoundException, StepNotFoundException, InvalidColumnNameException, IOException, SQLException {
        return ResponseEntity.ok(httpSource.read(configDTO));
    }
}