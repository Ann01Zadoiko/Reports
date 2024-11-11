package com.example.demo.importData;

import com.example.demo.ticket.Ticket;
import com.example.demo.ticket.TicketService;
import com.example.demo.tram.TramService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class ImportTickets implements Import{

    private final TramService tramService;
    private final TicketService ticketService;

    @Override
    public void importExcelToData(MultipartFile file, String depo, LocalDate day){

        try (InputStream inputStream = file.getInputStream()){

            Sheet sheet;

            if (file.getOriginalFilename().endsWith(".xls")){
                HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
                sheet = workbook.getSheetAt(0);
            } else {
                XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
                sheet = workbook.getSheetAt(0);
            }

            for(Row row: sheet) {
                if (row.getRowNum() == 0){
                    continue;
                }

                String rowDay = row.getCell(1).getStringCellValue();
                String dayString;

                if (rowDay.isEmpty()){
                    break;
                } else {
                    dayString = rowDay;
                }

                String depoT = row.getCell(2).getStringCellValue();
                String numberOfTram = row.getCell(3).getStringCellValue();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime date = LocalDateTime.parse(dayString, formatter);

                Ticket ticket = new Ticket();
                ticket.setDay(date.toLocalDate());
                ticket.setTram(tramService.getByDepoAndNumberOfTram(depoT, numberOfTram));

                ticketService.add(ticket);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
