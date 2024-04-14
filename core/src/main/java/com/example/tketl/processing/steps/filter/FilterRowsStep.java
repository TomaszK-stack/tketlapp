package com.example.tketl.processing.steps.filter;

import com.example.tketl.exceptions.InvalidColumnNameException;
import com.example.tketl.processing.OutputFromStep;
import com.example.tketl.processing.steps.BaseStep;
import com.example.tketl.processing.steps.filter.FilterRowStepMeta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
@Builder
@AllArgsConstructor
public class FilterRowsStep extends BaseStep {
    private FilterRowStepMeta filterRowStepMeta;
    private Object value1;
    private Object value2;

    public FilterRowsStep( FilterRowStepMeta filterRowStepMeta) {
        this.filterRowStepMeta = filterRowStepMeta;
    }

    @SneakyThrows
    @Override
    public OutputFromStep processData() {
        List<List<Object>> newData = new ArrayList<>();
        int index1 = inputStepMeta.getColumnNames().indexOf(filterRowStepMeta.getColumn1());
        int index2 = inputStepMeta.getColumnNames().indexOf(filterRowStepMeta.getColumn2());
        if ((index1 == -1 && index2 == -1 && !filterRowStepMeta.staticValue ) || (index1 == -1)) {
            throw new InvalidColumnNameException("You entered column name which not exists in previous step!!!");
        }

        for (List<Object> row : inputStepData.getData()) {
            boolean expectConditionRow = evaluateExpressionForRow(row, index1, index2);
            if (filterRowStepMeta.isMeetsCondition()) {
                if (expectConditionRow) {
                    newData.add(row);
                }

            } else {
                if (!expectConditionRow) {
                    newData.add(row);
                }
            }
            inputStepData.setData(newData);
        }

        return new OutputFromStep(this.inputStepData, this.inputStepMeta);
    }

    private boolean evaluateExpressionForRow(List<Object> row, int index1, int index2) {
        var value1 = row.get(index1);
        var value2 = filterRowStepMeta.isStaticValue() ?  filterRowStepMeta.getColumn2() : row.get(index2);
        String s = "";
        switch (filterRowStepMeta.getConditionType()) {
            case EQUAL:
                return value1.equals(value2);

            case LARGER:
                return (double) value1 > (double) value2;

            case SMALLER:
                return (double) value1 < (double) value2;

            case NOT_EQUAL:
                return !value1.equals(value2);


            default:
                throw new IllegalArgumentException();
        }

    }


}
