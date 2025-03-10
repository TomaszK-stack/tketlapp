package com.example.tketl.DTO.source;

import lombok.Data;

@Data
public class ConfigFTPDTO extends BaseDTO {
    public String host;
    public int port;
    public String username;
    public String password;
}
