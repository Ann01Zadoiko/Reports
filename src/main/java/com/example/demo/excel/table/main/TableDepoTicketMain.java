package com.example.demo.excel.table.main;

import com.example.demo.excel.style.SheetStyle;
import com.example.demo.ticket.sum.SumTicketService;
import com.example.demo.track.TrackService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TableDepoTicketMain {

    private final TrackService trackService;
    private final SumTicketService sumTicketService;

    //create a main part of tickets by depo
    public int createMain(int indexRow, Workbook workbook, Sheet sheet, LocalDate day, String depo){

        CellStyle cellStyle = new SheetStyle().setStyle(workbook, 12, BorderStyle.NONE, false);

        List<String> listTracks = trackService.getListTracks(day, depo);

        Collections.sort(listTracks, (track1, track2) -> {
            return Integer.parseInt(track1) - Integer.parseInt(track2);
        });

        for (String track: listTracks){
            Row row = sheet.createRow(indexRow++);

            //insert the cell by track
            Cell cell = row.createCell(0);
            cell.setCellValue(track);
            cell.setCellStyle(cellStyle);

            //insert the cell by count of tickets
            cell = row.createCell(1);
            cell.setCellValue(sumTicketService.sumTicketsByDepoAndTrack(day, depo, track)/15);
            cell.setCellStyle(cellStyle);

            //insert the cell by sum of tickets
            cell = row.createCell(2);
            cell.setCellValue(sumTicketService.sumTicketsByDepoAndTrack(day, depo, track));
            cell.setCellStyle(cellStyle);
        }

        return indexRow;
    }
}
