package helperObjects;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import enummerables.Enums;
import enummerables.Enums.BrowserTypes;
import enummerables.Enums.LoggingType;
import readInputData.ExcelWorkbook;
import utilities.AppConfig;
import utilities.SeleniumDriver;

public class TestBase
{
	
    public static String testResultLocation;
    public static String environment;
    public static String release;
    
	protected static SeleniumDriver seleniumDriver;
	protected static AppConfig  appConfig = new AppConfig();;
	protected Properties configFile;
	protected static FileInputStream inputStream;
	protected static ExcelWorkbook excelWorkbook = new ExcelWorkbook();;

	
	
    protected static TestData testData;
	protected static List<TestData> testDataList = new ArrayList<TestData>();
	protected static TestResult testResult;
	protected static Logging  logger = new Logging();
	protected static String workingDir = System.getProperty("user.dir");
	
	
	public TestBase ()
	{	
		
	}
	
	public TestBase (SeleniumDriver seleniumDriver)
	{
		TestBase.seleniumDriver = seleniumDriver;
	}

	public static void prepareSuite(String inputFileName, BrowserTypes browserType) throws Exception
	{
		try
		{
		    
			loadTestDataList(workingDir + appConfig.getUsersInputFile());
			
        	switch(browserType)
        	{
        		case Chrome:
        			seleniumDriver = new SeleniumDriver("dependencies\\chromedriver.exe", browserType);
        			seleniumDriver.startWebDriver(browserType);
        			break;
        		case Edge:
        			seleniumDriver = new SeleniumDriver("dependencies\\MicrosoftWebDriver.exe", browserType);
        			seleniumDriver.startWebDriver(browserType);

        			break;
        		default:
        	}
 
		}
		catch(Exception e)
		{
			logger.printLog(LoggingType.ERROR, "prepareSuite() " + e.getMessage());
			throw(e);
		}
	}
	
	public static void setupTest(String testName)
	{
		try
		{
			  	//Start new test
			    testData = new TestData();
			    testData = getTestData(testName);
			    
		        testResult = new TestResult();
				testResult.startTime();
				testResult.SetTestData(TestBase.testData);

      	}
		catch(Exception e)
		{
			logger.printLog(LoggingType.ERROR, "setupTest() " + e.getMessage());
			throw(e);
		}
	}
	
    public static boolean loadTestDataList(String inputFile)
    {
        try
        {
            if(testDataList.isEmpty())
            {
                testDataList = excelWorkbook.ReadExcelWorkbook(inputFile);
                return true;
            }
            else 
            	return true;
        
        }
        catch(Exception e)
        {
        	logger.printLog(Enums.LoggingType.ERROR, "ReadExcelWorkbook() " + e.getMessage());
            return false;
        }
    }
    
	public static void closeBrowser()
	{
        SeleniumDriver.closeBrowser();
	}
	
    public static TestData getTestData(String testName)
    {
    	try
    	{
			for(TestData tData: testDataList)
			{ 
				if(tData.GetTestDataName().trim().equals(testName))	
				{
					testData = tData;
				}
			}
			
			return testData;
    	}
    	catch(Exception e)
    	{
    		logger.printLog(LoggingType.ERROR, "Unable to complete getTestData() method");
    		return testData;
    	}
    }

    public static void endTest()
    {
		testResult.endTime();
		
		if(testResult.GetStatus())
		{
			testResult.setMessage("Completed succesfully");
		}
		else
		{
			testResult.setMessage("Completed unsuccessfully");
		}

    }

}

