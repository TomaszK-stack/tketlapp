package com.example.scheduling.main.api;

import com.example.scheduling.main.services.InvokeScheduleService;
import org.example.structure.DTO.schedule.FileScheduleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("schedule")
public class JobReaderController {

    private InvokeScheduleService invokeScheduleService;

    @Autowired
    public JobReaderController(InvokeScheduleService invokeScheduleService) {
        this.invokeScheduleService = invokeScheduleService;
    }

    @PostMapping("file")
    public ResponseEntity<String> createJob(@RequestBody FileScheduleDTO fileScheduleDTO){
        try {
            invokeScheduleService.prepareConfig(fileScheduleDTO);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().body("It occured problem during create of schedule, please contact with administration.");
        }
        return ResponseEntity.ok("Your schedule created succesfully.");
    }


}
