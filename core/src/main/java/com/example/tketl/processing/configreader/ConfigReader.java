package com.example.tketl.processing.configreader;

import com.example.tketl.api.DTO.processing.ConfigProcessingDTO;
import com.example.tketl.exceptions.StepNotFoundException;
import com.example.tketl.processing.steps.BaseStep;

import java.util.List;

public interface ConfigReader {
     List<BaseStep> readConfig(ConfigProcessingDTO processingDTO) throws StepNotFoundException;

}
