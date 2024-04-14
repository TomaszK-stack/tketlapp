package com.example.tketl.processing.steps.unique;

import com.example.tketl.processing.OutputFromStep;
import com.example.tketl.processing.steps.BaseStep;

import java.util.ArrayList;
import java.util.List;

public class UniqueRowsStep extends BaseStep {
    @Override
    public OutputFromStep processData() {
        List<List<Object>> newData = new ArrayList<>();
        for (List<Object> row: inputStepData.getData()){
            if (!newData.contains(row)){
                newData.add(row);
            }
        }
        inputStepData.setData(newData);
        return new OutputFromStep(this.inputStepData, this.inputStepMeta);

    }
}
