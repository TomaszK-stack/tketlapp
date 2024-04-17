package com.example.scheduling.main.jobs;

import com.example.scheduling.main.ScheduleType;
import com.example.scheduling.main.JobDataStore;
import org.example.structure.DTO.source.ConfigFileDTO;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.UUID;

@DisallowConcurrentExecution
public class FileInvokeJob extends InvokeSourceJob{

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        UUID uuid = (UUID) context.getJobDetail().getJobDataMap().get(String.valueOf(JobDataStore.DATA_STORE_UID));
        ConfigFileDTO fileDTO = (ConfigFileDTO) readDTOFromFile(uuid, ScheduleType.FILE);
        if (fileDTO == null){
            throw new NullPointerException("There is no configuration provided");
        }
        sendRequest(fileDTO, ScheduleType.FILE + "/" + fileDTO.type.toString());
    }





}
