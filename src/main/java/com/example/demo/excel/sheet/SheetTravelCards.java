package com.example.demo.excel.sheet;

import com.example.demo.excel.constance.FieldConstance;
import com.example.demo.excel.constance.TravelCardConstance;
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
public class SheetTravelCards {

    private final CreaterMain createrMain;
    private final CreaterButtom createrButtom;

    public void createTable(LocalDate day, Workbook workbook){
        List<String> list = Arrays.asList(
            FieldConstance.TRAVEL_CARD, FieldConstance.COUNT_OF_TICKETS, FieldConstance.AMOUNT);

        Sheet sheet = workbook.createSheet("Проздні ");
        sheet.setColumnWidth(0, 6000);
        sheet.setColumnWidth(1, 6000);

        new CreaterHeader().createHeaderTickets(workbook, sheet, list);

        int indexRow = 1;

        int rowMain = createrMain.createTravelCards(indexRow, workbook, sheet, day, TravelCardConstance.list);

        createrButtom.createTravelCards(rowMain, workbook, sheet,day);

    }
}
