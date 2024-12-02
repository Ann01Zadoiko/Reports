package com.example.demo.excel.table.main;

import com.example.demo.excel.style.SheetStyle;
import com.example.demo.ticket.sum.SumTicketService;
import com.example.demo.track.TrackService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class TableDepoTicketMain {

    private final TrackService trackService;
    private final SumTicketService sumTicketService;

    public int createMain(int indexRow, Workbook workbook, Sheet sheet, LocalDate day, String depo){

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
}
