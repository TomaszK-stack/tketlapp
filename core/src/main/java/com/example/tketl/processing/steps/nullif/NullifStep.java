package com.example.tketl.processing.steps.nullif;

import com.example.tketl.processing.InputStepData;
import com.example.tketl.processing.InputStepMeta;
import com.example.tketl.processing.OutputFromStep;
import com.example.tketl.processing.steps.BaseStep;
import com.example.tketl.processing.steps.nullif.NullifStepMeta;
import lombok.Data;
import lombok.Setter;

import java.util.List;


@Data
@Setter
public class NullifStep extends BaseStep {
    private NullifStepMeta nullifStepMeta;

    public NullifStep() {
        super();
    }


    public NullifStep(InputStepData inputStepData, InputStepMeta inputStepMeta, NullifStepMeta nullifStepMeta) {
        super(inputStepData, inputStepMeta);
        this.nullifStepMeta = nullifStepMeta;
    }

    @Override
    public OutputFromStep processData() {
        List<String> headerRow =  inputStepMeta.getColumnNames();
        int expressionColumnIndex = 0;
        int columnToChangeIndex = 0;

        columnToChangeIndex = headerRow.indexOf(nullifStepMeta.getColumnName());
        expressionColumnIndex = headerRow.indexOf(nullifStepMeta.getColumnNameValueExpression());
        if(columnToChangeIndex == -1 || expressionColumnIndex == -1){
            throw new IllegalArgumentException("You typed invalid column name in nullif statement");
        }

        for(List<Object> s: inputStepData.getData()){
            if(s.get(expressionColumnIndex).equals(nullifStepMeta.getValueLogicExpression())) s.set(columnToChangeIndex, null);
        }

        return new OutputFromStep(this.inputStepData, this.inputStepMeta);

    }
}
