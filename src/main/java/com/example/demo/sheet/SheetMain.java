package com.example.demo.sheet;

import com.example.demo.combine.Combine;
import com.example.demo.creater.CreaterFile;
import com.example.demo.creater.CreaterHeader;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SheetMain {

    private final SheetTickets sheetTickets;
    private final SheetTravelCard sheetTravelCard;
    private final Combine combine;

    public String createWorkbook(LocalDate day) throws IOException {

        List<String> list1 = new ArrayList<>();
        list1.add("Депо №1 трамвай");
        list1.add("Депо №2 трамвай");
        list1.add("Троллейбусное депо");

        combine.combineLong(day);

        File file = new CreaterFile().createFile(day);

        try (Workbook workbook = new XSSFWorkbook();
             FileOutputStream fileOutputStream = new FileOutputStream(file)){

            sheetTickets.createTable(day, workbook);
            sheetTravelCard.createTable(day, workbook, list1);

            workbook.write(fileOutputStream);
        }

        return file.getCanonicalPath();
    }
}
