package com.example.demo.excel.table.bottom;

import com.example.demo.excel.constance.Style;
import com.example.demo.excel.style.SheetStyle;
import com.example.demo.ticket.count.CountTravelCardService;
import com.example.demo.ticket.sum.SumTravelCardService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class TableRegularCardBottom implements ITableRegularBottom {

    private final CountTravelCardService countTravelCardService;
    private final SumTravelCardService sumTravelCardService;

    //create a button part for travel cards
    @Override
    public void createBottom(int indexRow, Workbook workbook, Sheet sheet, LocalDate day) {
        CellStyle cellStyle = new SheetStyle().setStyle(workbook, 12, BorderStyle.NONE, false);

        Row amountRow = sheet.createRow(++indexRow);

        Cell amountCell = amountRow.createCell(0);
        amountCell.setCellValue(Style.AMOUNT.getFullName());
        amountCell.setCellStyle(cellStyle);

        //insert the cell by count
        amountCell = amountRow.createCell(1);
        amountCell.setCellValue(countTravelCardService.countTravelCard(day));
        amountCell.setCellStyle(cellStyle);

        //insert the cell by sum
        amountCell = amountRow.createCell(2);
        amountCell.setCellValue(sumTravelCardService.sumTravelCard(day));
        amountCell.setCellStyle(cellStyle);
    }
}
