package com.example.demo.excel.sheet;

import com.example.demo.excel.constance.Field;
import com.example.demo.excel.table.TableHeader;
import com.example.demo.excel.table.bottom.TableRegularCardBottom;
import com.example.demo.excel.table.main.TableRegularCardMain;
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

    private final TableRegularCardMain main;
    private final TableRegularCardBottom bottom;

    //create the sheet for travel cards
    public void createTable(LocalDate day, Workbook workbook){
        //list of headers
        List<String> list = Arrays.asList(
                Field.TRAVEL_CARD.getFullName(),
                Field.COUNT_OF_TICKETS.getFullName(),
                Field.AMOUNT.getFullName());

        //set name for the sheet
        Sheet sheet = workbook.createSheet("Всі проїздні");
        sheet.setColumnWidth(0, 10000);
        sheet.setColumnWidth(1, 6000);

        //add header
        new TableHeader().createHeaderTickets(workbook, sheet, list);

        int indexRow = 1;

        //add main part
        //int rowMain = main.createMain(indexRow, workbook, sheet, day, TravelCardConstance.list);
        int rowMain = main.createMain(indexRow, workbook, sheet, day);

        //add buttn part
        bottom.createBottom(rowMain, workbook, sheet,day);

    }
}
