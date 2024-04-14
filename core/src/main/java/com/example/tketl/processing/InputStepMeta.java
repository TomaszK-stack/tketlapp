package com.example.tketl.processing;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InputStepMeta  {
    private int columnNumber;
    private List<String> columnNames;

    public InputStepMeta(InputStepMeta inputStepMeta) {
        this.columnNumber = inputStepMeta.getColumnNumber();
        this.columnNames = new ArrayList<>(inputStepMeta.getColumnNames());
    }
}
