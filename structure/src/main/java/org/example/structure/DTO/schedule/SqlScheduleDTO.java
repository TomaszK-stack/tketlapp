package org.example.structure.DTO.schedule;


import lombok.Data;
import org.example.structure.DTO.source.ConfigDatabaseDTO;

@Data
public class SqlScheduleDTO extends BaseScheduleDTO {
    public ConfigDatabaseDTO configDatabaseDTO;

}
