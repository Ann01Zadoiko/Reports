package com.example.demo.excel.constance;

public enum Style {

    FONT_NAME("Times New Roman"),
    AMOUNT("Всього");

    private String fullName;

    Style(String fullName){
        this.fullName = fullName;
    }

    public String getFullName(){
        return fullName;
    }
}
