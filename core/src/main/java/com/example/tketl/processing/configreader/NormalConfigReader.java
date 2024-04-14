package com.example.tketl.processing.configreader;

import com.example.tketl.api.DTO.processing.ConfigProcessingDTO;
import com.example.tketl.exceptions.StepNotFoundException;
import com.example.tketl.processing.configreader.ConfigReader;
import com.example.tketl.processing.steps.BaseStep;
import com.example.tketl.processing.steps.filter.ConditionType;
import com.example.tketl.processing.steps.filter.FilterRowStepMeta;
import com.example.tketl.processing.steps.filter.FilterRowsStep;
import com.example.tketl.processing.steps.groupby.GroupByStep;
import com.example.tketl.processing.steps.groupby.GroupByStepMeta;
import com.example.tketl.processing.steps.join.JoinColumnStepMeta;
import com.example.tketl.processing.steps.join.JoinColumnsStep;
import com.example.tketl.processing.steps.nullif.NullifStep;
import com.example.tketl.processing.steps.nullif.NullifStepMeta;
import com.example.tketl.processing.steps.unique.UniqueRowsStep;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class NormalConfigReader implements ConfigReader {

    @Override
    public List<BaseStep> readConfig(ConfigProcessingDTO processingDTO) throws StepNotFoundException {
        List<BaseStep> stepList = new ArrayList<>();
        for (Map.Entry<String, HashMap<String, Object>> entry : processingDTO.getConfigProcessmap().entrySet()) {
            BaseStep step = getStep(entry.getKey(), entry.getValue());
            stepList.add(step);
        }
        return stepList;
    }

    private BaseStep getStep(String stepName, HashMap<String, Object> stepMetaList) throws StepNotFoundException {
        switch (stepName.toLowerCase()) {
            case "nullif":
                NullifStep nullifStep = new NullifStep();
                NullifStepMeta nullifStepMeta = NullifStepMeta.builder()
                        .columnName((String) stepMetaList.get("columnName"))
                        .columnNameValueExpression((String) stepMetaList.get("columnValueExpression"))
                        .valueLogicExpression((String) stepMetaList.get("valueLogicExpression"))
                        .build();
                nullifStep.setNullifStepMeta(nullifStepMeta);
                return nullifStep;
            case "joincolumn":
                JoinColumnStepMeta joinColumnStepMeta = JoinColumnStepMeta.builder()
                        .columnsToJoinList((List<String>) stepMetaList.get("columnToJoin"))
                        .newColumnName((String) stepMetaList.get("newColumn"))
                        .build();
                JoinColumnsStep joinColumnsStep = new JoinColumnsStep(joinColumnStepMeta);
                return joinColumnsStep;
            case "groupby":
                GroupByStepMeta groupByStepMeta =  GroupByStepMeta.builder()
                        .columnTogroup((String) stepMetaList.get("columnToGroup"))
                        .OperationColumn((String) stepMetaList.get("operationColumn"))
                        .operation((String) stepMetaList.get("operation"))
                        .build();
                GroupByStep groupByStep = new GroupByStep(groupByStepMeta);
                return groupByStep;
            case "filterrows":
                FilterRowStepMeta filterRowStepMeta = FilterRowStepMeta.builder()
                        .column1((String) stepMetaList.get("column1"))
                        .column2((String) stepMetaList.get("column2"))
                        .conditionType(ConditionType.valueOf((String) stepMetaList.get("conditionType")))
                        .meetsCondition((Boolean) stepMetaList.get("meetsCondition"))
                        .staticValue((Boolean) stepMetaList.get("staticValue"))
                        .build();
                FilterRowsStep filterRowsStep = new FilterRowsStep(filterRowStepMeta);
                return filterRowsStep;
            case "uniquerows":
                return new UniqueRowsStep();
//                cases....
                    default:
                throw new StepNotFoundException("Invalid step name");
        }


    }
}
