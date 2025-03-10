package com.example.tketl.DTO;

import java.util.ArrayList;
import java.util.List;

public class Test2Dto {
    public int liczba;

    public List<Integer> list;

    public Test2Dto(int liczba) {
        this.liczba = liczba;
        this.list = new ArrayList<>();
    }
}
