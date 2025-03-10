package com.example.tketl.DTO.destination;

import lombok.Data;

@Data
public class FileDestinationDTO implements BaseDestinationEndpoint {
    public String path;
    public String delimiter;
}
