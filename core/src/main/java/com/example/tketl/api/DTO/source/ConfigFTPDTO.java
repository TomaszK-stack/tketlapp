package com.example.tketl.api.DTO.source;

import com.example.tketl.api.DTO.source.BaseDTO;
import lombok.Data;

@Data
public class ConfigFTPDTO extends BaseDTO {
    public String host;
    public int port;
    public String username;
    public String password;
}
