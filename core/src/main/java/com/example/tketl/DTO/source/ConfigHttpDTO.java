package com.example.tketl.DTO.source;

import lombok.Data;

@Data
public class ConfigHttpDTO extends BaseDTO{
    public String url;
    
    public String delimiter;

    public boolean header;

    public FileType type;



}
