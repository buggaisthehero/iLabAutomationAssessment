package utilities;

import java.io.File;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import enummerables.Enums.BrowserTypes;
import enummerables.Enums.LocatorType;
import enummerables.Enums.LoggingType;
import helperObjects.Logging;
import helperObjects.TestBase;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;

public class SeleniumDriver extends TestBase
{
	static Logging logger;
  
    private static WebDriver webDriver;
    private final File browserDriverFile;
    private boolean browserStarted = false;
       
    public SeleniumDriver(String driverLocation, BrowserTypes browserType) throws Exception
    {
        this.browserDriverFile = new File(driverLocation);
        SeleniumDriver.logger = new Logging();
        
    	switch(browserType)
    	{
    		case Chrome:
    			 System.setProperty("webdriver.chrome.driver", this.browserDriverFile.getAbsolutePath());
    			 break;
    		case Edge:
    			System.setProperty("webdriver.edge.driver", this.browserDriverFile.getAbsolutePath());
    			break;
    		default:

    	}
    	
       
    }

	public boolean startWebDriver(BrowserTypes browserType)
    {
            switch (browserType) 
            {
	            case Chrome:
	            {
	            	SeleniumDriver.webDriver = new ChromeDriver();
	                this.browserStarted = true;
	                break;
	            }
                case Edge: 
                {
                    SeleniumDriver.webDriver = new EdgeDriver();
                    this.browserStarted = true;
                    break;
                }

            }

        SeleniumDriver.webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        SeleniumDriver.webDriver.manage().timeouts().pageLoadTimeout(2000, TimeUnit.SECONDS);
        SeleniumDriver.webDriver.manage().window().maximize();
        
        logger.printLog(LoggingType.INFO,"WebDriver successfully started");
        
        return this.browserStarted;

    }
    public boolean navigateToPage(String url)
    		throws TimeoutException
    {
        try
        {
            webDriver.navigate().to(url); 
            logger.printLog(LoggingType.INFO,"NavigateToPage (" + url + ")");
            
            return true;
        }
        catch(NoSuchElementException e)
        {
            logger.printLog(LoggingType.ERROR,"NavigateToPage (" + url + ") " + e.getMessage());
            
            return false;
        }
        catch(Exception e)
        {
            logger.printLog(LoggingType.ERROR,"Unable to navigate to (" + url + ") \n" + e.getMessage());
            return false;
        }
    }
    
    public WebElement FindWebElement(LocatorType locatorType, String value) 
    		throws ElementNotVisibleException, Exception
    {
        try
        {
            By locator = LocatorValue(locatorType, value);
            WebElement element = webDriver.findElement(locator);

            return element;
        }
        catch(ElementNotVisibleException e)
        {
            logger.printLog(LoggingType.ERROR,"Element not visible: " + e.getMessage());
            throw(e);
        }
        catch(Exception e)
        {
            logger.printLog(LoggingType.ERROR,"Unable to find web element (" + value + ") \n" + e.getMessage());
            throw(e);
        }
    }
    public boolean verifyError(LocatorType locatorType, String text) 
    		throws ElementNotVisibleException, Exception
    {
    	
        By locator = LocatorValue(locatorType, text);
        WebElement element = webDriver.findElement(locator);

        if(element.isDisplayed()) {
        	return true;
        }
        else {
        	return false;
        }
    }   

    public boolean enterText(LocatorType locatorType, String value, String text) 		
    		throws ElementNotVisibleException, Exception
    {
        try 
        {
            By locator = LocatorValue(locatorType, value);
            WebElement element = webDriver.findElement(locator);

            element.clear();
            element.sendKeys(text);
            
            logger.printLog(LoggingType.INFO,"enterText (" + text + ")");
            return true;
        }
        catch(ElementNotVisibleException e)
        {
            logger.printLog(LoggingType.ERROR,"Element not visible: " + value);
            return false;
        }
        catch(Exception e)
        {
            logger.printLog(LoggingType.ERROR,"Unable to enter the text (" + text + ") \n" + e.getMessage());
            return false;
        }
    }
    
    
    public boolean selectOption(LocatorType locatorType, String value, String text) 
    		throws ElementNotVisibleException, Exception
    {
        try
        {
            By locator = LocatorValue(locatorType, value);
            WebElement element = webDriver.findElement(locator);
            Select dropdown= new Select(element);
            
            dropdown.selectByVisibleText(text);
            
            logger.printLog(LoggingType.INFO,"selectOption(" + text + ")");
            return true;
        }

        catch(ElementNotVisibleException e)
        {
        	logger.printLog(LoggingType.ERROR,"Element not visible: " + value);
            return false;
        }
        catch(Exception e)
        {
        	logger.printLog(LoggingType.ERROR,"Unable to select the text (" + text + ") \n" + e.getMessage());
            return false;
        }
    }
    
