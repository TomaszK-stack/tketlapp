package com.example.tketl.archive.manager;

import com.example.tketl.DTO.source.ConfigDatabaseDTO;
import com.example.tketl.DTO.source.ConfigFileDTO;
import com.example.tketl.DTO.source.ConfigHttpDTO;
import com.example.tketl.archive.save.ArchiveFileSaver;
import com.example.tketl.db.log.entities.ArchiveLog;
import com.example.tketl.db.log.repo.ArchiveLogRepository;
import com.example.tketl.archive.save.ArchiveSaveType;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Data
@Component
public class ArchiveManager {


    private ArchiveLogRepository logRepository;
    private ArchiveLog archiveLog;
    private ArchiveFileSaver fileSaver;

    @Autowired
    public ArchiveManager(ArchiveLogRepository logRepository, ArchiveFileSaver fileSaver) {
        this.logRepository = logRepository;
        this.fileSaver = fileSaver;

    }

    public void startProcessing(String sourceType, String source, String destinationType, List<String> destination, String originalFileName ){
            this.archiveLog = destination != null ?
                    ArchiveLog.builder()
                    .sourceType(sourceType)
                    .source(source)
                    .destination(destination.toString())
                    .destinationType(destinationType)
                    .status(1)
                    .originalFileName(originalFileName)
                    .build()
                    :
        ArchiveLog.builder()
                .sourceType(sourceType)
                .source(source)
                .status(1)
                .originalFileName(originalFileName)
                .build()

        ;
        logRepository.save(archiveLog);
    }
    public void startProcessing(String sourceType, String source, String destinationType, List<String> destination ){
        this.archiveLog = destination != null ?
                ArchiveLog.builder()
                .sourceType(sourceType)
                .source(source)
                .destination(destination.toString())
                .destinationType(destinationType)
                .status(1)
                .build()
        :
                ArchiveLog.builder()
                        .sourceType(sourceType)
                        .source(source)
                        .status(1)
                        .build()
        ;
        logRepository.save(archiveLog);
    }
    public void update(String errorMessage, int status){
        this.archiveLog.setStatus(status);
        this.archiveLog.setErrorMessage(errorMessage);
        logRepository.save(archiveLog);
    }

    public void saveArchive(ConfigFileDTO configFileDTO, ArchiveSaveType saveType){
        File file = new File(configFileDTO.getPath());
        String originalFileName = file.getName();
        startProcessing("csv", configFileDTO.getPath(),configFileDTO.getDestinationType(), configFileDTO.getDestinationElementsList(), originalFileName);
        try {
            fileSaver.save(file, archiveLog.getId().toString(), saveType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void saveArchive(ConfigDatabaseDTO configDatabaseDTO, StringBuilder data, ArchiveSaveType saveType)  {
        startProcessing("sql", configDatabaseDTO.getHost(), configDatabaseDTO.getDestinationType(), configDatabaseDTO.getDestinationElementsList());
        try {
            fileSaver.save(data, archiveLog.getId().toString(), saveType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void saveArchive(ConfigHttpDTO configHttpDTO){
        startProcessing("http", configHttpDTO.getUrl(), configHttpDTO.getDestinationType(), configHttpDTO.getDestinationElementsList());
    }
    public void saveArchive(StringBuilder data, ArchiveSaveType saveType){
        try {
            fileSaver.save(data, archiveLog.getId().toString(), saveType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
