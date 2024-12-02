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

@Slf4j
@Service
@RequiredArgsConstructor
public class ImportTracks implements Import{

    private final TramService tramService;
    private final TrackService trackService;


    //import data from a file into tracks (table)
    @Override
    public void importExcelToData(MultipartFile file, String depo, LocalDate day) {

        //обрабока входящего файла
        try (InputStream inputStream = file.getInputStream()){

            Sheet sheet;

            //проверка формата файла
            if (file.getOriginalFilename().endsWith(".xls")){
                HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
                sheet = workbook.getSheetAt(0);
            } else {
                XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
                sheet = workbook.getSheetAt(0);
            }

            //пропуск 0-строки
            for (Row row: sheet){
                //пропуск 0-строки
                if (row.getRowNum() == 0){
                    continue;
                }

                //отбор данных из ячейки
                String number = row.getCell(0).getStringCellValue();

                if (number.isEmpty()){
                    break;
                }

                Tram tram = tramService.getByDepoAndNumberOfTram(depo, number);

                String firstPart = row.getCell(1).getStringCellValue();
                String secondPart;
                LocalTime localTime;

                if (row.getCell(2) == null || row.getCell(2).getStringCellValue() == null || row.getCell(2).getStringCellValue().isEmpty()){

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

                //добавление маршрута в бд
                Track track = new Track();
                track.setTram(tram);
                track.setFirstPart(firstPart);
                track.setSecondPart(secondPart);
                track.setTime(localTime);
                track.setDay(day);

                trackService.add(track);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
