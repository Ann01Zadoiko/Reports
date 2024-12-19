package com.example.demo.excel.workbook;

import com.example.demo.excel.sheet.*;
import com.example.demo.excel.table.TableFile;
import com.example.demo.ticket.TicketService;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
@RequiredArgsConstructor
public class WorkbookForMonth {

    private final TicketService ticketService;
    private final SheetTicketsDepo sheetTicketsDepo;
    private final SheetTravelCardsDepoMonth sheetTravelCardsDepoMonth;
    private final SheetTicketsDepoMonth sheetTicketsDepoMonth;

    //create workbook
    public String createWorkbook(String month, String year, String depo) throws IOException {
        //create file in reports
        File file = new TableFile().createFileForMonth(month, year, depo);

        //create workbook
        try (Workbook workbook = new XSSFWorkbook();
             FileOutputStream fileOutputStream = new FileOutputStream(file)){
            
            //add sheets of tickets by depo
            List<LocalDate> listOfDaysByMonth = ticketService.getDaysOfMoth(month, year);
            for (LocalDate day: listOfDaysByMonth){
                sheetTicketsDepo.createTableSize(day, workbook, depo);
            }

           //amount for a month tickets
            sheetTicketsDepoMonth.createTable(workbook, depo, month, year);
           //amount for a month travel cards
            sheetTravelCardsDepoMonth.createTable(workbook, depo, month, year);

            workbook.write(fileOutputStream);
        }

        return file.getCanonicalPath();
    }

}
