package com.example.demo.excel.table.bottom;

import com.example.demo.excel.constance.Style;
import com.example.demo.excel.constance.TravelCard;
import com.example.demo.excel.style.SheetStyle;
import com.example.demo.ticket.sum.SumTravelCardService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class TableDepoCardMonthBottom {

    private final SumTravelCardService sumTravelCardService;

    //create a button part for travel cards by depo
    public void createBottom(int indexRow, Workbook workbook, Sheet sheet, String depo, String month, String year) {
        CellStyle cellStyle = new SheetStyle().setStyle(workbook, 12, BorderStyle.NONE, false);

        Row amountRow = sheet.createRow(++indexRow);

        Cell amountCell = amountRow.createCell(0);
        amountCell.setCellValue(Style.AMOUNT.getFullName());
        amountCell.setCellStyle(cellStyle);

        int i = 1;
        for (TravelCard travelCard: TravelCard.values()){
            amountCell = amountRow.createCell(i++);
            amountCell.setCellStyle(cellStyle);
            amountCell.setCellValue(sumTravelCardService.sumByTravelCardMonthDepo(month, year, depo, travelCard.getFullName()));
        }

        //insert the cell by sum
        amountCell = amountRow.createCell(2);
        amountCell.setCellValue(sumTravelCardService.sumByMonthDepo(month, year, depo));
        amountCell.setCellStyle(cellStyle);
    }
}
