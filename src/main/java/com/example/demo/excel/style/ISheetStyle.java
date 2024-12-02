package com.example.demo.excel.style;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;

public interface ISheetStyle {

    CellStyle setStyle(Workbook workbook, int size, BorderStyle borderStyle, boolean isBold);
}
