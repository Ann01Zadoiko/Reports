package com.example.demo.excel.sheet;

import com.example.demo.combine.Combine;
import com.example.demo.excel.constance.DepoConstance;
import com.example.demo.excel.table.TableFile;
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
    private final Combine combine;
    private final SheetTicketsDepo sheetTicketsDepo;
    private final SheetTravelCards sheetTravelCards;
    private final SheetTravelCardsDepo sheetTravelCardsDepo;

    //create workbook
    public String createWorkbook(LocalDate day) throws IOException {

        //add track into tickets
        combine.combineTrackAndTicketsByDay(day);

        //create file in reports
        File file = new TableFile().createFile(day);

        //create workbook
        try (Workbook workbook = new XSSFWorkbook();
             FileOutputStream fileOutputStream = new FileOutputStream(file)){

            //add a sheet of tickets
            sheetTickets.createTable(day, workbook);

            //add sheets of tickets by depo
            sheetTicketsDepo.createTable(day, workbook, DepoConstance.DEPOS.get(0));
            sheetTicketsDepo.createTable(day, workbook, DepoConstance.DEPOS.get(1));
            sheetTicketsDepo.createTable(day, workbook, DepoConstance.DEPOS.get(2));

            //add a sheet of travel cards
            sheetTravelCards.createTable(day, workbook);

            //add a sheet of travel cards by depo
            sheetTravelCardsDepo.createTable(day, workbook, DepoConstance.DEPOS.get(0));
            sheetTravelCardsDepo.createTable(day, workbook, DepoConstance.DEPOS.get(1));
            sheetTravelCardsDepo.createTable(day, workbook, DepoConstance.DEPOS.get(2));

            workbook.write(fileOutputStream);
        }

        return file.getCanonicalPath();
    }
}
