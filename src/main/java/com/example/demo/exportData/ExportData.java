package com.example.demo.exportData;


import com.example.demo.ticket.Ticket;
import com.example.demo.ticket.TicketService;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ExportData {

    private final TicketService ticketService;

    public void exportDataToExcel(String filePath, String depoOrGeneral){


    }
}
//public String exportEmployeesToExcel() throws IOException {
//        List<Tutorial> employees = employeeRepository.findAll();
//
//        // Путь к папке /src/main/resources/
//        String filePath = "./src/main/resources/employees.xlsx";
//        File file = new File(filePath);
//
//        try (Workbook workbook = new XSSFWorkbook(); FileOutputStream fileOut = new FileOutputStream(file)) {
//            Sheet sheet = workbook.createSheet("Employees");
//
//            // Заголовки
//            Row headerRow = sheet.createRow(0);
//            headerRow.createCell(0).setCellValue("ID");
//            headerRow.createCell(1).setCellValue("Title");
//            headerRow.createCell(2).setCellValue("Description");
//            headerRow.createCell(3).setCellValue("Published");
//
//            // Данные
//            int rowIdx = 1;
//            for (Tutorial employee : employees) {
//                Row row = sheet.createRow(rowIdx++);
//                row.createCell(0).setCellValue(employee.getId());
//                row.createCell(1).setCellValue(employee.getTitle());
//                row.createCell(2).setCellValue(employee.getDescription());
//                row.createCell(3).setCellValue(employee.isPublished());
//            }
//
//            // Запись в файл
//            workbook.write(fileOut);
//        }
//
//        return filePath;  // Возвращаем путь к файлу
//    }
