package com.example.tketl.api.DTO.schedule;

import com.example.tketl.api.DTO.schedule.BaseScheduleDTO;
import com.example.tketl.api.DTO.source.ConfigDatabaseDTO;
import lombok.Data;

@Data
public class SqlScheduleDTO extends BaseScheduleDTO {
    public ConfigDatabaseDTO configDatabaseDTO;

}
