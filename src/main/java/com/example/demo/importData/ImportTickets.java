package com.example.demo.importData;

import com.example.demo.ticket.TicketService;
import com.example.demo.tram.TramService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
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
public class ImportTickets {

    private final TramService tramService;
    private final TicketService ticketService;

    public void importExcelToData(MultipartFile file){

        try (InputStream inputStream = file.getInputStream()){
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheetAt(0);

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

                String depo = row.getCell(2).getStringCellValue();
                String numberOfTram = row.getCell(3).getStringCellValue();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime day = LocalDateTime.parse(dayString, formatter);

                ticketService.add(day.toLocalDate(), tramService.getByDepoAndNumberOfTram(depo, numberOfTram));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void importExcelToDataXls(MultipartFile file){

        try (InputStream inputStream = file.getInputStream()){
            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
            HSSFSheet sheet = workbook.getSheetAt(0);

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

                String depo = row.getCell(2).getStringCellValue();
                String numberOfTram = row.getCell(3).getStringCellValue();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime day = LocalDateTime.parse(dayString, formatter);

                ticketService.add(day.toLocalDate(), tramService.getByDepoAndNumberOfTram(depo, numberOfTram));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
