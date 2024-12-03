package com.example.demo.excel.table.buttom;

import com.example.demo.excel.constance.StyleConstance;
import com.example.demo.excel.style.SheetStyle;
import com.example.demo.ticket.sum.SumTicketService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class TableDepoTicketButtom implements ITableDepoButtom{

    private final SumTicketService sumTicketService;

    //create a button part for tickets by depo
    @Override
    public void createMain(int indexRow, Workbook workbook, Sheet sheet, LocalDate day, String depo) {
        CellStyle cellStyle = new SheetStyle().setStyle(workbook, 12, BorderStyle.NONE, false);

        Row amountRow = sheet.createRow(++indexRow);

        Cell amountCell = amountRow.createCell(0);
        amountCell.setCellValue(StyleConstance.AMOUNT);
        amountCell.setCellStyle(cellStyle);

        //insert the cell by count
        amountCell = amountRow.createCell(1);
        amountCell.setCellValue(sumTicketService.sumTicketsByDepo(day, depo)/15);
        amountCell.setCellStyle(cellStyle);

        //insert the cell by sum
        amountCell = amountRow.createCell(2);
        amountCell.setCellValue(sumTicketService.sumTicketsByDepo(day, depo));
        amountCell.setCellStyle(cellStyle);
    }
}
