package com.example.demo.excel.sheet;

import com.example.demo.excel.constance.FieldConstance;
import com.example.demo.excel.table.CreaterButtom;
import com.example.demo.excel.table.CreaterHeader;
import com.example.demo.excel.table.CreaterMain;
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

    private final CreaterMain createrMain;
    private final CreaterButtom createrButtom;

    public void createTable(LocalDate day, Workbook workbook){

        List<String> list = Arrays.asList(
                FieldConstance.DEPO, FieldConstance.TRAM, FieldConstance.COUNT_OF_TICKETS,
                FieldConstance.AMOUNT, FieldConstance.TRACK + " 1", FieldConstance.TRACK + " 2"
        );

        Sheet sheet = workbook.createSheet("Звіт за " + day);
        sheet.setColumnWidth(0, 6000);
        sheet.setColumnWidth(2, 6000);
        sheet.setColumnWidth(4, 4500);
        sheet.setColumnWidth(5, 4500);

        new CreaterHeader().createHeaderTickets(workbook, sheet, list);

        int indexRow = 1;

        int rowMain = createrMain.createRowMain(indexRow, workbook, sheet, day);

        createrButtom.createRowMain(rowMain, workbook, sheet,day);

    }
}
