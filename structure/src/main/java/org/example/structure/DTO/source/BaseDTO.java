package org.example.structure.DTO.source;


import lombok.Getter;
import org.example.structure.DTO.destination.BaseDestinationEndpoint;
import org.example.structure.DTO.processing.ConfigProcessingDTO;

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
