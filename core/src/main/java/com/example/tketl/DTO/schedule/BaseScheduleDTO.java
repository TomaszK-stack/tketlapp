package com.example.tketl.DTO.schedule;


import lombok.Data;
import com.example.tketl.DTO.source.BaseDTO;

import java.io.Serializable;

@Data
public class BaseScheduleDTO implements Serializable {

    public BaseDTO baseDTO;
    public int timeout;
    public String sourceName;
}
