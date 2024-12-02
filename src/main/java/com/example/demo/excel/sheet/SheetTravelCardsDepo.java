package com.example.demo.excel.sheet;

import com.example.demo.excel.constance.FieldConstance;
import com.example.demo.excel.constance.TravelCardConstance;
import com.example.demo.excel.table.TableHeader;
import com.example.demo.excel.table.buttom.TableDepoCardButtom;
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
    private final TableDepoCardButtom buttom;

    public void createTable(LocalDate day, Workbook workbook, String depo){

        List<String> list = Arrays.asList(
                FieldConstance.TRAVEL_CARD, FieldConstance.COUNT_OF_TICKETS, FieldConstance.AMOUNT);

        Sheet sheet = workbook.createSheet("Проїздні за " + depo);
        sheet.setColumnWidth(0, 8000);
        sheet.setColumnWidth(1, 6000);

        new TableHeader().createHeaderTickets(workbook, sheet, list);

        int indexRow = 1;

        int rowMain = main.createMain(indexRow, workbook, sheet, day, TravelCardConstance.list, depo);

        buttom.createMain(rowMain, workbook, sheet,day, depo);

    }
}
