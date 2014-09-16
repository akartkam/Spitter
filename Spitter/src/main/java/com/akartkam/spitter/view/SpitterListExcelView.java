package com.akartkam.spitter.view;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.akartkam.spitter.domain.Spitter;

public class SpitterListExcelView extends AbstractExcelView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<Spitter> listSpitters = (List<Spitter>) model.get("listSpitters");
        
        // create a new Excel sheet
        HSSFSheet sheet = workbook.createSheet("Current Spitters");
        sheet.setDefaultColumnWidth(30);
         
        // create style for header cells
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.BLUE.index);
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);
         
        // create header row
        HSSFRow header = sheet.createRow(0);
         
        header.createCell(0).setCellValue("Имя пользователя");
        header.getCell(0).setCellStyle(style);
         
        header.createCell(1).setCellValue("Ф.И.О.");
        header.getCell(1).setCellStyle(style);
         
        header.createCell(2).setCellValue("Email");
        header.getCell(2).setCellStyle(style);
         
        header.createCell(3).setCellValue("Пароль");
        header.getCell(3).setCellStyle(style);
         
        header.createCell(4).setCellValue("Обновлен через почту");
        header.getCell(4).setCellStyle(style);
         
        // create data rows
        int rowCount = 1;
         
        for (Spitter aSpitter : listSpitters) {
            HSSFRow aRow = sheet.createRow(rowCount++);
            aRow.createCell(0).setCellValue(aSpitter.getUsername());
            aRow.createCell(1).setCellValue(aSpitter.getFullName());
            aRow.createCell(2).setCellValue(aSpitter.getEmail());
            aRow.createCell(3).setCellValue(aSpitter.getPassword());
            aRow.createCell(4).setCellValue(aSpitter.isUpdateByEmail());
        }
    }

}


