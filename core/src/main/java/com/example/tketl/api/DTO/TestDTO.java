package com.example.tketl.api.DTO;

import com.example.tketl.api.DTO.Test2Dto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TestDTO {
    public boolean test;


    public Test2Dto t;
    public TestDTO(boolean test, Test2Dto t) {
        this.test = test;
        this.t = t;
    }


    public boolean isTest() {
        return test;
    }

}
