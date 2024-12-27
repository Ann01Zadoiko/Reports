package com.example.demo.excel.sheet;

import com.example.demo.excel.constance.*;
import com.example.demo.excel.table.TableHeader;
import com.example.demo.excel.table.bottom.TableDepoTicketMonthBottom;
import com.example.demo.excel.table.main.TableDepoTicketMonthMain;

import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SheetTicketsDepoMonth {

    private final TableDepoTicketMonthMain main;
    private final TableDepoTicketMonthBottom bottom;

    //create a sheet for travel card by depo
    public void createTable(Workbook workbook, String depo, String month, String year){

        ArrayList<String> strings = null;

        if (depo.equals(Depo.TRAM_1.getFullName())){
            strings = new ArrayList<>(Arrays.stream(TrackDepo1.values()).map(TrackDepo1::getTrack).toList());
        }

        if (depo.equals(Depo.TRAM_2.getFullName())){
            strings = new ArrayList<>(Arrays.stream(TrackDepo2.values()).map(TrackDepo2::getTrack).toList());
        }

        if (depo.equals(Depo.TROLL.getFullName())){
            strings = new ArrayList<>(Arrays.stream(TrackDepoTroll.values()).map(TrackDepoTroll::getTrack).toList());
        }
        
        //date, tracks, amount
        List<String> list = new ArrayList<>();
        list.add(Field.DATE.getFullName());
        list.addAll(strings);
        list.add(Field.AMOUNT.getFullName());


        //name of the sheet
        Sheet sheet = workbook.createSheet("За " + month + "." + year + " одноразові квитки");
        //  sheet.setColumnWidth(0, 10000);
        //  sheet.setColumnWidth(1, 6000);

        //add the header of the sheet
        new TableHeader().createHeaderTickets(workbook, sheet, list);

        int indexRow = 1;

        //add the main part of the sheet
        int rowMain = main.createMain(indexRow, workbook, sheet, depo, month, year);

        //amount for month
        bottom.createBottom(rowMain, workbook, sheet, depo, month, year);


    }

}
