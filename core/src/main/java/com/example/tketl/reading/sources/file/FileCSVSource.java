package com.example.tketl.reading.sources.file;

import org.example.structure.DTO.source.ConfigFileDTO;
import com.example.tketl.archive.manager.ArchiveManager;
import com.example.tketl.exceptions.InvalidColumnNameException;
import com.example.tketl.exceptions.StepNotFoundException;
import com.example.tketl.processing.OutputFromStep;
import com.example.tketl.reading.InputHandler;
import com.example.tketl.reading.sources.file.FileSource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

@Service
public class FileCSVSource extends FileSource {
    private InputHandler inputHandler;


    public FileCSVSource(ArchiveManager manager, InputHandler inputHandler ) {
        super(manager);
        this.inputHandler = inputHandler;

    }

    @Override
    public List<OutputFromStep> read(ConfigFileDTO configFileDTO) throws IOException, StepNotFoundException, SQLException, ClassNotFoundException, InvalidColumnNameException {
        super.read(configFileDTO);
        StringBuilder data = get_data_from_file(configFileDTO.getPath());
        return inputHandler.handle_data( data,configFileDTO.getDelimiter(), configFileDTO.isHeader(), configFileDTO);
    }

    public StringBuilder get_data_from_file(String path) throws FileNotFoundException {
        StringBuilder content = new StringBuilder();

        try (Scanner scanner = new Scanner(new File(path))) {
            while (scanner.hasNextLine()) {
                content.append(scanner.nextLine());
                content.append("\n");
            }
        }

        return content;
    }

}
