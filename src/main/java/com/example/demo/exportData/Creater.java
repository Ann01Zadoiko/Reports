package com.example.demo.exportData;

import java.io.File;
import java.time.LocalDate;

public class Creater {

    public File createFile(LocalDate day){
        StringBuilder builder = new StringBuilder()
                .append("./src/main/resources/reports/general_")
                .append(day)
                .append(".xlsx");

        File file = new File(String.valueOf(builder));
        return file;
    }
}
