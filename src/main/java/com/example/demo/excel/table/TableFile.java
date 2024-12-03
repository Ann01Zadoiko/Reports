package com.example.demo.excel.table;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.time.LocalDate;

@Slf4j
public class TableFile {

    //create a file in reports (folder)
    public File createFile(LocalDate day){
        String builder = "./src/main/resources/reports/general_" + day + ".xlsx";
        log.info("File has created with path: {}", builder);
        return new File(builder);
    }
}
