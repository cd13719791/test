package com.moyou.moyouRms.util.excel;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.moyou.moyouRms.util.data.DataframeInDC;

/**
 * 读取excel 升级版
 * 
 * @title ExcelRead.java
 * @Description TODO
 * @author liuxinyi
 * @date 2016年11月29日 下午1:39:08
 * @version 1.0.0
 */
public class ExcelReadX {
	public DataframeInDC converter(String xslsFilePath, String sheetName)
			throws Exception {
		Workbook wb = null;
		FileInputStream fIns = null;
		try {

			fIns = new FileInputStream(new File(xslsFilePath));
			wb = WorkbookFactory.create(fIns);
			Sheet sheet = wb.getSheet(sheetName);
			// wb = new XSSFWorkbook(fIns);
			// sheetName=基因分析
			// XSSFSheet sheet = wb.getSheet(sheetName);
			if (sheet == null) {
				throw new Exception("Excel缺少sheet页[" + sheetName + "]");
			}

//			int nFirstRowNum = sheet.getFirstRowNum();
			int nLastRowNum = sheet.getLastRowNum();
			Row row1 = sheet.getRow(0);
			int nCellNum1 = row1.getFirstCellNum();
			int nCellNum2 = row1.getLastCellNum();
			DataframeInDC dataframe = new DataframeInDC();

			// 根据Excel的前1行，构造表头
			Map<Integer, String> hmIndexColNames = new HashMap<Integer, String>();
			List<String> colNames = new ArrayList<String>();
			for (int colIndex = nCellNum1; colIndex <= nCellNum2; colIndex++) {
				Cell cell1 = row1.getCell(colIndex);
				// XSSFCell cell2 = row2.getCell(colIndex);
				String cell1Value = ExcelUtils.getExcelCellValue(cell1);
				if (cell1Value.equals(""))
					cell1Value = "Column" + colIndex;
				// String cell2Value = ExcelUtils.getExcelCellValue(cell2);
				String colName = cell1Value;
				/*
				 * if(!cell2Value.equals("")){ colName =
				 * cell1Value+"__"+cell2Value; }
				 */colNames.add(colName);
				hmIndexColNames.put(colIndex, colName);
			}
			dataframe.setColNames(colNames);

			// 根据Excel的后面行，构造内容
			for (int colIndex = nCellNum1; colIndex <= nCellNum2; colIndex++) {
				ArrayList<Object> o = new ArrayList<Object>();
				String colName = hmIndexColNames.get(colIndex);
				for (int rowIndex = 1; rowIndex <= nLastRowNum; rowIndex++) {
					Row row = sheet.getRow(rowIndex);
					Cell cell = row.getCell(colIndex);
					String cellValue = ExcelUtils.getExcelCellValue(cell);
					o.add(cellValue);
				}
				dataframe.updateColValue(colName, o);
			}
			return dataframe;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (fIns != null) {
				try {
					fIns.close();
					fIns = null;
				} catch (Exception ex) {
				}
			}
		}
	}
//	public static void main(String[] args) {
//		try {
//			ExcelReadX er = new ExcelReadX();
//			DataframeInDC dc = er.converter("C:\\Users\\Administrator\\Desktop\\说说评论.xlsx","Sheet1");
//			Map<String, ArrayList<Object>> dataMap = dc.getColValues();
//			List nameList = dataMap.get("正文");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}
