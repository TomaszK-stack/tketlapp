package com.example.tketl.reading.sources.sql;

import com.example.tketl.DTO.source.ConfigDatabaseDTO;
import com.example.tketl.archive.manager.ArchiveManager;
import com.example.tketl.exceptions.InvalidColumnNameException;
import com.example.tketl.exceptions.StepNotFoundException;
import com.example.tketl.processing.OutputFromStep;
import com.example.tketl.archive.save.ArchiveSaveType;
import com.example.tketl.reading.InputHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.*;
import java.util.List;

@Service
public class SqlSourceDefault extends SqlSource {

    private InputHandler inputHandler;
    private ArchiveManager manager;
    @Autowired
    public SqlSourceDefault(InputHandler inputHandler, ArchiveManager manager) {
        this.inputHandler = inputHandler;
        this.manager = manager;
    }



    @Override
    public List<OutputFromStep> read(ConfigDatabaseDTO configDTO, SqlType type) throws ClassNotFoundException, SQLException, StepNotFoundException, InvalidColumnNameException, IOException {
        String connectionurl="jdbc:sqlserver://" + configDTO.getHost()
                + ":"  + configDTO.getPort()
                + ";DatabaseName=" + configDTO.getDatabaseName()
                + ";user=" + configDTO.getUser()
                + ";password=" + configDTO.getPassword()
                + ";encrypt=true;trustServerCertificate=true;";

        switch (type){
            case SQL_SERVER -> Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        }


        Connection connection = DriverManager.getConnection(connectionurl);
        if (connection != null) {
            System.out.println("Connection created successfully..");
        }
        String sql = "{CALL " + configDTO.getInterface_name() + "()}";
        CallableStatement callableStatement = connection.prepareCall(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = callableStatement.executeQuery();

        ResultSetMetaData rsmd = resultSet.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        StringBuilder sb = new StringBuilder();
        if(resultSet.next()) {
            resultSet.previous();
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1) sb.append(",");
                String columnName = rsmd.getColumnName(i);
                sb.append(columnName);
            }
            sb.append("\n");


            while (resultSet.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) sb.append(",");

                    String columnValue = resultSet.getString(i);
                    sb.append(columnValue);

                }
                sb.append("\n");
            }
            manager.saveArchive( configDTO , sb, ArchiveSaveType.IN);

            return inputHandler.handle_data(sb,",", true, configDTO);
        }
        else {
            return null;
        }
    }


}
