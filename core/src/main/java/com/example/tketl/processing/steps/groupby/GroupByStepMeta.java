package com.example.tketl.processing.steps.groupby;

import com.example.tketl.processing.steps.BaseStepMeta;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GroupByStepMeta extends BaseStepMeta {
    private String columnTogroup;

    private String operation;

    private String OperationColumn;

}
