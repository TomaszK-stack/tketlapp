package com.example.tketl.saving.file;

import com.example.tketl.archive.manager.ArchiveManager;
import com.example.tketl.processing.InputStepData;
import com.example.tketl.processing.InputStepMeta;
import com.example.tketl.saving.BaseDestination;
import lombok.SneakyThrows;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;


public class FileDestination extends BaseDestination {
    public String path;
    public String delimiter;

    public FileDestination(String path, String delimiter) {
        this.path = path;
        this.delimiter = delimiter;
    }

    @SneakyThrows
    @Override
    public void save(InputStepData inputStepData, InputStepMeta inputStepMeta, boolean header)  {
        File destination = new File(path);
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(destination));
        int len = 0;
        for(String column : inputStepMeta.getColumnNames()){
            bufferedWriter.write(column);

            if (len < inputStepMeta.getColumnNumber()){
                bufferedWriter.write(delimiter);
            }else{
                len = 0;
                bufferedWriter.newLine();
            }


        }
        bufferedWriter.newLine();
        for (List<Object> row : inputStepData.getData()){
            for(Object value : row){
                bufferedWriter.write(String.valueOf(value));
                len++;
                if (len < inputStepMeta.getColumnNumber()){
                    bufferedWriter.write(delimiter);
                }else{
                    len = 0;
                    bufferedWriter.newLine();
                }

            }
        }
        bufferedWriter.flush();
    }
}
