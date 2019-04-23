package tests;

import org.openqa.selenium.ElementNotVisibleException;

import enummerables.Enums.BrowserTypes;
import enummerables.Enums.LoggingType;
import helperObjects.Logging;
import helperObjects.TestBase;
import helperObjects.TestData;
import pageObjects.LandingPage;
import pageObjects.JobPostPage;
import pageObjects.CareersPage;




public class AddUserTest extends TestBase 
{
	Logging logging = new Logging();
	
	public static void main(String[] args) throws ElementNotVisibleException, Exception
	{
		LandingPage landingPage;
		CareersPage careersPage;
		JobPostPage jobPostPage;

		for(BrowserTypes browser: appConfig.getSelectedBrowsers())
		{
	 	 	TestBase.prepareSuite("UsersInputFile", browser);
	 	 	TestBase.setupTest("AddiLabUser");
	 	 	
	 	 	landingPage = new LandingPage(seleniumDriver);
	 	 	careersPage = new CareersPage(seleniumDriver);
	 	 	jobPostPage = new JobPostPage(seleniumDriver);
	 		
	 	 	landingPage.navigateToLandingPage(appConfig.getSelectedEnvironment().urlUnderTest);
	 	 	landingPage.clickCareersLink();
	 	 	careersPage.clickSouthAfricaLink();
	 	 	careersPage.clickFirstJobInCurrentOpeningsList();
	 	 	
	 	 	for(TestData tData : testDataList)
	 	 	{
	 	 		jobPostPage.addUserDetails(tData);
		 	 	testResult.setStatus(true);
	 	 	}
	 	 	
	
	 		
	 		TestBase.endTest();
	 		TestBase.closeBrowser();
		}

	}
}

