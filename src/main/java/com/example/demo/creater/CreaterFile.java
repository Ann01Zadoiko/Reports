package com.example.demo.creater;

import java.io.File;
import java.time.LocalDate;

public class CreaterFile {

    public File createFile(LocalDate day){
        String builder = "./src/main/resources/reports/general_" + day + ".xlsx";
        return new File(builder);
    }
}
