package org.example.structure.DTO.schedule;


import lombok.Data;
import org.example.structure.DTO.source.BaseDTO;

import java.io.Serializable;

@Data
public class BaseScheduleDTO implements Serializable {

    public BaseDTO baseDTO;
    public int timeout;
    public String sourceName;
}
