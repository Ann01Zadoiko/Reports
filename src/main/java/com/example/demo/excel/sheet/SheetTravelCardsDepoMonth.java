package com.example.demo.excel.sheet;

import com.example.demo.excel.constance.Field;
import com.example.demo.excel.constance.TravelCard;
import com.example.demo.excel.table.TableHeader;
import com.example.demo.excel.table.main.TableDepoCardMonthMain;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class SheetTravelCardsDepoMonth {

    private final TableDepoCardMonthMain main;

    //create a sheet for travel card by depo
    public void createTable(Workbook workbook, String depo, String month, String year){

        List<String> travelCards = new ArrayList<>(Arrays.stream(TravelCard.values()).map(TravelCard::getFullName).toList());

        List<String> list = new ArrayList<>();
        list.add(Field.DATE.getFullName());
        list.addAll(travelCards);
        list.add(Field.AMOUNT.getFullName());

        //name of the sheet
        Sheet sheet = workbook.createSheet("За " + month + "." + year + " qaqa");
      //  sheet.setColumnWidth(0, 10000);
      //  sheet.setColumnWidth(1, 6000);

        //add the header of the sheet
        new TableHeader().createHeaderTickets(workbook, sheet, list);

        int indexRow = 1;

        //add the main part of the sheet
        int rowMain = main.createMain(indexRow, workbook, sheet, depo, month, year);

        //amount for month
        //use formule

    }
}
