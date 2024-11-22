package com.example.demo.excel.sheet;

import com.example.demo.combine.Combine;
import com.example.demo.excel.constance.DepoConstance;
import com.example.demo.excel.table.CreaterFile;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class SheetMain {

    private final SheetTickets sheetTickets;
    private final SheetTravelCard sheetTravelCard;
    private final Combine combine;
    private final SheetTicketsDepo sheetTicketsDepo;
    private final SheetTravelCards sheetTravelCards;

    public String createWorkbook(LocalDate day) throws IOException {

        combine.combineLong(day);

        File file = new CreaterFile().createFile(day);

        try (Workbook workbook = new XSSFWorkbook();
             FileOutputStream fileOutputStream = new FileOutputStream(file)){

            sheetTickets.createTable(day, workbook);

            sheetTicketsDepo.createTable(day, workbook, DepoConstance.DEPOS.get(0));
            sheetTicketsDepo.createTable(day, workbook, DepoConstance.DEPOS.get(1));
            sheetTicketsDepo.createTable(day, workbook, DepoConstance.DEPOS.get(2));

            sheetTravelCard.createTable(day, workbook, DepoConstance.DEPOS);

            sheetTravelCards.createTable(day, workbook);

            workbook.write(fileOutputStream);
        }

        return file.getCanonicalPath();
    }
}
