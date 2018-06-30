/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.nanois.patentdataorganizer.utilities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.filechooser.FileFilter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Nano
 */
public class Tools {

    public class Constants {

        public static final String ANSI_RESET = "\u001B[0m";
        public static final String ANSI_BLACK = "\u001B[30m";
        public static final String ANSI_RED = "\u001B[31m";
        public static final String ANSI_GREEN = "\u001B[32m";
        public static final String ANSI_YELLOW = "\u001B[33m";
        public static final String ANSI_BLUE = "\u001B[34m";
        public static final String ANSI_PURPLE = "\u001B[35m";
        public static final String ANSI_CYAN = "\u001B[36m";
        public static final String ANSI_WHITE = "\u001B[37m";

        public static final String XML = "xml";
    }

    public static void testAccess() {
        System.out.println("Access to " + Tools.class + " is working.");
    }

    /**
     * Altered Oracle tutorial code.
     *
     * @see
     * https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html
     * @param s
     * @return
     */
    public static String getExtension(final String s) {
        String ext = null;
        int i = s.lastIndexOf('.');

        if (i > 0 && i < s.length() - 1) {
            ext = s.substring(i + 1).toLowerCase();
        }
        return ext;
    }

    /**
     * Altered Oracle tutorial code.
     *
     * @see
     * https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html
     * @param f
     * @return
     */
    public static String getExtension(File f) {
        return getExtension(f.getName());
    }

    /**
     * This code is derrived from some code I found online, I lost track of the
     * link because windoze 10 decided to go rogue and update. If and when I
     * find the site again I will credit them.
     *
     * @param records
     * @param keys
     * @param columns
     * @param filePath xlsx File path
     *
     */
    public static void writeExcelFile(HashMap records, ArrayList<String> keys, ArrayList<String> columns, String filePath) {
        CellStyle headerCellStyle;
        CellStyle dateCellStyle;
        Sheet sheet;
        FileOutputStream fileOut;
        Row headerRow;
        Workbook workbook;
        CreationHelper createHelper;
        Font headerFont;

        workbook = new XSSFWorkbook();
        createHelper = workbook.getCreationHelper();
        sheet = workbook.createSheet("Patent Data");
        headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.RED.getIndex());

        headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        headerRow = sheet.createRow(0);

        for (int i = 0; i < columns.size(); i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns.get(i));
            cell.setCellStyle(headerCellStyle);
        }

        dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));

        int rowNum = 1;
        for (String key : keys) {
            Row row = sheet.createRow(rowNum++);
            int i = 0;
            for(String c : columns){
                Record r = ((Record)records.get(key));
                RecordDataPoints dp = r.getDataPoints();
                String s = dp.getDataPoint(c);
                row.createCell(i).setCellValue(s);
                i++;
            }
        }

        for (int i = 0; i < columns.size(); i++) {
            //sheet.autoSizeColumn(i);
        }

        try {
            fileOut = new FileOutputStream(filePath);
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();
        } catch (IOException ex) {
            System.out.println("Error: " + ex);
        }
        System.out.println("Finished Exporting");
    }

    public class ExcelFileFilter extends FileFilter {

        @Override
        public boolean accept(File f) {
            if (f.isFile()) {
                String ext = f.getName();
                if (ext.equals("xlsx") || ext.equals("xls")) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public String getDescription() {
            return "Excel Files";
        }

    }
}
