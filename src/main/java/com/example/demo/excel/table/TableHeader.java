package com.example.demo.excel.table;


import com.example.demo.excel.style.SheetStyle;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableHeader {

    public void createHeaderTickets(Workbook workbook, Sheet sheet, List<String> headerList){
        Row headerRow = sheet.createRow(0);

        CellStyle cellStyle = new SheetStyle().setStyle(workbook, 14, BorderStyle.MEDIUM, true);

        Cell headerCell;

        for (int i = 0; i < headerList.size(); i++){
            headerCell = headerRow.createCell(i);
            headerCell.setCellValue(headerList.get(i));
            headerCell.setCellStyle(cellStyle);
        }
    }
}
