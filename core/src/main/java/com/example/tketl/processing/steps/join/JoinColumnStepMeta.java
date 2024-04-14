package com.example.tketl.processing.steps.join;

import com.example.tketl.processing.steps.BaseStepMeta;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class JoinColumnStepMeta extends BaseStepMeta {
    private List<String> columnsToJoinList;
    private String newColumnName;
}
