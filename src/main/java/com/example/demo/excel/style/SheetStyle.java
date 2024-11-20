package com.example.demo.excel.style;

import com.example.demo.excel.constance.StyleConstance;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class SheetStyle {

    public CellStyle setStyle(Workbook workbook, int size, BorderStyle borderStyle, boolean isBold){
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setBorderBottom(borderStyle);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);

        XSSFFont font = ((XSSFWorkbook) workbook).createFont();
        font.setFontName(StyleConstance.FONT_NAME);
        font.setFontHeightInPoints((short) size);
        font.setBold(isBold);
        headerStyle.setFont(font);

        return headerStyle;
    }
}
