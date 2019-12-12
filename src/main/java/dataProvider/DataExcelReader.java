package dataProvider;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;


public class DataExcelReader {
	Logger log = Logger.getLogger(DataExcelReader.class);
	//for reading data from excel sheet
		public String ReadExcel(int getSheet, int getRowData,int getCellData){
			String cellval = null;
			try{
				FileInputStream fis = new FileInputStream("/framework/src/main/resources/inputfiles/excelinput/dataInput.xlsxs");
				HSSFWorkbook workbook = new HSSFWorkbook(fis);
				HSSFSheet sheet = workbook.getSheetAt(getSheet);
	            Row row = sheet.getRow(getRowData);
				Cell cell = row.getCell(getCellData);
				cellval= cell.getStringCellValue();
				log.info("Data is read from excel: "+ cellval);
				}
				catch(IOException e){
					log.error("not able to found excel file" + e.getMessage());
				}
			return cellval;
		}
		
		//for writing data to excel sheet
		public void WriteExcel(String setSheet,int setRowData,int setCellData, String cellValue){
			try{
				FileInputStream fis = new FileInputStream("/framework/src/main/resources/inputfiles/excelinput/dataInput.xlsx");
				HSSFWorkbook workbook = new HSSFWorkbook(fis);
				HSSFSheet sheet = workbook.getSheet(setSheet);
				Row row = sheet.createRow(setRowData);
				Cell cell = row.createCell(setCellData);
				cell.setCellType(CellType.STRING);
				cell.setCellValue("SoftwareTestingMaterial.com");
				FileOutputStream fos = new FileOutputStream(cellValue);
				workbook.write(fos);
				fos.close();
				log.info("Data is Write in excel: "+ cellValue);
				}
				catch(IOException e){
					log.error("not able to found excel file" + e.getMessage());
				}
		}
}
