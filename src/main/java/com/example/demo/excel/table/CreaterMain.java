package com.example.demo.excel.table;

import com.example.demo.excel.style.SheetStyle;
import com.example.demo.ticket.Ticket;
import com.example.demo.ticket.TicketService;
import com.example.demo.ticket.count.CountTravelCardService;
import com.example.demo.ticket.sum.SumTicketService;
import com.example.demo.ticket.sum.SumTravelCardService;
import com.example.demo.track.Track;
import com.example.demo.track.TrackService;
import com.example.demo.tram.Tram;
import com.example.demo.tram.TramService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CreaterMain {

    private final TramService tramService;
    private final TicketService ticketService;
    private final TrackService trackService;
    private final CountTravelCardService countTravelCardService;
    private final SumTicketService sumTicketService;
    private final SumTravelCardService sumTravelCardService;

    //создание основной части таблицы из базы данных
    public int createRowMain(int indexRow, Workbook workbook, Sheet sheet, LocalDate day){

        CellStyle cellStyle = new SheetStyle().setStyle(workbook, 12, BorderStyle.NONE, false);

        for (Tram tram: tramService.getByDayAndPrice(day)){
            Row row = sheet.createRow(indexRow++);

            Cell cell = row.createCell(0);
            cell.setCellValue(tram.getDepo());
            cell.setCellStyle(cellStyle);

            cell = row.createCell(1);
            cell.setCellValue(tram.getNumberOfTram());
            cell.setCellStyle(cellStyle);

            cell = row.createCell(2);
            cell.setCellValue(sumTicketService.sumTicketsByTram(day, tram) /15);
            cell.setCellStyle(cellStyle);

            cell = row.createCell(3);
            cell.setCellValue(sumTicketService.sumTicketsByTram(day, tram));
            cell.setCellStyle(cellStyle);

            Track track = trackService.getByDayAndIdTram(day, tram.getId());

            cell = row.createCell(4);
            if (track != null){
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
                cell.setCellValue("");
                cell.setCellStyle(cellStyle);
            }
        }
        return indexRow;
    }

    public int createMainTravelCard(int indexRow, Workbook workbook, Sheet sheet, LocalDate day){

        CellStyle cellStyle = new SheetStyle().setStyle(workbook, 12, BorderStyle.NONE, false);

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

        CellStyle cellStyle = new SheetStyle().setStyle(workbook, 12, BorderStyle.NONE, false);

        for (String track: trackService.getListTracks(day, depo)){
            Row row = sheet.createRow(indexRow++);

            Cell cell = row.createCell(0);
            cell.setCellValue(track);
            cell.setCellStyle(cellStyle);

            cell = row.createCell(1);
            cell.setCellValue(sumTicketService.sumTicketsByDepoAndTrack(day, depo, track)/15);
            cell.setCellStyle(cellStyle);

            cell = row.createCell(2);
            cell.setCellValue(sumTicketService.sumTicketsByDepoAndTrack(day, depo, track));
            cell.setCellStyle(cellStyle);
        }

        return indexRow;
    }

    public int createTravelCards(int indexRow, Workbook workbook, Sheet sheet, LocalDate day, List<String> list){

        CellStyle cellStyle = new SheetStyle().setStyle(workbook, 12, BorderStyle.NONE, false);

        for (String s: list){
            Row row = sheet.createRow(indexRow++);

            Cell cell = row.createCell(0);
            cell.setCellValue(s);
            cell.setCellStyle(cellStyle);

            cell = row.createCell(1);
            cell.setCellValue(countTravelCardService.countByTravelCardAndDay(day, s));
            cell.setCellStyle(cellStyle);

            cell = row.createCell(2);
            cell.setCellValue(sumTravelCardService.sumByTravelCardAndDay(day, s));
            cell.setCellStyle(cellStyle);
        }

        return indexRow;
    }

    public int createTravelCardsDepo(int indexRow, Workbook workbook, Sheet sheet, LocalDate day, List<String> list, String depo){

        CellStyle cellStyle = new SheetStyle().setStyle(workbook, 12, BorderStyle.NONE, false);

        for (String s: list){
            Row row = sheet.createRow(indexRow++);

            Cell cell = row.createCell(0);
            cell.setCellValue(s);
            cell.setCellStyle(cellStyle);

            cell = row.createCell(1);
            cell.setCellValue(countTravelCardService.countByTravelCardAndDayAndDepo(day, s, depo));
            cell.setCellStyle(cellStyle);

            cell = row.createCell(2);
            cell.setCellValue(sumTravelCardService.sumByTravelCardAndDayAndDepo(day, s, depo));
            cell.setCellStyle(cellStyle);
        }

        return indexRow;
    }
}
