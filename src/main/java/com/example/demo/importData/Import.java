package com.example.demo.importData;

import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

public interface Import {

    void importExcelToData(MultipartFile file, String depo, LocalDate day);

}
