package com.example.demo.excel.table.main;

import com.example.demo.excel.style.SheetStyle;
import com.example.demo.ticket.sum.SumTicketService;
import com.example.demo.track.Track;
import com.example.demo.track.TrackService;
import com.example.demo.tram.Tram;
import com.example.demo.tram.TramService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class TableRegularTicketMain {

    private final TramService tramService;
    private final SumTicketService sumTicketService;
    private final TrackService trackService;

    //create a main part of the sheet for tickets
    public int createMain(int indexRow, Workbook workbook, Sheet sheet, LocalDate day){

        CellStyle cellStyle = new SheetStyle().setStyle(workbook, 12, BorderStyle.NONE, false);

        Set<Tram> byDayAndPrice = tramService.getByDayAndPrice(day);
        List<Tram> list = new ArrayList<>(byDayAndPrice);

        Comparator<Tram> comparing = Comparator.comparing(Tram::getDepo);
        list.sort(comparing);

        for (Tram tram: list){
            Row row = sheet.createRow(indexRow++);

            //insert the cell by depo
            Cell cell = row.createCell(0);
            cell.setCellValue(tram.getDepo());
            cell.setCellStyle(cellStyle);

            //insert the cell by number of tram
            cell = row.createCell(1);
            cell.setCellValue(tram.getNumberOfTram());
            cell.setCellStyle(cellStyle);

            //insert the cell by count of tickets
            cell = row.createCell(2);
            cell.setCellValue(sumTicketService.sumTicketsByTram(day, tram) /15);
            cell.setCellStyle(cellStyle);

            //insert the cell by sum of tickets
            cell = row.createCell(3);
            cell.setCellValue(sumTicketService.sumTicketsByTram(day, tram));
            cell.setCellStyle(cellStyle);

            Track track = trackService.getByDayAndIdTram(day, tram.getId());

            //insert cells by track
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
}
