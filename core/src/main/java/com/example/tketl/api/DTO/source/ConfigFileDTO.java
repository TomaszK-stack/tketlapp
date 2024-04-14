package com.example.tketl.api.DTO.source;

import com.example.tketl.api.DTO.source.BaseDTO;
import lombok.Data;

@Data
public class ConfigFileDTO extends BaseDTO {
    public String path;
    public String delimiter;

    public boolean header;


}
