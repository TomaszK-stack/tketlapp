package com.example.tketl.reading.sources.file;

import com.example.tketl.api.DTO.source.ConfigFileDTO;
import com.example.tketl.archive.manager.ArchiveManager;
import com.example.tketl.exceptions.InvalidColumnNameException;
import com.example.tketl.exceptions.StepNotFoundException;
import com.example.tketl.processing.OutputFromStep;
import com.example.tketl.reading.InputHandler;
import com.example.tketl.reading.sources.file.FileSource;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Service
public class FileXLSXSource extends FileSource {
    private InputHandler inputHandler;

    public FileXLSXSource(ArchiveManager manager, InputHandler inputHandler) {
        super(manager);
        this.inputHandler = inputHandler;
    }

    @Override
    public List<OutputFromStep> read(ConfigFileDTO configFileDTO) throws IOException, StepNotFoundException, SQLException, ClassNotFoundException, InvalidColumnNameException {
        super.read(configFileDTO);
        StringBuilder data = get_data_from_file(configFileDTO.getPath());
        return inputHandler.handle_data(data,",", configFileDTO.header,configFileDTO);
    }
    private StringBuilder get_data_from_file(String path){
        StringBuilder data = new StringBuilder();
        try {
            Workbook workbook = WorkbookFactory.create(new File(path));

            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                for (Cell cell : row) {
                    switch (cell.getCellType()) {
                        case STRING:
                            data.append(cell.getStringCellValue());
                            break;
                        case NUMERIC:
                            data.append(cell.getNumericCellValue());
                            break;
                        case BOOLEAN:
                            data.append(cell.getBooleanCellValue());
                            break;
                        default:
                            break;
                    }
                    data.append(",");
                }
            }

            workbook.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
    }



