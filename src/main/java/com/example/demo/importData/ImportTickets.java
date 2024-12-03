package com.example.demo.importData;

import com.example.demo.file.FileTicket;
import com.example.demo.file.FileTicketService;
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
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ImportTickets implements Import{

    private final TramService tramService;
    private final TicketService ticketService;
    private final FileTicketService fileTicketService;

    //import data from a file into tickets (table) by depo and day
    @Override
    public void importExcelToData(MultipartFile file, String depo, LocalDate day){

        //processing an incoming file
        try (InputStream inputStream = file.getInputStream()){

            Sheet sheet;

            FileTicket fileTicket = new FileTicket();
            fileTicket.setName(file.getOriginalFilename());
            fileTicketService.add(fileTicket);

            //check format of the file
            if (Objects.requireNonNull(file.getOriginalFilename()).endsWith(".xls") && !(file.isEmpty())){
                //create a sheet
                HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
                sheet = workbook.getSheetAt(0);
            } else {
                XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
                sheet = workbook.getSheetAt(0);
            }

            //read data and selection data from cell
            for(Row row: sheet) {

                //skip 0-row
                if (row.getRowNum() == 0){
                    continue;
                }


                String rowDay = row.getCell(1).getStringCellValue();
                String dayString;

                //stop reading data
                if (rowDay.isEmpty()){
                    break;
                } else {
                    dayString = rowDay;
                }

                String depoT = row.getCell(2).getStringCellValue();
                String numberOfTram = row.getCell(3).getStringCellValue();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime date = LocalDateTime.parse(dayString, formatter);

                String travelCard = row.getCell(4).getStringCellValue();

                String price = row.getCell(5).getStringCellValue().strip();

                Ticket ticket = new Ticket();
                ticket.setDay(date.toLocalDate());
                ticket.setTime(date.toLocalTime());
                ticket.setTram(tramService.getByDepoAndNumberOfTram(depoT, numberOfTram));
                ticket.setPrice(Integer.valueOf(price.substring(0, price.length()-3)));
                ticket.setTravelCard(travelCard);

                //save a data in db
                ticketService.add(ticket);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
