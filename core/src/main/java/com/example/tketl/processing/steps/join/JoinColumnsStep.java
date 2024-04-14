package com.example.tketl.processing.steps.join;

import com.example.tketl.processing.InputStepMeta;
import com.example.tketl.processing.steps.BaseStep;
import com.example.tketl.processing.steps.join.JoinColumnStepMeta;
import lombok.Data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Data
public class JoinColumnsStep extends BaseStep {
    private JoinColumnStepMeta joinStepMeta;
    private List<Integer> columntToRemoveIndexes;

    public JoinColumnsStep(JoinColumnStepMeta joinStepMeta) {
        this.joinStepMeta = joinStepMeta;
        columntToRemoveIndexes = new ArrayList<>();
    }


    @Override
    protected List<Object> processRow(List<Object> row) {
        StringBuilder valueToAdd = new StringBuilder();
        Iterator<Integer> iterator2 = columntToRemoveIndexes.iterator();
        while (iterator2.hasNext()){
            int i = iterator2.next();
            if(row.get(i) !=null) {
                valueToAdd.append(row.get(i));
            }
            row.remove(i);
        }
        row.add(row.size(), valueToAdd.toString());
        return row;

    }



    @Override
    protected void modifyMeta() {
//        columntToJoinIndexes.forEach(i -> inputStepMeta.getColumnNames().remove(i.intValue()));
        for (String column: joinStepMeta.getColumnsToJoinList()){
            int i = inputStepMeta.getColumnNames().indexOf(column);
            inputStepMeta.getColumnNames().remove(i);
            columntToRemoveIndexes.add(i);
        }
        inputStepMeta.getColumnNames().add(joinStepMeta.getNewColumnName());
        inputStepMeta.setColumnNumber(inputStepMeta.getColumnNames().size());
    }

    @Override
    public void setInputStepMeta(InputStepMeta inputStepMeta) {
        super.setInputStepMeta(inputStepMeta);
        prepareData();
        modifyMeta();


    }
}
