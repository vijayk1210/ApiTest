package PageObjects;

import org.openqa.selenium.By;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.relevantcodes.extentreports.LogStatus;

import Utility.Base;
import Utility.ExtentTestManager;
import Utility.util;

public class HomePage extends Base {
	
	private static String arrivalAirportLocator = "//div[@class='field js-fields field--active']/input[@name='Arrival airport']";
	private static String continueButtonLocator = "//a[@class='cta cta--large cta--primary js-continue-search-flight search-flight__continue--cta ']";
	private static String dateLocator = "//eol-calendar[@class='eol-calendar--visible eol-calendar--top']/../..//td[@data-string='testData']";
	private static String searchFlightsLocator = "//button[@type='submit']/span[text()='Search flights']";
	
	
	
	public static void selectDestination(String destination){
		try{
			
		util.isDisplayedWait(arrivalAirportLocator,30);
		driver.findElement(By.xpath(arrivalAirportLocator)).sendKeys(destination);
		
		}catch(ElementNotFoundException e){
			
			ExtentTestManager.getTest().log(LogStatus.ERROR, "Element not found.");
		}
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
