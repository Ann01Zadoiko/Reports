package com.example.demo.excel.table.bottom;

import com.example.demo.excel.constance.StyleConstance;
import com.example.demo.excel.style.SheetStyle;
import com.example.demo.ticket.sum.SumTicketService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class TableRegularTicketBottom implements ITableRegularBottom {

    private final SumTicketService sumTicketService;

    //insert a bottom part of tickets
    @Override
    public void createBottom(int indexRow, Workbook workbook, Sheet sheet, LocalDate day) {
        CellStyle cellStyle = new SheetStyle().setStyle(workbook, 12, BorderStyle.NONE, false);

        Row amountRow = sheet.createRow(++indexRow);

        Cell amountCell = amountRow.createCell(1);
        amountCell.setCellValue(StyleConstance.AMOUNT);
        amountCell.setCellStyle(cellStyle);

        //insert the cell by count
        amountCell = amountRow.createCell(2);
        amountCell.setCellValue(sumTicketService.sumTickets(day)/15);
        amountCell.setCellStyle(cellStyle);

        //insert the cell by sum
        amountCell = amountRow.createCell(3);
        amountCell.setCellValue(sumTicketService.sumTickets(day));
        amountCell.setCellStyle(cellStyle);
    }
}
