package com.example.tketl.api.controllers.scheduling;

import org.example.structure.DTO.schedule.SqlScheduleDTO;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("config/schedule/sql")
public class ReadingSqlScheduleController {

    @PostMapping("/sqlserver")
    public void sqlServerScheduleCreate(@RequestBody SqlScheduleDTO scheduleDTO) throws SchedulerException {
        scheduleDTO.setSourceName("SqlServer");
    }


}
