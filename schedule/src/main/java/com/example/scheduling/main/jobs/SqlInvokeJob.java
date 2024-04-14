package com.example.scheduling.main.jobs;

import com.example.scheduling.main.ScheduleType;
import com.example.scheduling.main.JobDataStore;
import org.example.structure.DTO.source.ConfigDatabaseDTO;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.UUID;

@DisallowConcurrentExecution
public class SqlInvokeJob extends InvokeSourceJob{
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        UUID uuid = (UUID) context.getJobDetail().getJobDataMap().get(String.valueOf(JobDataStore.DATA_STORE_UID));
        ConfigDatabaseDTO configDatabaseDTO = (ConfigDatabaseDTO) readDTOFromFile(uuid, ScheduleType.SQL);
        if (configDatabaseDTO == null){
            throw new NullPointerException("There is no configuration provided");
        }
        sendRequest(configDatabaseDTO, ScheduleType.SQL.toString());
    }


}
