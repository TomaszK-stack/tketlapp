package com.example.tketl.processing;
import com.example.tketl.processing.InputStepData;
import com.example.tketl.processing.InputStepMeta;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class OutputFromStep {

    @JsonIgnore
    private InputStepData inputStepData;


//    @JsonProperty("meta")
    private InputStepMeta inputStepMeta;

    public OutputFromStep(InputStepData inputStepData, InputStepMeta inputStepMeta) {
        this.inputStepData = inputStepData;
        this.inputStepMeta = inputStepMeta;

    }


    @JsonProperty
    public String returnData(){
        StringBuilder dataBuilder = new StringBuilder();

        for(List<Object> row: inputStepData.getData().stream().limit(10).toList()){
            dataBuilder.append(row);
            dataBuilder.append(System.getProperty("line.separator"));

        }
        System.out.println(dataBuilder);
        return dataBuilder.toString();
    }


    @Override
    public String toString() {
        return "OutputFromStep{ sdasdasdasdsad" +
                "inputStepData=" + inputStepData.data +
                ", inputStepMeta=" + inputStepMeta +
                '}';
    }
}
