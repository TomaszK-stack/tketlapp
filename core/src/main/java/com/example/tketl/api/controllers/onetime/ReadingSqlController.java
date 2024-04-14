package com.example.tketl.api.controllers.onetime;

import com.example.tketl.api.DTO.source.ConfigDatabaseDTO;
import com.example.tketl.exceptions.InvalidColumnNameException;
import com.example.tketl.exceptions.StepNotFoundException;
import com.example.tketl.processing.OutputFromStep;
import com.example.tketl.reading.sources.sql.SqlSourceDefault;
import com.example.tketl.reading.sources.sql.SqlType;
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
public class ReadingSqlController {

     public SqlSourceDefault SqlSourceDefault;

    public ReadingSqlController(SqlSourceDefault SqlSourceDefault) {
        this.SqlSourceDefault = SqlSourceDefault;
    }

    @PostMapping("/sql")
    public ResponseEntity<List<OutputFromStep>> sqlServerGetData(@RequestBody ConfigDatabaseDTO configDTO) throws ClassNotFoundException, SQLException, StepNotFoundException, InvalidColumnNameException, IOException {
        return ResponseEntity.ok(SqlSourceDefault.read(configDTO, SqlType.SQL_SERVER));
    }
}
