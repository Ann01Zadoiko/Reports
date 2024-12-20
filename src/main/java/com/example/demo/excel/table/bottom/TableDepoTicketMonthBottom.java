package com.example.demo.excel.table.bottom;

import com.example.demo.excel.constance.Style;
import com.example.demo.excel.constance.TrackDepo1;
import com.example.demo.excel.style.SheetStyle;
import com.example.demo.ticket.sum.SumTicketService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TableDepoTicketMonthBottom {

    private final SumTicketService sumTicketService;

    //create a button part for tickets by depo
    public void createBottom(int indexRow, Workbook workbook, Sheet sheet, String depo, String month, String year) {
        CellStyle cellStyle = new SheetStyle().setStyle(workbook, 12, BorderStyle.NONE, false);

        Row amountRow = sheet.createRow(++indexRow);

        Cell amountCell = amountRow.createCell(0);
        amountCell.setCellValue(Style.AMOUNT.getFullName());
        amountCell.setCellStyle(cellStyle);

        int i = 1;
        //insert the cell by count
        for (TrackDepo1 trackDepo1: TrackDepo1.values()){
            amountCell = amountRow.createCell(i++);
            amountCell.setCellValue(sumTicketService.sumTicketsByDepoMonthTrack(month, year, depo, trackDepo1.getTrack()));
            amountCell.setCellStyle(cellStyle);
        }

        //insert the cell by sum
        amountCell = amountRow.createCell(i);
        amountCell.setCellValue(sumTicketService.sumTicketsByDepoMonth(month, year, depo));
        amountCell.setCellStyle(cellStyle);
    }
}
