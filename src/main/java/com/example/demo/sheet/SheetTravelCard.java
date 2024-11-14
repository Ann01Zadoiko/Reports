package com.example.demo.sheet;

import com.example.demo.combine.Combine;
import com.example.demo.creater.CreaterButtom;
import com.example.demo.creater.CreaterFile;
import com.example.demo.creater.CreaterHeader;
import com.example.demo.creater.CreaterMain;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SheetTravelCard {

    private final CreaterMain createrMain;
    private final CreaterButtom createrButtom;

    public void createTable(LocalDate day, Workbook workbook, List<String> depo) throws IOException {

        List<String> list = new ArrayList<>();
        list.add("Депо");
        list.add("Вагон");
        list.add("Ціна");

        Sheet sheet = workbook.createSheet("Проїздні за " + day);
        sheet.setColumnWidth(0, 6000);

        new CreaterHeader().createHeaderTickets(workbook, sheet, list);

        int indexRow = 1;

        int rowMain = createrMain.createMainTravelCard(indexRow, workbook, sheet, day);
            createrButtom.createTravelCard(rowMain, workbook, sheet,day, depo);

    }
}
