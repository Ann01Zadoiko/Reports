package com.example.demo.importData;

import com.example.demo.track.Track;
import com.example.demo.track.TrackService;
import com.example.demo.tram.Tram;
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


@Service
@RequiredArgsConstructor
public class ImportTracks implements Import{

    private final TramService tramService;
    private final TrackService trackService;

    //импорт данных из файла в таблицу tracks
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

                String track = row.getCell(1).getStringCellValue();

                //добавление маршрута в бд
                Tram tram = tramService.getByDepoAndNumberOfTram(depo, number);

                Track track1 = new Track();
                track1.setTram(tram);
                track1.setTrack(track);
                track1.setDay(day);

                trackService.add(track1);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
