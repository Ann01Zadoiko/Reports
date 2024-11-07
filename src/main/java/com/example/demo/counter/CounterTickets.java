package com.example.demo.counter;

import com.example.demo.ticket.Ticket;
import com.example.demo.ticket.TicketService;
import com.example.demo.track.Track;
import com.example.demo.track.TrackService;
import com.example.demo.tram.Tram;
import com.example.demo.tram.TramService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CounterTickets implements Counter{

    private final TicketService ticketService;
    private final TramService tramService;
    private final TrackService trackService;


    //use
    @Override
    public int countByDayGeneral(String dayString){
        LocalDate day = LocalDate.parse(dayString);

        return ticketService.getByDay(day).size();
    }

    public int countByDayAndTram(Tram tram, LocalDate day){
        return ticketService.getByTramAndDay(tram, day).size();
    }


    //use
    @Override
    public Map<Tram, Integer> countMapOfTram(LocalDate day, int amount) {
        Map<Tram, Integer> map = new HashMap<>();

        for (Tram tram: tramService.getByDay(day)){
            map.put(tram, ticketService.getByTram(tram).size() * amount);
            System.out.println(tram.getDepo() + " " + tram.getNumberOfTram() + ": " + ticketService.getByTram(tram).size());
        }

        return map;
    }

    public String  createTable(LocalDate day)  throws IOException {

        String filePath = "./src/main/resources/general.xlsx";
        File file = new File(filePath);

        try (Workbook workbook = new XSSFWorkbook();
             FileOutputStream fileOutputStream = new FileOutputStream(file)){

            Sheet sheet = workbook.createSheet("Report by " + day);

            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Depo");
            headerRow.createCell(1).setCellValue("Tram");
            headerRow.createCell(2).setCellValue("Count");
            headerRow.createCell(3).setCellValue("Amount");
            headerRow.createCell(4).setCellValue("Track");


            int indexRow = 1;
            for (Tram tram: tramService.getByDay(day)){
                Row row = sheet.createRow(indexRow++);

                row.createCell(0).setCellValue(tram.getDepo());
                row.createCell(1).setCellValue(tram.getNumberOfTram());
                row.createCell(2).setCellValue(ticketService.getByTramAndDay(tram, day).size());
                row.createCell(3).setCellValue(ticketService.getByTramAndDay(tram, day).size() * 7);

                if (trackService.getByDayAndIdTram(day, tram.getId()) == null){
                    continue;
                } else {
                    row.createCell(4).setCellValue(trackService.getByDayAndIdTram(day, tram.getId()).getTrack());
                }
            }

            Row amountRow = sheet.createRow(++indexRow);
            amountRow.createCell(2).setCellValue("Amount");
            amountRow.createCell(3).setCellValue(ticketService.getByDay(day).size());
            amountRow.createCell(4).setCellValue(ticketService.getByDay(day).size() * 7);

            workbook.write(fileOutputStream);
        }

        return filePath;
    }

}
