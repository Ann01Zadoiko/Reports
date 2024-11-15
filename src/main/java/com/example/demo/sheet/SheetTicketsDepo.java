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
public class SheetTicketsDepo {

    private final CreaterMain createrMain;
    private final CreaterButtom createrButtom;

    public void createTable(LocalDate day, Workbook workbook, String depo){

        List<String> list = new ArrayList<>();
        list.add("Маршнут");
        list.add("Кількість квітків");
        list.add("Сума");

        Sheet sheet = workbook.createSheet(depo);
        sheet.setColumnWidth(0, 4000);
        sheet.setColumnWidth(1, 6000);

        new CreaterHeader().createHeaderTickets(workbook, sheet, list);

        int indexRow = 1;

        int rowMain = createrMain.createTicketsByDepo(indexRow, workbook, sheet, day, depo);

        createrButtom.createTicketsByDepo(rowMain, workbook, sheet,day, depo);

    }
}
