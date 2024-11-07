package com.example.demo.importData;

import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

public interface Import {

    void importExcelToDataXls(MultipartFile file, String depo, LocalDate day);

    void importExcelToDataXlsx(MultipartFile file, String depo, LocalDate day);
}
