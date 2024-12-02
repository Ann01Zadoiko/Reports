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

    public void createTable(LocalDate day, Workbook workbook){
        List<String> list = Arrays.asList(
            FieldConstance.TRAVEL_CARD, FieldConstance.COUNT_OF_TICKETS, FieldConstance.AMOUNT);

        Sheet sheet = workbook.createSheet("Всі проїздні");
        sheet.setColumnWidth(0, 6000);
        sheet.setColumnWidth(1, 6000);

        new TableHeader().createHeaderTickets(workbook, sheet, list);

        int indexRow = 1;

        int rowMain = main.createMain(indexRow, workbook, sheet, day, TravelCardConstance.list);

        buttom.createMain(rowMain, workbook, sheet,day);

    }
}
