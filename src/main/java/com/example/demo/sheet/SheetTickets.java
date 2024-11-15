package com.example.demo.sheet;

import com.example.demo.creater.CreaterButtom;
import com.example.demo.creater.CreaterHeader;
import com.example.demo.creater.CreaterMain;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SheetTickets {

    private final CreaterMain createrMain;
    private final CreaterButtom createrButtom;

    public void createTable(LocalDate day, Workbook workbook){

        List<String> list = new ArrayList<>();
        list.add("Депо");
        list.add("Вагон");
        list.add("Кількість квітків");
        list.add("Сума");
        list.add("Маршрут 1");
        list.add("Маршрут 2");

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
