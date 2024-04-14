package com.example.tketl.processing.steps.filter;

import com.example.tketl.processing.steps.BaseStepMeta;
import com.example.tketl.processing.steps.filter.ConditionType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FilterRowStepMeta extends BaseStepMeta {
    public ConditionType conditionType;
    public String column1;
    public String column2;

    public boolean meetsCondition;

    public boolean staticValue;


}
