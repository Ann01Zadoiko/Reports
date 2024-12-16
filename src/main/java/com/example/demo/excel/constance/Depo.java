package com.example.demo.excel.constance;

public enum Depo {
    TRAM_1("Депо №1 трамвай"),
    TRAM_2("Депо №2 трамвай"),
    TROLL("Троллейбусное депо");

    private String fullName;

    Depo(String fullName){
        this.fullName = fullName;
    }

    public String getFullName(){
        return fullName;
    }
}
