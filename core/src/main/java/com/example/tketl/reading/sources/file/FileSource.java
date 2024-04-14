package com.example.tketl.reading.sources.file;

import com.example.tketl.api.DTO.source.ConfigFileDTO;
import com.example.tketl.archive.manager.ArchiveManager;
import com.example.tketl.exceptions.InvalidColumnNameException;
import com.example.tketl.exceptions.StepNotFoundException;
import com.example.tketl.processing.OutputFromStep;
import com.example.tketl.archive.save.ArchiveSaveType;
import com.example.tketl.reading.sources.Source;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Component
public abstract class FileSource extends Source {


   private ArchiveManager manager;

   public FileSource(ArchiveManager manager) {
      this.manager = manager;
   }

   List<OutputFromStep> read(ConfigFileDTO configFileDTO) throws IOException, StepNotFoundException, SQLException, ClassNotFoundException, InvalidColumnNameException {

      if(configFileDTO.isArchivePayload()) manager.saveArchive( configFileDTO, ArchiveSaveType.IN);
      return null;
   }


}
