package com.example.tketl.api.DTO.processing;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;

@Data
public class ConfigProcessingDTO implements Serializable {
    public LinkedHashMap<String, HashMap<String, Object>> configProcessmap;

}
