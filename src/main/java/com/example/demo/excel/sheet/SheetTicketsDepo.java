package com.example.demo.excel.sheet;

import com.example.demo.excel.constance.Field;
import com.example.demo.excel.table.TableHeader;
import com.example.demo.excel.table.bottom.TableDepoTicketBottom;
import com.example.demo.excel.table.main.TableDepoTicketMain;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SheetTicketsDepo {

    private final TableDepoTicketMain main;
    private final TableDepoTicketBottom bottom;

    //create the sheet for tickets by depo
    public void createTable(LocalDate day, Workbook workbook, String depo){
        //list headers
        List<String> list = Arrays.asList(
                Field.TRACK.getFullName(),
                Field.COUNT_OF_TICKETS.getFullName(),
                Field.AMOUNT.getFullName()
        );

        //set name for the sheet
        Sheet sheet = workbook.createSheet("Одноразові квитки за " + depo);
        sheet.setColumnWidth(0, 4000);
        sheet.setColumnWidth(1, 6000);

        //add header
        new TableHeader().createHeaderTickets(workbook, sheet, list);

        int indexRow = 1;

        //add main part
        int rowMain = main.createMain(indexRow, workbook, sheet, day, depo);

        //add button
        bottom.createBottom(rowMain, workbook, sheet,day, depo);

    }

    public void createTableSize(LocalDate day, Workbook workbook, String depo){
        //list headers
        List<String> list = Arrays.asList(
                Field.TRACK.getFullName(),
                Field.COUNT_OF_TICKETS.getFullName(),
                Field.AMOUNT.getFullName()
        );

        //set name for the sheet
        Sheet sheet = workbook.createSheet(day.getDayOfMonth() + "." + day.getMonthValue());
        sheet.setColumnWidth(0, 4000);
        sheet.setColumnWidth(1, 6000);

        //add header
        new TableHeader().createHeaderTickets(workbook, sheet, list);

        int indexRow = 1;

        //add main part
        int rowMain = main.createMain(indexRow, workbook, sheet, day, depo);

        //add button
        bottom.createBottom(rowMain, workbook, sheet,day, depo);

    }
}
