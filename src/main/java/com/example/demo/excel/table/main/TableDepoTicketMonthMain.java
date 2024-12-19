package com.example.demo.excel.table.main;

import com.example.demo.excel.constance.TrackDepo1;
import com.example.demo.excel.style.SheetStyle;
import com.example.demo.ticket.TicketService;
import com.example.demo.ticket.sum.SumTicketService;
import com.example.demo.track.TrackService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TableDepoTicketMonthMain {

    private final SumTicketService sumTicketService;
    private final TicketService ticketService;

    //create a main part of tickets by depo
    public int createMain(int indexRow, Workbook workbook, Sheet sheet, String depo, String month, String year){

        CellStyle cellStyle = new SheetStyle().setStyle(workbook, 12, BorderStyle.NONE, false);

        List<LocalDate> listOfDaysByMonth = ticketService.getDaysOfMoth(month, year);

        for (LocalDate day: listOfDaysByMonth){
            Row row = sheet.createRow(indexRow++);

            Cell cell = row.createCell(0);
            cell.setCellValue(day.getDayOfMonth() + "." + day.getMonthValue());
            cell.setCellStyle(cellStyle);

            int i = 1;
            for (TrackDepo1 trackDepo1: TrackDepo1.values()) {
                cell = row.createCell(i);
                cell.setCellValue(sumTicketService.sumTicketsByDepoAndTrack(day, depo, trackDepo1.getTrack()));
                cell.setCellStyle(cellStyle);
                i++;
            }

            cell = row.createCell(i);
            cell.setCellValue(sumTicketService.sumTicketsByDepo(day, depo));
            cell.setCellStyle(cellStyle);
        }

        return indexRow;
    }

}
