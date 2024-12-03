package com.example.demo.excel.table.main;

import com.example.demo.excel.style.SheetStyle;
import com.example.demo.ticket.count.CountTravelCardService;
import com.example.demo.ticket.sum.SumTravelCardService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TableDepoCardMain {

    private final CountTravelCardService countTravelCardService;
    private final SumTravelCardService sumTravelCardService;

    //create a main part of travel card by depo
    public int createMain(int indexRow, Workbook workbook, Sheet sheet, LocalDate day, List<String> list, String depo){

        CellStyle cellStyle = new SheetStyle().setStyle(workbook, 12, BorderStyle.NONE, false);

        for (String s: list){
            Row row = sheet.createRow(indexRow++);

            //insert the cell by type of tickets
            Cell cell = row.createCell(0);
            cell.setCellValue(s);
            cell.setCellStyle(cellStyle);

            //insert the cell by count of travel card
            cell = row.createCell(1);
            cell.setCellValue(countTravelCardService.countByTravelCardAndDayAndDepo(day, s, depo));
            cell.setCellStyle(cellStyle);

            //insert the cell by sum of travel card
            cell = row.createCell(2);
            cell.setCellValue(sumTravelCardService.sumByTravelCardAndDayAndDepo(day, s, depo));
            cell.setCellStyle(cellStyle);
        }

        return indexRow;
    }
}
