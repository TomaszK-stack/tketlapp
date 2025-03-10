package com.example.tketl.DTO.schedule;


import lombok.Data;
import com.example.tketl.DTO.source.ConfigDatabaseDTO;

@Data
public class SqlScheduleDTO extends BaseScheduleDTO {
    public ConfigDatabaseDTO configDatabaseDTO;

}
