package com.example.demo.excel.sheet;

import com.example.demo.excel.constance.FieldConstance;
import com.example.demo.excel.constance.TravelCardConstance;
import com.example.demo.excel.table.TableHeader;
import com.example.demo.excel.table.buttom.TableRegularCardButtom;
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
    private final TableRegularCardButtom buttom;

    //create the sheet for travel cards
    public void createTable(LocalDate day, Workbook workbook){
        //list of headers
        List<String> list = Arrays.asList(
            FieldConstance.TRAVEL_CARD, FieldConstance.COUNT_OF_TICKETS, FieldConstance.AMOUNT);

        //set name for the sheet
        Sheet sheet = workbook.createSheet("Всі проїздні");
        sheet.setColumnWidth(0, 10000);
        sheet.setColumnWidth(1, 6000);

        //add header
        new TableHeader().createHeaderTickets(workbook, sheet, list);

        int indexRow = 1;

        //add main part
        int rowMain = main.createMain(indexRow, workbook, sheet, day, TravelCardConstance.list);

        //add buttn part
        buttom.createMain(rowMain, workbook, sheet,day);

    }
}
