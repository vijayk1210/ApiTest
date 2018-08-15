package PageObjects;

import org.openqa.selenium.By;

import Utility.Base;

public class HomePage extends Base {
	
	private static String arrivalAirportLocator = "//input[@id='6587b3fb-9222-4906-b323-61a097c3119e']";
	private static String continueButtonLocator = "//a[@class='cta cta--large cta--primary js-continue-search-flight search-flight__continue--cta ']";
	private static String dateLocator = "//eol-calendar[@data-component-id='c8b3-1177-4f53-6388']/../..//td[@data-string='testData']";
	private static String searchFlightsLocator = "//button[@type='submit']/span[text()='Search flights']";
	
	
	
	public static void selectDestination(String destination){
		
		driver.findElement(By.xpath(arrivalAirportLocator)).sendKeys(destination);
	}
	
	public static void clickContinueButton(){
		
		driver.findElement(By.xpath(continueButtonLocator)).click();
	}
	
	public static void selectTravelDate(String date){
		
		String dateLocatorNew = dateLocator.replace("testData",date);
		
		driver.findElement(By.xpath(dateLocatorNew)).click();
		
	}
	
	
	public static void clickSearchFlights(){
		
		driver.findElement(By.xpath(searchFlightsLocator)).click();
	}

}
