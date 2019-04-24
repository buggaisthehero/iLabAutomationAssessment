package utilities;

import enummerables.Enums.*;
import helperObjects.Logging;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public final class AppConfig
{
	Logging logger;
	public Properties properties = new Properties();
	
    private String UsersInputFile;
    private String username, password;
    private String ReportFileDirectory, browserTypeConfig, environmentConfig;
    
    private File appConfig = new File("src//config.properties");

    private int waitTimeout;
    private List<BrowserTypes> browserTypes;
    private List<Platforms> platforms;
    private String environment;
    public Environment environmentEnum;

    public int WaitTimeout()
    {
        return waitTimeout;
    }
    
    public String getUsersInputFile() 
    { 
    	return UsersInputFile;
    }
    
    public String getUsername() 
    { 
    	return username;
    }
    
    public String getPassword() 
    { 
    	return password;
    }

    public List<BrowserTypes> getSelectedBrowsers()
    {
        return browserTypes;
    }
    
    public List<Platforms> SelectedPlatforms()
    {
        return platforms;
    }
    
    public Environment getSelectedEnvironment()
    {
        return environmentEnum;
    }
    
    public String GetEnvironment()
    {
        return environment;
    }

    public AppConfig()
    {
        try
        {
        	this.loadExistingConfigurationFile();
            this.loadConfigurationSettings();
        }
        catch(Exception e)
        {
        	logger.printLog(LoggingType.ERROR, "AppConfig() instantiation failed." + e.getMessage());
        }

    }

    private void loadConfigurationSettings()
    {
        try
        {
        	//Get properties from configuration file
        	UsersInputFile = properties.getProperty("UsersInputFile");
            ReportFileDirectory = properties.getProperty("ReportFileDirectory"); 
            username = properties.getProperty("Username"); 
            password = properties.getProperty("Password"); 
            waitTimeout = Integer.parseInt(properties.getProperty("waitTimeout"));
            browserTypes = resolveBrowserType();
            platforms = resolvePlatforms();
            environment = properties.getProperty("Environment");

            //Determine run environment to execute tests against 
            environmentEnum = resolveEnvironment();
        }
        catch(Exception e)
        {
        	logger.printLog(LoggingType.ERROR, "The configuration file was not properly loaded." + e.getMessage());
        }           
    }
    
    private Environment resolveEnvironment() 
    {
        environmentConfig = properties.getProperty("Environment");
        
        switch(environmentConfig.toUpperCase())
        {
            case "DEV":
                return Environment.Dev;
            case "TESTING":
                return Environment.QA;
            case "PROD":
                return Environment.Prod;
            default:
                return Environment.QA;
        }
    }
    
    private List<BrowserTypes> resolveBrowserType()
    {
    	String[] configBrowserTypes = properties.getProperty("BrowserType").split(":");
    	List<BrowserTypes> browsers = new ArrayList<BrowserTypes>();
       
        
        for(String browser : configBrowserTypes)
        {
        	switch(browser.toUpperCase())
		     {
		         case "CHROME":
		             browsers.add(BrowserTypes.Chrome);
		             break;
		         case "IE":
		        	 browsers.add(BrowserTypes.IE);
		             break;
		         default: 
		        	 browsers.add(BrowserTypes.Chrome);
		     }
        }
        	 
        
        
        return browsers;
    }
    
    private List<Platforms> resolvePlatforms()
    {
    	String[] configPlatforms = properties.getProperty("Platforms").split(":");
        
    	List<Platforms> platforms = new ArrayList<Platforms>();
        
        
        for(String platform : configPlatforms)
        {
        	 switch(platform.toUpperCase())
             {
                 case "WINDOWS":
                	 platforms.add(Platforms.Windows);
                	 break;
                 case "MAC":
                	 platforms.add(Platforms.OS_X);
                     break;
                 default: 
                	return platforms;
             }
        	 
        }
        
        return platforms;
    }
  
    private boolean loadExistingConfigurationFile()
    {
        try
        {  
            if(properties == null)
            {
            	properties = new Properties();
            }
            
            properties.load(new FileInputStream(appConfig.getAbsolutePath()));

            return true;
           
        }
        catch(FileNotFoundException e)
        {
        	 logger.printLog(LoggingType.ERROR, "Configuration file was not found." + e.getMessage());
             return false;
        }
        catch(IOException e)
        {
        	 logger.printLog(LoggingType.ERROR, "Could not read the configuration file, might be corrupt." + e.getMessage());
             return false;
        }
    }

    
    
}


