package com.example.demo.excel.table.buttom;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.time.LocalDate;

public interface ITableDepoButtom {

    void createMain(int indexRow, Workbook workbook, Sheet sheet, LocalDate day, String depo);
}
