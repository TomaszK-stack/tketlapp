package com.example.tketl.api.DTO.schedule;

import com.example.tketl.api.DTO.schedule.BaseScheduleDTO;
import com.example.tketl.api.DTO.source.ConfigFileDTO;
import lombok.Data;

@Data
public class FileScheduleDTO extends BaseScheduleDTO {
    public ConfigFileDTO configFileDTO;
    public String sentdir;
}
