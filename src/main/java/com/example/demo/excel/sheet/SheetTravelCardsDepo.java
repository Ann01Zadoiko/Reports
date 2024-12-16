package com.example.demo.excel.sheet;

import com.example.demo.excel.constance.Field;
import com.example.demo.excel.table.TableHeader;
import com.example.demo.excel.table.bottom.TableDepoCardBottom;
import com.example.demo.excel.table.main.TableDepoCardMain;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SheetTravelCardsDepo {

    private final TableDepoCardMain main;
    private final TableDepoCardBottom bottom;

    //create a sheet for travel card by depo
    public void createTable(LocalDate day, Workbook workbook, String depo){

        //list of header
        List<String> list = Arrays.asList(
                Field.TRAVEL_CARD.getFullName(),
                Field.COUNT_OF_TICKETS.getFullName(),
                Field.AMOUNT.getFullName());

        //name of the sheet
        Sheet sheet = workbook.createSheet("Проїздні за " + depo);
        sheet.setColumnWidth(0, 10000);
        sheet.setColumnWidth(1, 6000);

        //add the header of the sheet
        new TableHeader().createHeaderTickets(workbook, sheet, list);

        int indexRow = 1;

        //add the main part of the sheet
        int rowMain = main.createMain(indexRow, workbook, sheet, day, depo);

        //add the button of the sheet
        bottom.createBottom(rowMain, workbook, sheet,day, depo);

    }
}
