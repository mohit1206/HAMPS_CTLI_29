package com.ctli.it.lib;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFHyperlink;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataTable {
	public static  String path;
	public  FileInputStream fis = null;
	public  FileOutputStream fileOut =null;
	private static XSSFWorkbook workbook = null;
	private XSSFSheet sheet = null;
	private XSSFRow row   =null;
	private XSSFCell cell = null;
	static DataTable datatable;
	

	public DataTable(String path) {
		
		this.path=path;
		try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheetAt(0);
			fis.close();
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
		
	}
	
	//return all sheet count
	public int getAllSheetCount(){
		int sheetCount = workbook.getActiveSheetIndex();
		return sheetCount+1;
		
	}
	
	//return all sheet count
		public static int getAllSheetCount(String filePath){
			DataTable datatable = new DataTable(filePath);
			int sheetCount = workbook.getActiveSheetIndex();
			return sheetCount+1;
			
		}
	//return all sheets
	public String[] getAllSheets(){
	
		
		//int allSheet = workbook.getActiveSheetIndex();
		int allSheet = workbook.getNumberOfSheets();
		String[] allSheets =new String[allSheet];
		//System.out.println(allSheet);
		for(int i=0;i<=allSheet-1;i++){
			//System.out.println(workbook.getSheetName(i));
			allSheets[i] = workbook.getSheetName(i);
		}
		
		return allSheets;
	}
	
public static String[] getAllSheets(String filePath){
	
	DataTable datatable = new DataTable(filePath);
		int allSheet = workbook.getActiveSheetIndex();
		String[] allSheets =new String[allSheet+1];
		System.out.println(allSheet);
		for(int i=0;i<=allSheet;i++){
			System.out.println(workbook.getSheetName(i));
			allSheets[i] = workbook.getSheetName(i);
		}
		
		return allSheets;
	}
	// returns the row count in a sheet
	public int getRowCount(String sheetName){
		int index = workbook.getSheetIndex(sheetName);
		if(index==-1)
			return 0;
		else{
		sheet = workbook.getSheetAt(index);
		int number=sheet.getLastRowNum()+1;
		return number;
		}
		
	}
	
	// returns the data from a cell
	public String getCellData(String sheetName,String colName,int rowNum){
		try{
			if(rowNum <=0)
				return "";
		
		int index = workbook.getSheetIndex(sheetName);
		int col_Num=-1;
		if(index==-1)
			return "";
		
		sheet = workbook.getSheetAt(index);
		row=sheet.getRow(0);
		for(int i=0;i<row.getLastCellNum();i++){
			
			if(row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
				col_Num=i;
		}
		if(col_Num==-1)
			return "";
		
		sheet = workbook.getSheetAt(index);
		row = sheet.getRow(rowNum-1);
		if(row==null)
			return "";
		cell = row.getCell(col_Num);
		
		if(cell==null)
			return "";
	
		if(cell.getCellType()==Cell.CELL_TYPE_STRING ){
			  return cell.getStringCellValue();
		}
		else if(cell.getCellType()==3){
		//	return cell.getRawValue();
			return cell.getStringCellValue();
			//return cell.toString().trim();
			//return cell.getCellStyle().getDataFormatString();
	//	return cell.getRichStringCellValue().toString();
		}
		else if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC || cell.getCellType()==Cell.CELL_TYPE_FORMULA ){
			  
			  String cellText  = String.valueOf(cell.getNumericCellValue());
			  if (HSSFDateUtil.isCellDateFormatted(cell)) {
		      
				  double d = cell.getNumericCellValue();

				  Calendar cal =Calendar.getInstance();
				  cal.setTime(HSSFDateUtil.getJavaDate(d));
		            cellText =
		             (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
		           cellText = cal.get(Calendar.DAY_OF_MONTH) + "/" +
		                      cal.get(Calendar.MONTH)+1 + "/" + 
		                      cellText;
		           
		           //System.out.println(cellText);

		         }

			  
			  
			  return cellText;
		  }else if(cell.getCellType()==Cell.CELL_TYPE_BLANK)
		      return ""; 
		
		  else 
			  return String.valueOf(cell.getBooleanCellValue());
		
		}
		catch(Exception e){
			
			e.printStackTrace();
			return "row "+rowNum+" or column "+colName +" does not exist in xls";
		}
	}
	
	// returns the data from a cell
	public String getCellData(String sheetName,int colNum,int rowNum){
		try{
			
			if(rowNum <=0)
				return "";
		
		int index = workbook.getSheetIndex(sheetName);

		if(index==-1)
			return "";
		
	     
		sheet = workbook.getSheetAt(index);
		row = sheet.getRow(rowNum-1);
		if(row==null)
			return "";
		cell = row.getCell(colNum);
		if(cell==null)
			return "";
		
	  if(cell.getCellType()==Cell.CELL_TYPE_STRING)
		  return cell.getStringCellValue();
	  else if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC || cell.getCellType()==Cell.CELL_TYPE_FORMULA ){
		  
		  String cellText  = String.valueOf(cell.getNumericCellValue());
		  if (HSSFDateUtil.isCellDateFormatted(cell)) {
	           // format in form of M/D/YY
			  double d = cell.getNumericCellValue();

			  Calendar cal =Calendar.getInstance();
			  cal.setTime(HSSFDateUtil.getJavaDate(d));
	            cellText =
	             (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
	           cellText = cal.get(Calendar.MONTH)+1 + "/" +
	                      cal.get(Calendar.DAY_OF_MONTH) + "/" +
	                      cellText;
	           
	 

	         }

		  
		  
		  return cellText;
	  }else if(cell.getCellType()==Cell.CELL_TYPE_BLANK)
	      return "";
	  else 
		  return String.valueOf(cell.getBooleanCellValue());
		}
		catch(Exception e){
			
			e.printStackTrace();
			return "row "+rowNum+" or column "+colNum +" does not exist  in xls";
		}
	}
	
	// returns true if data is set successfully else false
	public boolean setCellColor(String sheetName,String colName,int rowNum, String color){
		try{
			fis = new FileInputStream(path); 
			workbook = new XSSFWorkbook(fis);
			XSSFCellStyle style = workbook.createCellStyle();
			if(color.trim().equalsIgnoreCase("RED")){
			style.setFillForegroundColor(IndexedColors.RED.getIndex());
			 style.setFillPattern(CellStyle.SOLID_FOREGROUND);
			}
			if(color.trim().equalsIgnoreCase("GREEN")){
				style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
				 style.setFillPattern(CellStyle.SOLID_FOREGROUND);
			}
			if(color.trim().equalsIgnoreCase("YELLOW")){
				style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
				 style.setFillPattern(CellStyle.SOLID_FOREGROUND);
			}
			//style.setFillBackgroundColor(new XSSFColor(new java.awt.Color(128, 0, 128)));

			if(rowNum<=0)
				return false;
			
			int index = workbook.getSheetIndex(sheetName);
			int colNum=-1;
			if(index==-1)
				return false;
			
			
			sheet = workbook.getSheetAt(index);
			

			row=sheet.getRow(0);
			for(int i=0;i<row.getLastCellNum();i++){
				
				if(row.getCell(i).getStringCellValue().trim().equals(colName))
					colNum=i;
			}
			if(colNum==-1)
				return false;

			sheet.autoSizeColumn(colNum); 
			row = sheet.getRow(rowNum-1);
			if (row == null)
				row = sheet.createRow(rowNum-1);
			
			cell = row.getCell(colNum);	
			if (cell == null)
		        cell = row.createCell(colNum);

		cell.setCellStyle(style);
		   // cell.setCellValue(data);

		    fileOut = new FileOutputStream(path);

			workbook.write(fileOut);

		    fileOut.close();	

			}
			catch(Exception e){
				e.printStackTrace();
				return false;
			}
			return true;
		
	}
	
	// set cell data for particular sheet
	public boolean setCellData(String sheetName,String colName,int rowNum, String data){
		try{
		fis = new FileInputStream(path); 
		workbook = new XSSFWorkbook(fis);

		if(rowNum<=0)
			return false;
		
		int index = workbook.getSheetIndex(sheetName);
		int colNum=-1;
		if(index==-1)
			return false;
		
		
		sheet = workbook.getSheetAt(index);
		

		row=sheet.getRow(0);
		for(int i=0;i<row.getLastCellNum();i++){
			
			if(row.getCell(i).getStringCellValue().trim().equals(colName))
				colNum=i;
		}
		if(colNum==-1)
			return false;

		sheet.autoSizeColumn(colNum); 
		row = sheet.getRow(rowNum-1);
		if (row == null)
			row = sheet.createRow(rowNum-1);
		
		cell = row.getCell(colNum);	
		if (cell == null)
	        cell = row.createCell(colNum);

	
	    cell.setCellValue(data);

	    fileOut = new FileOutputStream(path);

		workbook.write(fileOut);

	    fileOut.close();	

		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	
	public boolean setCellData(String sheetName,String colName,int rowNum, String data,String url){
		
		try{
		fis = new FileInputStream(path); 
		workbook = new XSSFWorkbook(fis);

		if(rowNum<=0)
			return false;
		
		int index = workbook.getSheetIndex(sheetName);
		int colNum=-1;
		if(index==-1)
			return false;
		
		
		sheet = workbook.getSheetAt(index);
	
		row=sheet.getRow(0);
		for(int i=0;i<row.getLastCellNum();i++){
			
			if(row.getCell(i).getStringCellValue().trim().equalsIgnoreCase(colName))
				colNum=i;
		}
		
		if(colNum==-1)
			return false;
		sheet.autoSizeColumn(colNum); 
		row = sheet.getRow(rowNum-1);
		if (row == null)
			row = sheet.createRow(rowNum-1);
		
		cell = row.getCell(colNum);	
		if (cell == null)
	        cell = row.createCell(colNum);
			
	    cell.setCellValue(data);
	    XSSFCreationHelper createHelper = workbook.getCreationHelper();

	    CellStyle hlink_style = workbook.createCellStyle();
	    XSSFFont hlink_font = workbook.createFont();
	    hlink_font.setUnderline(XSSFFont.U_SINGLE);
	    hlink_font.setColor(IndexedColors.BLUE.getIndex());
	    hlink_style.setFont(hlink_font);
	

	    XSSFHyperlink link = createHelper.createHyperlink(XSSFHyperlink.LINK_FILE);
	    link.setAddress(url);
	    cell.setHyperlink(link);
	    cell.setCellStyle(hlink_style);
	      
	    fileOut = new FileOutputStream(path);
		workbook.write(fileOut);

	    fileOut.close();	

		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
public boolean setCellDataByColNum(String sheetName,int colNum,int rowNum, String data){
	

	try{
	fis = new FileInputStream(path); 
	workbook = new XSSFWorkbook(fis);

	if(rowNum<=0)
		return false;
	
	int index = workbook.getSheetIndex(sheetName);
	
	if(index==-1)
		return false;
	
	
	sheet = workbook.getSheetAt(index);
	

	row=sheet.getRow(0);
	

	sheet.autoSizeColumn(colNum); 
	row = sheet.getRow(rowNum-1);
	if (row == null)
		row = sheet.createRow(rowNum-1);
	
	cell = row.getCell(colNum);	
	if (cell == null)
        cell = row.createCell(colNum);


    cell.setCellValue(data);

    fileOut = new FileOutputStream(path);

	workbook.write(fileOut);

    fileOut.close();	

	}
	catch(Exception e){
		e.printStackTrace();
		return false;
	}
	return true;

	
}
	
	
	
	public boolean addSheet(String  sheetname){		
		
		FileOutputStream fileOut;
		try {
			 workbook.createSheet(sheetname);	
			 fileOut = new FileOutputStream(path);
			 workbook.write(fileOut);
		     fileOut.close();		    
		} catch (Exception e) {			
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	// returns true if sheet is removed successfully else false if sheet does not exist
	public static boolean deleteSheet(String sheetName){		
		int index = workbook.getSheetIndex(sheetName);
		if(index==-1)
			return false;
		
		FileOutputStream fileOut;
		try {
			workbook.removeSheetAt(index);
			fileOut = new FileOutputStream(path);
			workbook.write(fileOut);
		    fileOut.close();		    
		} catch (Exception e) {			
			e.printStackTrace();
			return false;
		}
		return true;
	}
	// returns true if column is created successfully
	public boolean addColumn(String sheetName,String colName){
		
		
		try{				
			fis = new FileInputStream(path); 
			workbook = new XSSFWorkbook(fis);
			int index = workbook.getSheetIndex(sheetName);
			if(index==-1)
				return false;
			
		XSSFCellStyle style = workbook.createCellStyle();
		style.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		
		sheet=workbook.getSheetAt(index);
		
		row = sheet.getRow(0);
		if (row == null)
			row = sheet.createRow(0);
	
		if(row.getLastCellNum() == -1)
			cell = row.createCell(0);
		else
			cell = row.createCell(row.getLastCellNum());
	        
	        cell.setCellValue(colName);
	        cell.setCellStyle(style);
	        
	        fileOut = new FileOutputStream(path);
			workbook.write(fileOut);
		    fileOut.close();		    

		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
		return true;
		
		
	}
	// removes a column and all the contents
	public boolean deleteColumn(String sheetName, int colNum) {
		try{
		if(!isSheetExist(sheetName))
			return false;
		fis = new FileInputStream(path); 
		workbook = new XSSFWorkbook(fis);
		sheet=workbook.getSheet(sheetName);
		XSSFCellStyle style = workbook.createCellStyle();
		style.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
		XSSFCreationHelper createHelper = workbook.getCreationHelper();
		style.setFillPattern(HSSFCellStyle.NO_FILL);
		
	    
	
		for(int i =0;i<getRowCount(sheetName);i++){
			row=sheet.getRow(i);	
			if(row!=null){
				cell=row.getCell(colNum);
				if(cell!=null){
					cell.setCellStyle(style);
					row.removeCell(cell);
				}
			}
		}
		fileOut = new FileOutputStream(path);
		workbook.write(fileOut);
	    fileOut.close();
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
		
	}
  // find whether sheets exists	
	public boolean isSheetExist(String sheetName){
		int index = workbook.getSheetIndex(sheetName);
		if(index==-1){
			index=workbook.getSheetIndex(sheetName.toUpperCase());
				if(index==-1)
					return false;
				else
					return true;
		}
		else
			return true;
	}
	
	// returns number of columns in a sheet	
	public int getColumnCount(String sheetName){
		
		if(!isSheetExist(sheetName))
		 return -1;
		
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(0);
		
		if(row==null)
			return -1;
		
		return row.getLastCellNum();
		
		
		
	}

	public int getCellRowNum(String sheetName,String colName,String cellValue){
		
		for(int i=2;i<=getRowCount(sheetName);i++){
	    	if(getCellData(sheetName,colName , i).equalsIgnoreCase(cellValue)){
	    		return i;
	    	}
	    }
		return -1;
		
	}
	public static boolean copyFile(String fromFile, String toFile){
		try {
			FileUtils.copyFile(new File(fromFile), new File(toFile));
		} catch (IOException e) {
					e.printStackTrace();
		}
		return true;
		
	}
	
	
	
	public static boolean copySheet(String sheetName, String fromFile, String toFile){		
		
		String tempFile = System.getProperty("user.dir") + "//TEMP" +"//temp.xlsx";
		
		
		
		FileOutputStream fileOut;
		try {
			FileUtils.copyFile(new File(fromFile), new File(tempFile));
			DataTable datatable = null;
			 datatable = new DataTable(tempFile);
			 
			 int index = workbook.getSheetIndex(sheetName);
			 
				if(index==-1)
					return false;
				datatable.workbook.cloneSheet(index);
			 int getSheetCnt = datatable.getAllSheetCount();
			 String[] allSheet = datatable.getAllSheets();
			 for(int i=0;i<getSheetCnt;i++){
				 
				 if(!allSheet[i].trim().equalsIgnoreCase(sheetName)){
						datatable.deleteSheet(allSheet[i]);
					}
				 
			 }
			
			FileInputStream fs = new FileInputStream(toFile);
			fileOut = new FileOutputStream(toFile);
		
			workbook.write(fileOut);
		    fileOut.close();
		    
			
		} catch (Exception e) {			
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public static boolean isfileExists(String filePath){
		try{
			
			FileInputStream toFileWB = new FileInputStream(filePath);
			return true;
			
	}catch(Exception e){
		return false;
	}
	}
	public static boolean cpSheet(String sheetName, String fromFile, String toFile){
		try{
			
		
		String tempFile = System.getProperty("user.dir") + "//TEMP" +"//temp.xlsx";
		FileOutputStream fileOut;
		FileUtils.copyFile(new File(fromFile), new File(tempFile));
		DataTable datatable = null;
		datatable = new DataTable(tempFile);
		XSSFWorkbook toWorkBook;
	
		
        FileInputStream tmp = new FileInputStream(tempFile);
        XSSFWorkbook workbook = new XSSFWorkbook(tmp);
        if(isfileExists(toFile)){
        	FileInputStream toFileWB = new FileInputStream(toFile);
              toWorkBook = new XSSFWorkbook(toFileWB);
        }else{
        	 toWorkBook = new XSSFWorkbook();
        }
        
        XSSFSheet sheet = null;
        XSSFRow row = null;
        XSSFCell cell = null;
        XSSFCellStyle myCellStyle = null;
        XSSFCellStyle newStyle  = null;
        XSSFSheet mySheet = null;
        XSSFRow myRow = null;
        XSSFCell myCell = null;
        int sheets = workbook.getNumberOfSheets();
        int fCell = 0;
        int lCell = 0;
        int fRow = 0;
        int lRow = 0;
    	int index = workbook.getSheetIndex(sheetName);
		if(index==-1)
					return false;
        
            sheet = workbook.getSheetAt(index);
            if (sheet != null) {
                mySheet = toWorkBook.createSheet(sheetName);
                newStyle = toWorkBook.createCellStyle();
                fRow = sheet.getFirstRowNum();
                lRow = sheet.getLastRowNum();
                for (int iRow = fRow; iRow <= lRow; iRow++) {
                    row = sheet.getRow(iRow);
                  //  myCellStyle = row.getRowStyle();
                    myRow = mySheet.createRow(iRow);
                 //   myRow.setRowStyle(myCellStyle);
                    if (row != null) {
                        fCell = row.getFirstCellNum();
                        lCell = row.getLastCellNum();
                        for (int iCell = fCell; iCell < lCell; iCell++) {
                        	
                            cell = row.getCell(iCell);
                            myCellStyle = cell.getCellStyle();
                            newStyle.cloneStyleFrom(myCellStyle);
                            myCell = myRow.createCell(iCell);
                           myCell.setCellStyle(newStyle);
                            if (cell != null) {
                                myCell.setCellType(cell.getCellType());
                                switch (cell.getCellType()) {
                                case HSSFCell.CELL_TYPE_BLANK:
                                	
                                    myCell.setCellValue("");
                                    break;

                                case HSSFCell.CELL_TYPE_BOOLEAN:
                                    myCell.setCellValue(cell.getBooleanCellValue());
                                    break;

                                case HSSFCell.CELL_TYPE_ERROR:
                                    myCell.setCellErrorValue(cell.getErrorCellValue());
                                    break;

                                case HSSFCell.CELL_TYPE_FORMULA:
                                    myCell.setCellFormula(cell.getCellFormula());
                                    break;

                                case HSSFCell.CELL_TYPE_NUMERIC:
                                    myCell.setCellValue(cell.getNumericCellValue());
                                    break;

                                case HSSFCell.CELL_TYPE_STRING:
                                    myCell.setCellValue(cell.getStringCellValue());
                                    break;
                                default:
                                    myCell.setCellFormula(cell.getCellFormula());
                                }
                            }
                        }
                    }
                }
            }
        
        tmp.close();
        FileInputStream fs = new FileInputStream(toFile);
        
		fileOut = new FileOutputStream(toFile);
	
		toWorkBook.write(fileOut);
	    fileOut.close();
       
    
		
		
	}catch(Exception e){
		e.printStackTrace();
		return false;
	}
		return true;
		
	

	
}

}
