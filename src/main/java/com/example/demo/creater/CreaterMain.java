package com.example.demo.creater;

import com.example.demo.ticket.Ticket;
import com.example.demo.ticket.TicketService;
import com.example.demo.track.Track;
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
public class CreaterMain {

    private final TramService tramService;
    private final TicketService ticketService;
    private final TrackService trackService;

    public int createRowMain(int indexRow, Workbook workbook, Sheet sheet, LocalDate day){

        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);

        XSSFFont fontInner = ((XSSFWorkbook) workbook).createFont();
        fontInner.setFontName("Times New Roman");
        fontInner.setFontHeightInPoints((short) 12);
        cellStyle.setFont(fontInner);

        for (Tram tram: tramService.getByDayAndPrice(day)){
            Row row = sheet.createRow(indexRow++);

            Cell cell = row.createCell(0);
            cell.setCellValue(tram.getDepo());
            cell.setCellStyle(cellStyle);

            cell = row.createCell(1);
            cell.setCellValue(tram.getNumberOfTram());
            cell.setCellStyle(cellStyle);

            cell = row.createCell(2);
            cell.setCellValue(ticketService.sumTicketsByTram(day, tram)/7);
            cell.setCellStyle(cellStyle);

            cell = row.createCell(3);
            cell.setCellValue(ticketService.sumTicketsByTram(day, tram));
            cell.setCellStyle(cellStyle);

            Track track = trackService.getByDayAndIdTram(day, tram.getId());

            if (track != null){
                cell = row.createCell(4);
                cell.setCellValue(track.getFirstPart());
                cell.setCellStyle(cellStyle);

                if (track.getSecondPart() != null){
                    cell = row.createCell(5);
                    cell.setCellValue(track.getSecondPart());
                    cell.setCellStyle(cellStyle);
                } else {
                    cell = row.createCell(5);
                    cell.setCellValue("");
                    cell.setCellStyle(cellStyle);
                }
            } else {
                cell = row.createCell(4);
                cell.setCellValue("");
                cell.setCellStyle(cellStyle);
            }
        }
        return indexRow;
    }

    public int createMainTravelCard(int indexRow, Workbook workbook, Sheet sheet, LocalDate day){

        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);

        XSSFFont fontInner = ((XSSFWorkbook) workbook).createFont();
        fontInner.setFontName("Times New Roman");
        fontInner.setFontHeightInPoints((short) 12);
        cellStyle.setFont(fontInner);

        for (Ticket ticket: ticketService.getTravelCards(day)){
            Row row = sheet.createRow(indexRow++);
            Tram tram = ticket.getTram();

            Cell cell = row.createCell(0);
            cell.setCellValue(tram.getDepo());
            cell.setCellStyle(cellStyle);

            cell = row.createCell(1);
            cell.setCellValue(tram.getNumberOfTram());
            cell.setCellStyle(cellStyle);

            cell = row.createCell(2);
            cell.setCellValue(ticket.getPrice());
            cell.setCellStyle(cellStyle);
        }

        return indexRow;
    }

    public int createTicketsByDepo(int indexRow, Workbook workbook, Sheet sheet, LocalDate day, String depo){

        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);

        XSSFFont fontInner = ((XSSFWorkbook) workbook).createFont();
        fontInner.setFontName("Times New Roman");
        fontInner.setFontHeightInPoints((short) 12);
        cellStyle.setFont(fontInner);

        for (String track: trackService.getListTracks(day, depo)){
            Row row = sheet.createRow(indexRow++);

            Cell cell = row.createCell(0);
            cell.setCellValue(track);
            cell.setCellStyle(cellStyle);

            cell = row.createCell(1);
            cell.setCellValue(ticketService.sumByDepoAndTrack(day, depo, track)/7);
            cell.setCellStyle(cellStyle);

            cell = row.createCell(2);
            cell.setCellValue(ticketService.sumByDepoAndTrack(day, depo, track));
            cell.setCellStyle(cellStyle);
        }

        return indexRow;
    }
}
