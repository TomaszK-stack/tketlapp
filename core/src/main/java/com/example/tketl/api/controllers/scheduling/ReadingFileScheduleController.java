package com.example.tketl.api.controllers.scheduling;

import org.example.structure.DTO.schedule.FileScheduleDTO;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("config/schedule/file")
public class ReadingFileScheduleController {


    @PostMapping("/csv")
    public void fileCsvScheduleCreate(@RequestBody FileScheduleDTO scheduleDTO) throws SchedulerException {
        System.out.println("TEST");
    }

}
