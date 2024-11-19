package com.example.demo.sheet;


import com.example.demo.constance.FieldConstance;
import com.example.demo.creater.CreaterButtom;

import com.example.demo.creater.CreaterHeader;
import com.example.demo.creater.CreaterMain;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SheetTravelCard {

    private final CreaterMain createrMain;
    private final CreaterButtom createrButtom;

    public void createTable(LocalDate day, Workbook workbook, List<String> depo) {

        List<String> list = Arrays.asList(
                FieldConstance.DEPO, FieldConstance.TRAM, FieldConstance.PRICE
        );

        Sheet sheet = workbook.createSheet("Проїздні за " + day);
        sheet.setColumnWidth(0, 6000);

        new CreaterHeader().createHeaderTickets(workbook, sheet, list);

        int indexRow = 1;

        int rowMain = createrMain.createMainTravelCard(indexRow, workbook, sheet, day);
            createrButtom.createTravelCard(rowMain, workbook, sheet,day, depo);

    }
}
