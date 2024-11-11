package com.example.demo.counter;

import com.example.demo.combine.Combine;
import com.example.demo.ticket.TicketService;
import com.example.demo.track.TrackService;
import com.example.demo.tram.Tram;
import com.example.demo.tram.TramService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CounterTickets implements Counter{

    private final TicketService ticketService;
    private final TramService tramService;
    private final TrackService trackService;
    private final Combine combine;


    //use
    @Override
    public int countByDayGeneral(String dayString){
        LocalDate day = LocalDate.parse(dayString);

        return ticketService.getByDay(day).size();
    }

    //use
    @Override
    public Map<Tram, Integer> countMapOfTram(LocalDate day, int amount) {

        combine.combineLong(day);

        Map<Tram, Integer> map = new HashMap<>();

        for (Tram tram: tramService.getByDay(day)){
            map.put(tram, ticketService.getByTram(tram).size() * amount);
            System.out.println(tram.getDepo() + " " + tram.getNumberOfTram() + ": " + ticketService.getByTram(tram).size());
        }

        return map;
    }

    public String createTable(LocalDate day)  throws IOException {

        StringBuilder builder = new StringBuilder()
                .append("./src/main/resources/reports/general_")
                .append(day)
                .append(".xlsx");

        File file = new File(String.valueOf(builder));

        try (Workbook workbook = new XSSFWorkbook();
             FileOutputStream fileOutputStream = new FileOutputStream(file)){

            Sheet sheet = workbook.createSheet("Звіт за " + day);
            sheet.setColumnWidth(0, 6000);
            sheet.setColumnWidth(2, 6000);
            sheet.setColumnWidth(4, 4000);

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

            int indexRow = 1;

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


            workbook.write(fileOutputStream);
        }

        return String.valueOf(builder);
    }

}
