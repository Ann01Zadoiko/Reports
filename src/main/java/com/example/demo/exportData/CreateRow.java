package com.example.demo.exportData;

import com.example.demo.ticket.TicketService;
import com.example.demo.track.TrackService;
import com.example.demo.tram.Tram;
import com.example.demo.tram.TramService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class CreateRow {

    private final TramService tramService;
    private final TicketService ticketService;
    private final TrackService trackService;

    public void createHeader(Workbook workbook, Sheet sheet){
        Row headerRow = sheet.createRow(0);

        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setBorderBottom(BorderStyle.MEDIUM);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);

        XSSFFont font = ((XSSFWorkbook) workbook).createFont();
        font.setFontName("Times New Roman");
        font.setFontHeightInPoints((short) 14);
        font.setBold(true);
        headerStyle.setFont(font);

        Cell headerCell = headerRow.createCell(0);
        headerCell.setCellValue("Депо");
        headerCell.setCellStyle(headerStyle);

        headerCell = headerRow.createCell(1);
        headerCell.setCellValue("Вагон");
        headerCell.setCellStyle(headerStyle);

        headerCell = headerRow.createCell(2);
        headerCell.setCellValue("Кількість квітків");
        headerCell.setCellStyle(headerStyle);

        headerCell = headerRow.createCell(3);
        headerCell.setCellValue("Сума");
        headerCell.setCellStyle(headerStyle);

        headerCell = headerRow.createCell(4);
        headerCell.setCellValue("Маршрут");
        headerCell.setCellStyle(headerStyle);
    }

    public void createRowMain(int indexRow, Workbook workbook, Sheet sheet, LocalDate day){

        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);

        XSSFFont fontInner = ((XSSFWorkbook) workbook).createFont();
        fontInner.setFontName("Times New Roman");
        fontInner.setFontHeightInPoints((short) 12);
        cellStyle.setFont(fontInner);

        for (Tram tram: tramService.getByDay(day)){
            Row row = sheet.createRow(indexRow++);

            Cell cell = row.createCell(0);
            cell.setCellValue(tram.getDepo());
            cell.setCellStyle(cellStyle);

            cell = row.createCell(1);
            cell.setCellValue(tram.getNumberOfTram());
            cell.setCellStyle(cellStyle);

            cell = row.createCell(2);
            cell.setCellValue(ticketService.getByTramAndDay(tram, day).size());
            cell.setCellStyle(cellStyle);

            cell = row.createCell(3);
            cell.setCellValue((ticketService.getByTramAndDay(tram, day).size() * 7));
            cell.setCellStyle(cellStyle);

            if (trackService.getByDayAndIdTram(day, tram.getId()) == null){
                continue;
            } else {
                cell = row.createCell(4);
                cell.setCellValue(trackService.getByDayAndIdTram(day, tram.getId()).getTrack());
                cell.setCellStyle(cellStyle);
            }

        }

        Row amountRow = sheet.createRow(++indexRow);

        Cell amountCell = amountRow.createCell(1);
        amountCell.setCellValue("Всього");
        amountCell.setCellStyle(cellStyle);

        amountCell = amountRow.createCell(2);
        amountCell.setCellValue(ticketService.getByDay(day).size());
        amountCell.setCellStyle(cellStyle);

        amountCell = amountRow.createCell(3);
        amountCell.setCellValue(ticketService.getByDay(day).size() * 7);
        amountCell.setCellStyle(cellStyle);
    }
}
