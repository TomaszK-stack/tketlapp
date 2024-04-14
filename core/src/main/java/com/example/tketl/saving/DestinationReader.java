package com.example.tketl.saving;

import com.example.tketl.exceptions.StepNotFoundException;
import com.example.tketl.saving.BaseDestination;
import com.example.tketl.saving.file.FileDestination;
import com.example.tketl.saving.sql.SqlServerDestination;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DestinationReader {
    public BaseDestination readDestinationConfig(String destinationType, List<String> destinationElements) throws StepNotFoundException {
        switch (destinationType) {
            case "sqlserver":

                return new SqlServerDestination(destinationElements.get(0), destinationElements.get(1), destinationElements.get(2), destinationElements.get(3), destinationElements.get(4), destinationElements.get(5));

            case "file":
                return new FileDestination(destinationElements.get(0), destinationElements.get(1));
            default:
                throw new StepNotFoundException("Invalid step name");


        }
    }
}
