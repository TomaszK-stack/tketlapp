package com.example.tketl.DTO.schedule;


import lombok.Data;
import com.example.tketl.DTO.source.ConfigFileDTO;

@Data
public class FileScheduleDTO extends BaseScheduleDTO {
    public ConfigFileDTO configFileDTO;
    public String sentdir;

}
