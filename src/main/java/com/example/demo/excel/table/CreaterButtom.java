package com.example.demo.excel.table;

import com.example.demo.excel.constance.StyleConstance;
import com.example.demo.excel.style.SheetStyle;
import com.example.demo.ticket.TicketService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CreaterButtom {

    private final TicketService ticketService;

    public void createRowMain(int indexRow, Workbook workbook, Sheet sheet, LocalDate day){

        CellStyle cellStyle = new SheetStyle().setStyle(workbook, 12, BorderStyle.NONE, false);

        Row amountRow = sheet.createRow(++indexRow);

        Cell amountCell = amountRow.createCell(1);
        amountCell.setCellValue(StyleConstance.AMOUNT);
        amountCell.setCellStyle(cellStyle);

        amountCell = amountRow.createCell(2);
        amountCell.setCellValue(ticketService.sumTickets(day)/7);
        amountCell.setCellStyle(cellStyle);

        amountCell = amountRow.createCell(3);
        amountCell.setCellValue(ticketService.sumTickets(day));
        amountCell.setCellStyle(cellStyle);
    }

    public void createTravelCard(int indexRow, Workbook workbook, Sheet sheet, LocalDate day, List<String> depo){

        CellStyle cellStyle = new SheetStyle().setStyle(workbook, 12, BorderStyle.NONE, false);

        for (String s : depo) {
            Row row = sheet.createRow(++indexRow);

            Cell cell = row.createCell(0);
            cell.setCellValue(s);
            cell.setCellStyle(cellStyle);

            cell = row.createCell(2);
            cell.setCellValue(ticketService.sumTravelCardByDepo(day, s));
            cell.setCellStyle(cellStyle);
        }

        Row amountRow = sheet.createRow(++indexRow);

        Cell amountCell = amountRow.createCell(1);
        amountCell.setCellValue(StyleConstance.AMOUNT);
        amountCell.setCellStyle(cellStyle);

        amountCell = amountRow.createCell(2);
        amountCell.setCellValue(ticketService.sumTravelCard(day));
        amountCell.setCellStyle(cellStyle);
    }

    public void createTicketsByDepo(int indexRow, Workbook workbook, Sheet sheet, LocalDate day, String depo){

        CellStyle cellStyle = new SheetStyle().setStyle(workbook, 12, BorderStyle.NONE, false);

        Row amountRow = sheet.createRow(++indexRow);

        Cell amountCell = amountRow.createCell(0);
        amountCell.setCellValue(StyleConstance.AMOUNT);
        amountCell.setCellStyle(cellStyle);

        amountCell = amountRow.createCell(1);
        amountCell.setCellValue(ticketService.sumTicketsByDepo(day, depo)/7);
        amountCell.setCellStyle(cellStyle);

        amountCell = amountRow.createCell(2);
        amountCell.setCellValue(ticketService.sumTicketsByDepo(day, depo));
        amountCell.setCellStyle(cellStyle);
    }

    public void createTravelCards(int indexRow, Workbook workbook, Sheet sheet, LocalDate day){

        CellStyle cellStyle = new SheetStyle().setStyle(workbook, 12, BorderStyle.NONE, false);

        Row amountRow = sheet.createRow(++indexRow);

        Cell amountCell = amountRow.createCell(0);
        amountCell.setCellValue(StyleConstance.AMOUNT);
        amountCell.setCellStyle(cellStyle);

        amountCell = amountRow.createCell(1);
        amountCell.setCellValue(ticketService.countTravelCards(day));
        amountCell.setCellStyle(cellStyle);

        amountCell = amountRow.createCell(2);
        amountCell.setCellValue(ticketService.sumTravelCard(day));
        amountCell.setCellStyle(cellStyle);
    }

    public void createTravelCardsDepo(int indexRow, Workbook workbook, Sheet sheet, LocalDate day, String depo){

        CellStyle cellStyle = new SheetStyle().setStyle(workbook, 12, BorderStyle.NONE, false);

        Row amountRow = sheet.createRow(++indexRow);

        Cell amountCell = amountRow.createCell(0);
        amountCell.setCellValue(StyleConstance.AMOUNT);
        amountCell.setCellStyle(cellStyle);

        amountCell = amountRow.createCell(1);
        amountCell.setCellValue(ticketService.countTravelCardDepo(day,depo));
        amountCell.setCellStyle(cellStyle);

        amountCell = amountRow.createCell(2);
        amountCell.setCellValue(ticketService.sumTravelCardDepo(day, depo));
        amountCell.setCellStyle(cellStyle);
    }
}
