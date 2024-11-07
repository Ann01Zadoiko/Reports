package com.example.demo.importData;

import com.example.demo.track.TrackService;
import com.example.demo.tram.Tram;
import com.example.demo.tram.TramService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
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

    @Override
    public void importExcelToDataXls(MultipartFile file, String depo, LocalDate day) {

        try (InputStream inputStream = file.getInputStream()){
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheetAt(0);

            for (Row row: sheet){
                if (row.getRowNum() == 0){
                    continue;
                }

                String number = row.getCell(0).getStringCellValue();

                if (number.isEmpty()){
                    break;
                }

                String track = row.getCell(1).getStringCellValue();

                Tram tram = tramService.getByDepoAndNumberOfTram(depo, number);
                trackService.add(tram, track, day);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void importExcelToDataXlsx(MultipartFile file, String depo, LocalDate day) {

        try (InputStream inputStream = file.getInputStream()){
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheetAt(0);

            for (Row row: sheet){
                if (row.getRowNum() == 0){
                    continue;
                }

                String number = row.getCell(0).getStringCellValue();

                if (number.isEmpty()){
                    break;
                }

                String track = row.getCell(1).getStringCellValue();

                Tram tram = tramService.getByDepoAndNumberOfTram(depo, number);
                trackService.add(tram, track, day);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
