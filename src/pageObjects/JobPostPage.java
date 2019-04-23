package pageObjects;
import org.openqa.selenium.ElementNotVisibleException;

import enummerables.Enums.*;
import helperObjects.TestBase;
import helperObjects.TestData;
import utilities.SeleniumDriver;

public class JobPostPage extends TestBase {
	
    public JobPostPage(SeleniumDriver seleniumDriver) 
    {
    	super(seleniumDriver);
    }
    public static String btnApplyOnlineXpath()
    {
    	return "//a[text()=\"Apply Online \"] ";
    }
    public static String txtNameXpath()
    {
    	return "//input[@name=\"applicant_name\"]";
    }
    
    public static String txtLastnameXpath()
    {
    	return "//input[@name=\"LastName\"]";
    }
    
    public static String txtEmailXpath()
    {
    	return "//input[@name=\"email\"]";
    }
    
    public static String txtCellphoneXpath()
    {
    	return "//input[@name=\"phone\"]";
    }
    public static String txtaMessageXath()
    {
    	return "//textarea[@name=\"message\"]";
    }
    
    public static String btnSendApplicationXpath()
    {
    	return "(//input[@type=\"submit\"])[1]";
    }
    
    public static String errorMessageXpath()
    {
    	return "//span[@class=\"wpjb-glyphs wpjb-icon-attention\"]";
    }
    
    public static String verifyError(String text)
    {
    	return "//span[text()=\"" + text +"\"]";
    }
    
    public static String getHomePageTitle()
    {
    	return SeleniumDriver.getPageTitle();
    }
    
	public JobPostPage addUserDetails(TestData testData) throws ElementNotVisibleException, Exception
	{
		SeleniumDriver.clickElementByXpath(btnApplyOnlineXpath());
		
		seleniumDriver.enterText(LocatorType.XPATH, txtNameXpath(), testData.GetValueFromTestData("Firstname") + " " + testData.GetValueFromTestData("Lastname"));
		seleniumDriver.enterText(LocatorType.XPATH, txtEmailXpath(), testData.GetValueFromTestData("Email"));
		seleniumDriver.enterText(LocatorType.XPATH, txtCellphoneXpath(), testData.GetValueFromTestData("Cellphone"));
		seleniumDriver.enterText(LocatorType.XPATH, txtaMessageXath(), testData.GetValueFromTestData("Message"));
		
		seleniumDriver.Pause(2000);
		SeleniumDriver.clickElementByXpath(btnSendApplicationXpath());
		seleniumDriver.Pause(2000);
		
		seleniumDriver.verifyError(LocatorType.XPATH, verifyError(testData.GetValueFromTestData("Expected Error Message")));
		
		return new JobPostPage(seleniumDriver);
	}
    

}
