package com.example.tketl.api.DTO.schedule;

import com.example.tketl.api.DTO.source.BaseDTO;
import lombok.Data;

import java.io.Serializable;

@Data
public class BaseScheduleDTO implements Serializable {

    public BaseDTO baseDTO;
    public int timeout;
    public String sourceName;
}
