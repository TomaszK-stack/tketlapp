package com.example.tketl.DTO.source;

import lombok.Data;

@Data
public class ConfigFileDTO extends BaseDTO {
    public String path;
    public String delimiter;

    public boolean header;

    public FileType type;




}
