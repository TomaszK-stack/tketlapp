package com.example.tketl.api.DTO.destination;

import com.example.tketl.api.DTO.destination.BaseDestinationEndpoint;
import lombok.Getter;

@Getter
public class SqlDestinationDTO implements BaseDestinationEndpoint {
    public String host;
    public int port;
    public String databaseName;

    public String user;

    public String password;


}
