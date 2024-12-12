package com.example.demo.importData;

import com.example.demo.track.Track;
import com.example.demo.track.TrackService;
import com.example.demo.tram.Tram;
import com.example.demo.tram.TramService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImportTracks implements Import{

    private final TramService tramService;
    private final TrackService trackService;


    //import data from a file into tracks (table) by depo and day
    @Override
    public void importExcelToData(MultipartFile file, String depo, LocalDate day) throws RuntimeException{

        //processing an incoming file
        try (InputStream inputStream = file.getInputStream()){

            Sheet sheet;

            //check format of the file
            if (Objects.requireNonNull(file.getOriginalFilename()).endsWith(".xls") && !(file.isEmpty())){
                HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
                sheet = workbook.getSheetAt(0);
            } else {
                XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
                sheet = workbook.getSheetAt(0);
            }

            //read data and selection data from cell
            for (Row row: sheet){

                //skip 0-row
                if (row.getRowNum() == 0){
                    continue;
                }

                String number = row.getCell(0).getStringCellValue();

                //stop reading data
                if (number.isEmpty()){
                    break;
                }

                if (number.length() < 4 && (depo.equals("Депо №1 трамвай") || depo.equals("Депо №2 трамвай"))){

                }

                Tram tram = tramService.getByDepoAndNumberOfTram(depo, number);

                String firstPart = row.getCell(1).getStringCellValue();
                String secondPart;
                LocalTime localTime;

                if (row.getCell(2) == null ||
                        row.getCell(2).getStringCellValue() == null ||
                        row.getCell(2).getStringCellValue().isEmpty() ||
                        row.getCell(2).getStringCellValue().equals("00:00:00")){

                    Track track = new Track();
                    track.setTram(tram);
                    track.setFirstPart(firstPart);
                    track.setDay(day);

                    trackService.add(track);
                    continue;
                } else {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                    localTime = LocalTime.parse(row.getCell(2).getStringCellValue(), formatter);

                    secondPart = row.getCell(3).getStringCellValue();
                }

                Track track = new Track();
                track.setTram(tram);
                track.setFirstPart(firstPart);
                track.setSecondPart(secondPart);
                track.setTime(localTime);
                track.setDay(day);

                //save a data in db
                trackService.add(track);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
