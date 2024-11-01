package com.example.demo.importData;

import com.example.demo.ticket.TicketService;
import com.example.demo.tram.TramService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;

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

                LocalDate day = row.getCell(1).getLocalDateTimeCellValue().toLocalDate();
                String depo = row.getCell(2).getStringCellValue();
                int numberOfTram = (int) row.getCell(3).getNumericCellValue();

                ticketService.add(day, tramService.getByDepoAndNumberOfTram(depo, numberOfTram));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
