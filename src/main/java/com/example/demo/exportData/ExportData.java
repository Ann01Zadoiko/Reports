package com.example.demo.exportData;


import com.example.demo.combine.Combine;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ExportData {

    private final Combine combine;
    private final CreateRow createRow;

    public String createTable(LocalDate day) throws IOException {

        combine.combineLong(day);

        File file = new Creater().createFile(day);

        try (Workbook workbook = new XSSFWorkbook();
             FileOutputStream fileOutputStream = new FileOutputStream(file)){

            Sheet sheet = workbook.createSheet("Звіт за " + day);
            sheet.setColumnWidth(0, 6000);
            sheet.setColumnWidth(2, 6000);
            sheet.setColumnWidth(4, 4000);

            createRow.createHeader(workbook, sheet);

            int indexRow = 1;

            createRow.createRowMain(indexRow, workbook, sheet,day);

            workbook.write(fileOutputStream);
        }

        return file.getCanonicalPath();
    }
}
