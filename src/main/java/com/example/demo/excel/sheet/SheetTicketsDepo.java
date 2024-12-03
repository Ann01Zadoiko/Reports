package com.example.demo.excel.sheet;

import com.example.demo.excel.constance.FieldConstance;
import com.example.demo.excel.table.TableHeader;
import com.example.demo.excel.table.buttom.TableDepoTicketButtom;
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
    private final TableDepoTicketButtom buttom;

    //create the sheet for tickets by depo
    public void createTable(LocalDate day, Workbook workbook, String depo){
        //list headers
        List<String> list = Arrays.asList(
                FieldConstance.TRACK, FieldConstance.COUNT_OF_TICKETS, FieldConstance.AMOUNT
        );

        //set name for the sheet
        Sheet sheet = workbook.createSheet(depo);
        sheet.setColumnWidth(0, 4000);
        sheet.setColumnWidth(1, 6000);

        //add header
        new TableHeader().createHeaderTickets(workbook, sheet, list);

        int indexRow = 1;

        //add main part
        int rowMain = main.createMain(indexRow, workbook, sheet, day, depo);

        //add button
        buttom.createMain(rowMain, workbook, sheet,day, depo);

    }
}
