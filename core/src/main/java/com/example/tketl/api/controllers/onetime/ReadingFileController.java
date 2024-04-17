package com.example.tketl.api.controllers.onetime;

import org.example.structure.DTO.source.ConfigFileDTO;
import com.example.tketl.exceptions.InvalidColumnNameException;
import com.example.tketl.exceptions.StepNotFoundException;
import com.example.tketl.processing.OutputFromStep;
import com.example.tketl.reading.sources.file.FileCSVSource;
import com.example.tketl.reading.sources.file.FileXLSXSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


@RestController
@RequestMapping("config/file")
public class ReadingFileController {
    private FileCSVSource fileCSVSource;

    private FileXLSXSource fileXLSXSource;


    public ReadingFileController(FileCSVSource fileCSVSource, FileXLSXSource fileXLSXSource) {
        this.fileCSVSource = fileCSVSource;
        this.fileXLSXSource = fileXLSXSource;

    }



    @PostMapping("/csv")
    public ResponseEntity<List<OutputFromStep>> fileCSVConfig(@RequestBody ConfigFileDTO configDTO) throws IOException, StepNotFoundException, SQLException, ClassNotFoundException, InvalidColumnNameException {
        return ResponseEntity.ok(fileCSVSource.read(configDTO));
    }
    @PostMapping("/xlsx")
    public ResponseEntity<List<OutputFromStep>> fileXLSXConfig(@RequestBody ConfigFileDTO configDTO) throws IOException, StepNotFoundException, SQLException, ClassNotFoundException, InvalidColumnNameException {

        return ResponseEntity.ok(fileXLSXSource.read(configDTO));
    }


}