    public static boolean clickElementByCssSelector(String cssSelector) 
    		throws ElementNotVisibleException, Exception
    {
        try
        {
            webDriver.findElement(By.cssSelector(cssSelector)).click();
            logger.printLog(LoggingType.INFO,"clickElementByCssSelector (" + cssSelector + ")");
            return true;
        }
        catch(ElementNotVisibleException e)
        {
            logger.printLog(LoggingType.ERROR,"Element not visible: " + e.getMessage());
            return false;
        }
        catch(Exception e)
        {
            logger.printLog(LoggingType.ERROR,"Unable to click element by cssSelector (" + cssSelector + ") \n" + e.getMessage());
            return false;
        }
    }
    
    public static boolean clickElementByXpath(String xpath) 
    		throws ElementNotVisibleException, Exception
    {
        try
        {
            webDriver.findElement(By.xpath(xpath)).click();
            logger.printLog(LoggingType.INFO,"clickElementByXpath (" + xpath + ")");
            return true;
        }
        catch(ElementNotVisibleException e)
        {
            logger.printLog(LoggingType.ERROR,"Element not visible: " + e.getMessage());
            return false;
        }
        catch(Exception e)
        {
            logger.printLog(LoggingType.ERROR,"Unable to click element by click element by xpath (" + xpath + ") \n" + e.getMessage());
            return false;
        }
    }
    
    public static boolean clickElementByID(String id) 
    		throws ElementNotVisibleException, Exception
    {
        try
        {
            webDriver.findElement(By.id(id)).click();
            logger.printLog(LoggingType.INFO,"clickElementByID (" + id + ")");
            return true;
        }

        catch(ElementNotVisibleException e)
        {
            logger.printLog(LoggingType.ERROR,"Element not visible (" + id + ") \n" + e.getMessage());
            return false;
        }
        catch(Exception e)
        {
            logger.printLog(LoggingType.ERROR,"Unable to click element by id (" + id + ") \n" + e.getMessage());
            return false;
        }
    }

    public By LocatorValue(LocatorType locatorType, String value) 
    {
        By by;
        switch (locatorType) 
        {
            case ID:
                    by = By.id(value);
                    break;
            case NAME:
                    by = By.name(value);
                    break;
            case XPATH:
                    by = By.xpath(value);
                    break;
            case CSS:
                    by = By.cssSelector(value);
                    break;
            case LINK_TEXT:
                    by = By.linkText(value);
                    break;
            case PARTIAL_LINK_TEXT:
                    by = By.partialLinkText(value);
                    break;
            case CLASS_NAME:
                    by = By.className(value);
                    break;
            default:
                    by = null;
                    break;
        }
        return by;
    }
   
    public boolean WaitForElement(org.openqa.selenium.WebElement element) throws TimeoutException, InterruptedException
    {
        boolean elementFound = false;
        int maxWaitTime = 60;
        int waitCount = 0;
        
		while(!elementFound && waitCount < maxWaitTime)
		{
		    //if(webDriver.findElement(element))
		        
		    elementFound = true;

		    Thread.sleep(500);
		    waitCount ++;
		}
		
		return elementFound;
            
    }
    
    public void Pause(int milliseconds)
    {
        try
        {
            Thread.sleep(milliseconds);
        }
        catch(Exception e)
        {
             logger.printLog(LoggingType.ERROR,"Pause (" + e.getMessage() + ")");
        }      
    }
    
    public static String getPageTitle()
    {
    	return webDriver.getTitle();
    }
    
    public WebDriver GetWebDriver()
    {
    	try
    	{
    		return webDriver;
    	}
    	catch(Exception e)
    	{
    		logger.printLog(LoggingType.ERROR,"GetWebDriver (" + e.getMessage() + ")");
    		return this.webDriver;
    	}
    }

    public static void closeBrowser() 
    {
    	if(webDriver != null)
            webDriver.quit();
    	else
    		logger.printLog(LoggingType.INFO, "The web driver has not been initialised.");
    }  
    


}

