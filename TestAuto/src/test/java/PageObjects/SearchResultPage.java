package PageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.LogStatus;

import Utility.Base;
import Utility.ExtentTestManager;
import Utility.util;

public class SearchResultPage extends Base {

	private static String flightPriceResultsLocator = "//div[@data-target='leg-OB']/div/div/div[2]/div[@data-class='testData']/span";
	private static String flightTypeLocator = "//div[@data-target='leg-OB']/div/div/div[2]/div[@data-class='testData']";
	
	public static boolean flightTyepVisibleOrNot(String flightType){
		boolean flightVisible = false;
		
		String flightTypeNewLocator = flightTypeLocator.replace("testData", flightType);
		flightVisible = driver.findElement(By.xpath(flightTypeNewLocator)).isDisplayed();
		
		return flightVisible;
	}

	public static int getPriceFromText(String text) {
		int priceVal = 0;

		String[] pricestr = text.split(" ");

		String priceText = pricestr[5];

		String priceFinalText = priceText.replace(",", "");

		priceVal = Integer.parseInt(priceFinalText);

		return priceVal;
	}

	public static boolean cheapestPriceOnTop(String flightType) {
		boolean cheapestPriceDisplayed = true;
		boolean flightResultsVisible = false;

		try {
			
			String flightPriceNewLocator = flightPriceResultsLocator.replace("testData", flightType);
			
			util.isDisplayedWait(flightPriceNewLocator,40);
		 	
			if(flightResultsVisible){
			List<WebElement> priceList = driver.findElements(By.xpath(flightPriceNewLocator));
			List<Integer> otherPrices = new ArrayList<Integer>();
			int cheapestPrice = 0;
			int count = 0;

			for (WebElement pr : priceList) {

				if (count == 0) {

					cheapestPrice = getPriceFromText(pr.getText());

				} else {

					otherPrices.add(getPriceFromText(pr.getText()));

				}

				count++;
			}

			for (Integer chkPrice : otherPrices) {

				if (cheapestPrice > chkPrice) {

					cheapestPriceDisplayed = false;
					break;
				}

			}
			
		 }else{
			 
			 ExtentTestManager.getTest().log(LogStatus.ERROR, "Flight type results didn't appear."); 
		 }
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return cheapestPriceDisplayed;

	}
}
