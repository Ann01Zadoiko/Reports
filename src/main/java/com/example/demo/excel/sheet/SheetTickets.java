package com.example.demo.excel.sheet;

import com.example.demo.excel.constance.FieldConstance;
import com.example.demo.excel.table.TableHeader;
import com.example.demo.excel.table.bottom.TableRegularTicketBottom;
import com.example.demo.excel.table.main.TableRegularTicketMain;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SheetTickets {

    private final TableRegularTicketBottom bottom;
    private final TableRegularTicketMain main;

    //create the sheet for tickets
    public void createTable(LocalDate day, Workbook workbook){
        //list of headers
        List<String> list = Arrays.asList(
                FieldConstance.DEPO,
                FieldConstance.TRAM,
                FieldConstance.COUNT_OF_TICKETS,
                FieldConstance.AMOUNT,
                FieldConstance.TRACK + " 1",
                FieldConstance.TRACK + " 2"
        );

        //set name for the sheet
        Sheet sheet = workbook.createSheet("Звіт з одноразових квітків");
        sheet.setColumnWidth(0, 6000);
        sheet.setColumnWidth(2, 6000);
        sheet.setColumnWidth(4, 4500);
        sheet.setColumnWidth(5, 4500);

        //add header
        new TableHeader().createHeaderTickets(workbook, sheet, list);

        int indexRow = 1;

        //add main part
        int rowMain = main.createMain(indexRow, workbook, sheet, day);

        //add button
        bottom.createBottom(rowMain, workbook, sheet,day);

    }
}
