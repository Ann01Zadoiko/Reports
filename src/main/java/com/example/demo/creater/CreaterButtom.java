package com.example.demo.creater;

import com.example.demo.ticket.Ticket;
import com.example.demo.ticket.TicketService;
import com.example.demo.tram.Tram;
import com.example.demo.tram.TramService;
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

    private final TramService tramService;
    private final TicketService ticketService;

    public void createRowMain(int indexRow, Workbook workbook, Sheet sheet, LocalDate day){

        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);

        XSSFFont fontInner = ((XSSFWorkbook) workbook).createFont();
        fontInner.setFontName("Times New Roman");
        fontInner.setFontHeightInPoints((short) 12);
        cellStyle.setFont(fontInner);

        Row amountRow = sheet.createRow(++indexRow);

        Cell amountCell = amountRow.createCell(1);
        amountCell.setCellValue("Всього");
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
        fontInner.setFontName("Times New Roman");
        fontInner.setFontHeightInPoints((short) 12);
        cellStyle.setFont(fontInner);

        for (int i = 0; i < depo.size(); i++) {
            Row row = sheet.createRow(++indexRow);

            Cell cell = row.createCell(0);
            cell.setCellValue(depo.get(i));
            cell.setCellStyle(cellStyle);

            cell = row.createCell(2);
            cell.setCellValue(ticketService.sumTravelCardByDepo(day, depo.get(i)));
            cell.setCellStyle(cellStyle);
        }

        Row amountRow = sheet.createRow(++indexRow);

        Cell amountCell = amountRow.createCell(1);
        amountCell.setCellValue("Всього");
        amountCell.setCellStyle(cellStyle);

        amountCell = amountRow.createCell(2);
        amountCell.setCellValue(ticketService.sumTravelCard(day));
        amountCell.setCellStyle(cellStyle);
    }
}
