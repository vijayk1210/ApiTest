package PageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Utility.Base;

public class SearchResultPage extends Base {

	private static String flightPriceResultsLocator = "//div[@data-target='leg-OB']/div/div/div[2]/div[@data-class='testData']/span";

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

		try {

			List<WebElement> priceList = driver.findElements(By.xpath(flightPriceResultsLocator));
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

		} catch (Exception e) {

			e.printStackTrace();
		}

		return cheapestPriceDisplayed;

	}
}
