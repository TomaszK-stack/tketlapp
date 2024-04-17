package com.example.tketl.reading.sources.sql;

import org.example.structure.DTO.source.ConfigDatabaseDTO;
import com.example.tketl.exceptions.InvalidColumnNameException;
import com.example.tketl.exceptions.StepNotFoundException;
import com.example.tketl.processing.OutputFromStep;
import com.example.tketl.reading.sources.Source;
import com.example.tketl.reading.sources.sql.SqlType;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public abstract class SqlSource extends Source {

    public abstract List<OutputFromStep> read(ConfigDatabaseDTO configDTO, SqlType type) throws ClassNotFoundException, SQLException, StepNotFoundException, InvalidColumnNameException, IOException;
    {

    }
}
