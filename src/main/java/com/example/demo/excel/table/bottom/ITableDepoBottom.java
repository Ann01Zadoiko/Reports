package com.example.demo.excel.table.bottom;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.time.LocalDate;

public interface ITableDepoBottom {

    void createBottom(int indexRow, Workbook workbook, Sheet sheet, LocalDate day, String depo);
}