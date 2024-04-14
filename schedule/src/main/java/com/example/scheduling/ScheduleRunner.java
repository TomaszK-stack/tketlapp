package com.example.scheduling;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.boot.SpringApplication;


@SpringBootApplication
@EnableScheduling
public class ScheduleRunner {
    public static void main(String[] args) {
        SpringApplication.run(com.example.scheduling.ScheduleRunner.class, args);
    }

}
