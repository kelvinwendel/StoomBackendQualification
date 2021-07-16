package com.stoom.application.util;

import lombok.SneakyThrows;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.core.io.InputStreamResource;

import java.beans.PropertyDescriptor;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Utility class to help with exportation of files.
 */
public class ExportUtilities {

    /**
     * Generate a '.XLSX' from collection and parameters informed.
     *
     * @param name       Name of sheet to be created.
     * @param columns    Columns names of a sheet.
     * @param fields     Fields of collection object to be filled.
     * @param collection Source of data.
     * @return A '.XLSX' file.
     */
    @SneakyThrows
    public static InputStreamResource generateXLSX(String name, String[] columns, String[] fields, List<?> collection) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet(name);
        Row firstRow = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);

        for (int i = 0; i < columns.length; i++) {
            firstRow.createCell(i).setCellValue(columns[i]);
            firstRow.getCell(i).setCellStyle(style);
        }

        for (int i = 0; i < collection.size(); i++) {
            Object record = collection.get(i);
            Row actualRow = sheet.createRow(i + 1);

            for (int j = 0; j < fields.length; j++) {
                PropertyDescriptor property = BeanUtils.getPropertyDescriptor(record.getClass(), fields[j]);
                Method getter = property.getReadMethod();
                Object value = getter.invoke(record);
                Cell cell = actualRow.createCell(j);

                if (value instanceof String) {
                    cell.setCellValue((String) value);
                } else if (value instanceof Double) {
                    cell.setCellValue((Double) value);
                } else if (value instanceof Integer) {
                    cell.setCellValue((Integer) value);
                } else if (value instanceof Boolean) {
                    cell.setCellValue((Boolean) value);
                }

                sheet.autoSizeColumn(j);
            }
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        return new InputStreamResource(new ByteArrayInputStream(outputStream.toByteArray()));
    }
}