package com.example.demo.excel.style;

import com.example.demo.excel.constance.Style;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class SheetStyle implements ISheetStyle{

    //set style for a sheet
    @Override
    public CellStyle setStyle(Workbook workbook, int size, BorderStyle borderStyle, boolean isBold){
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setBorderBottom(borderStyle);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);

        XSSFFont font = ((XSSFWorkbook) workbook).createFont();
        font.setFontName(Style.FONT_NAME.getFullName());
        font.setFontHeightInPoints((short) size);
        font.setBold(isBold);
        headerStyle.setFont(font);

        return headerStyle;
    }
}
