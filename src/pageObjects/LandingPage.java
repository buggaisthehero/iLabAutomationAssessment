package pageObjects;

import java.util.concurrent.TimeoutException;

import org.openqa.selenium.ElementNotVisibleException;

import helperObjects.TestBase;
import utilities.SeleniumDriver;

public class LandingPage extends TestBase {
	
    public LandingPage(SeleniumDriver seleniumDriver) 
    {
    	super(seleniumDriver);
    }
    
    public static String careersLinkXpath()
    {
    	return "(//a[text()=\"CAREERS\"])[1]";
    }
    
    public static String getLandingPageTitle()
    {
    	return SeleniumDriver.getPageTitle();
    }
    
	public LandingPage navigateToLandingPage(String url) throws TimeoutException
	{
		seleniumDriver.navigateToPage(url);
		
		return new LandingPage(seleniumDriver);
	}
    
    public boolean clickCareersLink() throws ElementNotVisibleException, Exception
    {
    	return SeleniumDriver.clickElementByXpath(careersLinkXpath());
    }

}
