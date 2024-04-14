package com.example.tketl.api.DTO.destination;

import com.example.tketl.api.DTO.destination.BaseDestinationEndpoint;
import lombok.Data;

@Data
public class FileDestinationDTO implements BaseDestinationEndpoint {
    public String path;
    public String delimiter;
}
