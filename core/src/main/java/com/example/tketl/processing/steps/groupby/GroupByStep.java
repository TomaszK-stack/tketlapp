package com.example.tketl.processing.steps.groupby;


import com.example.tketl.processing.InputStepData;
import com.example.tketl.processing.InputStepMeta;
import com.example.tketl.processing.OutputFromStep;
import com.example.tketl.processing.steps.BaseStep;
import com.example.tketl.processing.steps.groupby.GroupByStepMeta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupByStep extends BaseStep {

    private GroupByStepMeta groupByStepMeta;

    public GroupByStep(InputStepData inputStepData, InputStepMeta inputStepMeta, GroupByStepMeta groupByStepMeta) {
        super(inputStepData, inputStepMeta);
        this.groupByStepMeta = groupByStepMeta;
    }

    @Override
    public OutputFromStep processData()  {
        int groupIndex = inputStepMeta.getColumnNames().indexOf(groupByStepMeta.getColumnTogroup());
        int operationIndex = inputStepMeta.getColumnNames().indexOf(groupByStepMeta.getOperationColumn());
        if(groupIndex == -1 || (operationIndex == -1 && groupByStepMeta.getOperationColumn() != null)){
            throw new IllegalArgumentException("Entered invalid column name to group/for operation");
        }
        switch (groupByStepMeta.getOperation()){
            case "sum":
                sum(groupIndex, operationIndex);
                break;
            case "avg":
                avg(groupIndex, operationIndex);
                break;
            default:
                throw new IllegalArgumentException(groupByStepMeta.getOperation() + " is illegal operation type.");

        }
        modifyMeta();
        return new  OutputFromStep(this.inputStepData, this.inputStepMeta);



    }
    private void sum(int groupIndex, int operationIndex){
        List<List<Object>> newData = new ArrayList<>();
        Map<Object, List<Object>> groupMap = new LinkedHashMap<>();

        for (List<Object> row: inputStepData.getData()){
            if(groupMap.containsKey(row.get(groupIndex))){
                try {
                    List<Object> operationRow = groupMap.get(row.get(groupIndex));
                    var value = operationRow.get(1);
                    Double oldValue = value instanceof Double ? (Double) value :  Double.valueOf((String) operationRow.get(1));
                    Double newValue = oldValue + Double.valueOf((String) row.get(operationIndex));
                    operationRow.set(1, newValue);
                }catch (ClassCastException e){
                    throw new ClassCastException("You Can not sum values of columns of index " + operationIndex);
                }

            } else  {
                List<Object> newList = new ArrayList<>();
                IntStream.of(groupIndex, operationIndex).mapToObj(row::get).forEach(newList::add);
                groupMap.put(row.get(groupIndex), newList);
            }
        }
        newData = groupMap.values().stream()
                .collect(Collectors.toList());
        inputStepData.setData(newData);

    }
    private void avg(int groupIndex, int operationIndex){
        List<List<Object>> newData = new ArrayList<>();
        Map<Object, List<Object>> groupMap = new LinkedHashMap<>();
        Map<Object, Integer> averageMap = new HashMap<>();
        for (List<Object> row: inputStepData.getData()){
            if(groupMap.containsKey(row.get(groupIndex))){
                try {
                    List<Object> operationRow = groupMap.get(row.get(groupIndex));
                    var value = operationRow.get(operationIndex);
                    Double oldValue = value instanceof Double ? (Double) value :  Double.valueOf((String) operationRow.get(operationIndex));
                    Double newValue = oldValue + Double.valueOf((String) row.get(operationIndex));
                    operationRow.set(operationIndex, newValue);
                    averageMap.put(row.get(groupIndex), averageMap.get(row.get(groupIndex)) + 1);
                }catch (ClassCastException e){
                    throw new ClassCastException("You Can not get avg values of columns of index " + operationIndex);
                }

            } else  {
                groupMap.put(row.get(groupIndex), row);
                averageMap.put(row.get(groupIndex), 1);
            }
        }
        for(Map.Entry<Object, List<Object>> map: groupMap.entrySet()){
            map.getValue().set(operationIndex,(Double) map.getValue().get(operationIndex)/averageMap.get(map.getKey()));
        }
        
        newData = groupMap.values().stream()
                .collect(Collectors.toList());
        inputStepData.setData(newData);

    }

    @Override
    protected void modifyMeta() {
        inputStepMeta.setColumnNumber(inputStepData.data.get(0).size());
        List<String> newColumnList = inputStepMeta.getColumnNames()
                .stream()
                .filter(column -> column.equals(groupByStepMeta.getColumnTogroup()) || column.equals(groupByStepMeta.getOperationColumn()))
                .collect(Collectors.toList());
        inputStepMeta.setColumnNames(newColumnList);
    }
}
