package com.example.demo.creater;

import com.example.demo.constance.StyleConstance;
import com.example.demo.ticket.TicketService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CreaterButtom {

    private final TicketService ticketService;

    public void createRowMain(int indexRow, Workbook workbook, Sheet sheet, LocalDate day){

        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);

        XSSFFont fontInner = ((XSSFWorkbook) workbook).createFont();
        fontInner.setFontName(StyleConstance.FONT_NAME);
        fontInner.setFontHeightInPoints((short) 12);
        cellStyle.setFont(fontInner);

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

        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);

        XSSFFont fontInner = ((XSSFWorkbook) workbook).createFont();
        fontInner.setFontName(StyleConstance.FONT_NAME);
        fontInner.setFontHeightInPoints((short) 12);
        cellStyle.setFont(fontInner);

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

        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);

        XSSFFont fontInner = ((XSSFWorkbook) workbook).createFont();
        fontInner.setFontName(StyleConstance.FONT_NAME);
        fontInner.setFontHeightInPoints((short) 12);
        cellStyle.setFont(fontInner);

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
}
