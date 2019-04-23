package readInputData;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;

import enummerables.Enums.LoggingType;
import helperObjects.Logging;
import helperObjects.TestData;


public class ExcelWorkbook
{
	Logging logger;
	
    private List<TestData> testDataList;
    private TestData testData;
    private HSSFSheet sheet;
    private HSSFWorkbook workbook;
    
    public ExcelWorkbook()
    {
    	this.logger = new Logging();
        this.testDataList = new ArrayList<TestData>();
        this.testData =  new TestData();
        this.sheet = null;
    }

    public List<TestData> ReadExcelWorkbook(String path)
    {
        try
        {
            FileInputStream file = new FileInputStream(new File(path)); 

            String key = null;
            String value = null;

            Cell keyCell = null;
            Cell valueCell = null;
            
            //Get the workbook instance for XLS file
            this.workbook = new HSSFWorkbook(file);
            
            //Get first sheet from the workbook
            this.sheet = workbook.getSheetAt(0);

            //Iterate through each rows from first sheet
            Iterator<Row> rowIterator = sheet.iterator();

            while(rowIterator.hasNext())
            {
               Row keyRow = rowIterator.next();
               Row valueRow;
               
                //For each row, iterate through each columns
                Iterator<Cell> keyCellIterator = keyRow.cellIterator();
                keyCell = keyRow.getCell(0);
     
                if (keyCell != null)
                {
		                if(keyCell.getCellType() != Cell.CELL_TYPE_BLANK)
		                {
		                        while(keyCellIterator.hasNext())
		                        {
		                            keyCell = keyCellIterator.next();
		                            valueRow = this.sheet.getRow(keyRow.getRowNum() + 1);
		                            valueCell = valueRow.getCell(keyCell.getColumnIndex());
		
		                            key = CheckCellTypeAndAssignValue(keyCell);
		                            value = CheckCellTypeAndAssignValue(valueCell);
		
		                            if(!key.equals(""))
		                            {
		                                switch(keyCell.getColumnIndex())
		                                {
		                                    case 0: 
		                                        testData.SetTestDataID(key);
		                                        break;
		                                    case 1:   
		                                        testData.setTestDataName(key);
		                                        break;
		                                    default:
		                                        testData.AddToTestData(key, value);
		                                }
		                            }
		
		                        }
		                        this.testDataList.add(testData); 
		                        testData = new TestData();
		                }
		                
		                file.close();
                }

            }         
            return this.testDataList;
            
        }
        catch(FileNotFoundException fileNotfound)
        {
           logger.printLog(LoggingType.ERROR,"File not found. " + fileNotfound.getMessage());
           return testDataList; 
        }
        catch(IOException inputOutput)
        {
           logger.printLog(LoggingType.ERROR,"Input / Output. " + inputOutput.getMessage());
           return testDataList; 
        }

    }
    @SuppressWarnings("deprecation")
	private String CheckCellTypeAndAssignValue(Cell cell)
    {
        final DataFormatter df = new DataFormatter();
        String cellValue = String.valueOf("");
        
    	if(cell == null)
    	{
    		return cellValue;
    	}
    	else
    	{
	        switch(cell.getCellType())
	        {
	            case Cell.CELL_TYPE_BLANK:
	                break;
	            case Cell.CELL_TYPE_BOOLEAN:
	                cellValue = String.valueOf(cell.getBooleanCellValue());
	                break;
	            case Cell.CELL_TYPE_NUMERIC:
	                cellValue = df.formatCellValue(cell);
	                break;
	            case Cell.CELL_TYPE_STRING:
	                cellValue = cell.getStringCellValue();
	                break;
	        }
	        
	        return cellValue;
	    }
    }
    
}

