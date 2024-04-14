package org.example.structure.DTO.schedule;


import lombok.Data;
import org.example.structure.DTO.source.ConfigFileDTO;

@Data
public class FileScheduleDTO extends BaseScheduleDTO {
    public ConfigFileDTO configFileDTO;
    public String sentdir;

}
