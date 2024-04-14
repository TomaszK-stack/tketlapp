package com.example.scheduling.main.services;

import com.example.scheduling.main.JobDataStore;
import com.example.scheduling.main.ScheduleType;
import com.example.scheduling.main.jobs.FileInvokeJob;
import com.example.scheduling.main.jobs.SqlInvokeJob;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.example.structure.DTO.schedule.BaseScheduleDTO;
import org.example.structure.DTO.schedule.FileScheduleDTO;
import org.example.structure.DTO.schedule.SqlScheduleDTO;
import org.example.structure.DTO.source.ConfigDatabaseDTO;
import org.example.structure.DTO.source.ConfigFileDTO;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.HashMap;
import java.util.UUID;

@Service
public class InvokeScheduleService {

    private SchedulerFactoryBean schedulerFactoryBean;

    private HashMap<ScheduleType, Class> jobMap;

    private ObjectMapper mapper;
    @Value("${config.core.path}")
    private String configPath;

    @Autowired
    public InvokeScheduleService(SchedulerFactoryBean schedulerFactoryBean) {
        this.schedulerFactoryBean = schedulerFactoryBean;
        this.mapper = new ObjectMapper();
        jobMap = new HashMap<>();
        jobMap.put(ScheduleType.SQL, SqlInvokeJob.class);
        jobMap.put(ScheduleType.FILE, FileInvokeJob.class);
    }

    public void  prepareConfig(FileScheduleDTO fileScheduleDTO) throws SchedulerException {
        UUID uuid = UUID.randomUUID();
        saveConfig(fileScheduleDTO.getConfigFileDTO(), uuid);
        createJob(uuid, fileScheduleDTO, ScheduleType.FILE);
    }
    public void  prepareConfig(SqlScheduleDTO sqlScheduleDTO) throws SchedulerException {
        UUID uuid = UUID.randomUUID();
        saveConfig(sqlScheduleDTO.getConfigDatabaseDTO(), uuid);
        createJob(uuid, sqlScheduleDTO, ScheduleType.SQL);
    }

    private void saveConfig(ConfigDatabaseDTO configDatabaseDTO, UUID uuid){
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(configPath + "\\sql\\" +  uuid.toString())));
            bufferedWriter.write(mapper.writeValueAsString(configDatabaseDTO));
            bufferedWriter.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Something went wrong during saving config " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException("Something went wrong during saving config " + e.getMessage());
        }
    }


    private void saveConfig(ConfigFileDTO configFileDTO, UUID uuid){
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(configPath + "\\file\\" +  uuid.toString())));
            bufferedWriter.write(mapper.writeValueAsString(configFileDTO));
            bufferedWriter.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Something went wrong during saving config " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException("Something went wrong during saving config " + e.getMessage());
        }
    }
    private void createJob(UUID uuid, BaseScheduleDTO baseScheduleDTO, ScheduleType type) throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        int length = 10;
        boolean useLetters = true;
        boolean useNumbers = false;

        String generatedStringJobDetail = RandomStringUtils.random(length, useLetters, useNumbers);
        String generatedStringTrigger = RandomStringUtils.random(length, useLetters, useNumbers);
        JobDetail jobDetail = JobBuilder.newJob(jobMap.get(type))
                .withIdentity(generatedStringJobDetail)
                .build();
        JobDataMap jobDataMap = jobDetail.getJobDataMap();
        jobDataMap.put(String.valueOf(JobDataStore.DATA_STORE_UID), uuid);

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity( generatedStringTrigger,  "Trigger")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(baseScheduleDTO.getTimeout()).repeatForever())
                .build();
        scheduler.scheduleJob(jobDetail, trigger);

    }


}
