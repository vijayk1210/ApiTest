package TestCases;

import org.testng.annotations.Test;
import org.testng.Assert;
import PageObjects.HomePage;
import PageObjects.SearchResultPage;
import Utility.Base;
import Utility.util;

@Test
public class SearchResultTests extends Base {
	
	
  public void VerifyCheapestPriceDisplayedOrNot() {
	 boolean cheapestPrice = true;
	 HomePage.selectDestination(util.getTestData("destination")); 
	 
	 HomePage.clickContinueButton();
	 
	 HomePage.selectTravelDate(util.getTestData("fromDate"));
	 
	 HomePage.selectTravelDate(util.getTestData("toDate"));
	 
	 HomePage.clickSearchFlights();
	 
	 cheapestPrice = SearchResultPage.cheapestPriceOnTop(util.getTestData("flightBusiness"));
	 
	 Assert.assertTrue(cheapestPrice, "Cheapest price is not getting displayed");
	 
  }
  
}
