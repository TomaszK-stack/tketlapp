package com.example.tketl.DTO.source;


import lombok.Getter;
import com.example.tketl.DTO.destination.BaseDestinationEndpoint;
import com.example.tketl.DTO.processing.ConfigProcessingDTO;

import java.io.Serializable;
import java.util.List;


@Getter
public abstract class BaseDTO implements Serializable {
    public ConfigProcessingDTO configProcessingDTO;
    public BaseDestinationEndpoint baseDestinationEndpoint;

    public String destinationType;
    public List<String> destinationElementsList;

    public boolean archivePayload = true;


}
