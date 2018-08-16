package TestCases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import org.testng.Assert;
import PageObjects.HomePage;
import PageObjects.SearchResultPage;
import Utility.Base;
import Utility.ExtentTestManager;
import Utility.util;


public class SearchResultTests extends Base {

  @DataProvider(name = "flight type")
	  public static Object[][] flightType() {
	 
	        return new Object[][] { {"1","Economy"} ,{"2","Business"}, {"3","First"}};
	 
	  }

  @Test(dataProvider="flight type")
  public void VerifyCheapestPriceDisplayedOrNot(String flightIndex,String flightType) {
	 boolean cheapestPrice = true;
	 
	 HomePage.selectDestination(util.getTestData("destination")); 
	 ExtentTestManager.getTest().log(LogStatus.INFO, "Selected destination."); 
	 
	 HomePage.clickContinueButton();
	 ExtentTestManager.getTest().log(LogStatus.INFO, "Clicked on continue button."); 
	 
	 HomePage.selectTravelDate(util.getTestData("fromDate"));
	 ExtentTestManager.getTest().log(LogStatus.INFO, "Selected fromDate."); 
	 
	 HomePage.selectTravelDate(util.getTestData("toDate"));
	 ExtentTestManager.getTest().log(LogStatus.INFO, "Selected todate"); 
	 
	 HomePage.clickSearchFlights();
	 ExtentTestManager.getTest().log(LogStatus.INFO, "Clicked on search flights"); 
	 
	 
	 if(SearchResultPage.flightTyepVisibleOrNot(flightType)){
	 
	 cheapestPrice = SearchResultPage.cheapestPriceOnTop(util.getTestData("flightBusiness"));
	 
	 Assert.assertTrue(cheapestPrice, "Cheapest price is not getting displayed");
	 
	 ExtentTestManager.getTest().log(LogStatus.PASS, "Cheapest price is getting displayed.");
	 
	 }else{
		 
	 ExtentTestManager.getTest().log(LogStatus.SKIP, "Prices for "+flightType+" are not available.");	 
	 
	 }
	 
  }
  
}
