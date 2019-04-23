package pageObjects;

import org.openqa.selenium.ElementNotVisibleException;

import helperObjects.TestBase;
import utilities.SeleniumDriver;

public class CareersPage extends TestBase{
	
    public CareersPage(SeleniumDriver seleniumDriver) 
    {
    	super(seleniumDriver);
    }
    
    public static String southAfricaLinkXpath()
    {
    	return "//a[text()=\"South Africa\"]";
    }
    
    public static String firstJobInCurrentOpeningsList()
    {
    	return "(//span[@class=\"wpjb-line-major\"])[1]";
    }
    
    public boolean clickSouthAfricaLink() throws ElementNotVisibleException, Exception
    {
    	return SeleniumDriver.clickElementByXpath(southAfricaLinkXpath());
    }
    
    public boolean clickFirstJobInCurrentOpeningsList() throws ElementNotVisibleException, Exception
    {
    	return SeleniumDriver.clickElementByXpath(firstJobInCurrentOpeningsList());
    }

}
