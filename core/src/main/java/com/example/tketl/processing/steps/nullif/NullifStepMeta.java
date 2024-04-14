package com.example.tketl.processing.steps.nullif;

import com.example.tketl.processing.steps.BaseStepMeta;
import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class NullifStepMeta extends BaseStepMeta {

    private String columnName;
    private String columnNameValueExpression;
    private String valueLogicExpression;

}
