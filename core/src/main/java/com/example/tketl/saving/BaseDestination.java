package com.example.tketl.saving;

import com.example.tketl.processing.InputStepData;
import com.example.tketl.processing.InputStepMeta;
import lombok.SneakyThrows;

import java.io.IOException;
import java.sql.SQLException;

public abstract class BaseDestination {

    @SneakyThrows
    public void save(InputStepData inputStepData, InputStepMeta inputStepMeta, boolean header) throws ClassNotFoundException, SQLException, IOException {}
}
