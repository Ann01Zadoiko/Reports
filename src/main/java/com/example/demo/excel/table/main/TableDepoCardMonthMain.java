package com.example.demo.excel.table.main;

import com.example.demo.excel.constance.TravelCard;
import com.example.demo.excel.style.SheetStyle;
import com.example.demo.ticket.TicketService;
import com.example.demo.ticket.sum.SumTravelCardService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TableDepoCardMonthMain {

    private final TicketService ticketService;
    private final SumTravelCardService sumTravelCardService;

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
            for (TravelCard travelCard: TravelCard.values()){
                cell = row.createCell(i);
                cell.setCellValue(sumTravelCardService.sumByTravelCardAndDayAndDepo(day, travelCard.getFullName(), depo));
                cell.setCellStyle(cellStyle);
                i++;
            }

            cell = row.createCell(i);
            cell.setCellValue(sumTravelCardService.sumTravelCardDepo(day, depo));
            cell.setCellStyle(cellStyle);
        }

        return indexRow;
    }

}
