package com.aetins.web.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.aetins.web.exception.FileStorageException;

public class FileUtils {

	private static Logger logger = LoggerFactory.getLogger(FileUtils.class);

	public static File getFileFromDir(String fileName, String fileDir) {
		try {
			if (StringUtils.isEmpty(fileName) && StringUtils.isEmpty(fileDir)) {
				logger.error("File Path, File Name is not specified !");
				throw new FileStorageException("File path,name is empty !");
			} else {
				logger.info("Get File : " + fileDir);
				File file = new File(fileDir);
				File[] listFiles = file.listFiles();
				for (File files : listFiles) {
					if (files.getName().equals(fileName))
						return files;
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * To process the xls data based up on the headers
	 * 
	 * @param file
	 * @param sheetIndex
	 * @param annNo
	 * @param columnName
	 * @param rowNumberToSearch
	 * @return Map<Header,Values>
	 * @throws IOException
	 */
	public static List<String> verifyXLSFileCloumnCellAndParticularColumnCellRowValues(String file, int sheetIndex,
			String annNo, String columnName, int rowNumberToSearch) throws IOException {

		List<String> rowDataList = new ArrayList<>();
		// Workbook workbook = null;
		try {

			FileInputStream fs = new FileInputStream(new File(file));
			// workbook = WorkbookFactory.create(new File(file));
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			// will select the which sheet need to be selected
			HSSFSheet sheet = wb.getSheetAt(sheetIndex);// Integer.parseInt(sheetIndex)
			HSSFRow row;
			HSSFCell cell;
			// At which row need to search the column name
			row = sheet.getRow(rowNumberToSearch);
			int colNum = -1;

			int rowcount = sheet.getLastRowNum();
			cell = row.getCell(colNum);

			// loop search for all the columns from particular row
			for (int i = 0; i < row.getLastCellNum(); i++) {
				if (row.getCell(i).getStringCellValue().trim().equals(columnName)) {
					colNum = i;
					if (colNum > -1) {

						// loop search for complete row based on column number
						for (int j = colNum; j < rowcount; j++) {
							if ((sheet.getRow(j).getCell(0).getStringCellValue()).equals(annNo)) {
								row = sheet.getRow(j);

								// to select all the column for particular row
								for (int k = 0; k < row.getLastCellNum(); k++) {

									rowDataList.add(sheet.getRow(j).getCell(0).getStringCellValue());

								}
							}

						}
					}
				}
			}

			wb.close();
			fs.close();
		} catch (Exception ioe) {
			ioe.printStackTrace();
		}
		return rowDataList;

	}

	/**
	 * @param dirPath
	 * @return
	 */
	public static File[] getFilesFromDir(String dirPath) {
		File dir = new File(dirPath);
		File[] listFiles = dir.listFiles();
		return listFiles;
	}
	
	/**
	 * @param dirPath
	 * @return
	 */
	public static String getFileNameFromDir(String dirPath) {
		
		File[] filesFromDir = getFilesFromDir(dirPath);
		String fileName = "";
	    for(File file : filesFromDir) {
	    	fileName=  file.getName();
	    	break;
	    }
	    return fileName;
	}
}
