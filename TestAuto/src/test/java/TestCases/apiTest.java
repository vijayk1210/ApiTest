package TestCases;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PageObjects.api;
import Utility.Base;
import Utility.util;



public class apiTest extends Base{
	
	// String apiUrl = "https://api.darksky.net/forecast/8874af6be307b40e41b535ce8eb32979/37.8267,-122.4233";
	
	 @DataProvider(name = "Api Object verification")
	  public static Object[][] jsonObjects() {
	 
	        return new Object[][] { {"firstObj","latitude"} ,{"SecondObj","longitude"}};
	 
	  }
	 
	 
	 @Test(dataProvider = "Api Object verification")
		public void verifyResponseObjects(String jsonObj,String jsonExpObjVal){
		 boolean objExists = false;
		 
		 objExists = api.verifyJsonObjects(jsonExpObjVal, util.getTestData("apiUrl"));
		 
		 if(objExists){
			 
			 System.out.println("Test Passed.");
		 }
		 
		 Assert.assertTrue(objExists, "Json object exists in the response.");
		 
	 }
	 
	 
	 
	 @DataProvider(name = "Api data count")
	  public static Object[][] jsonDataCount() {
	 
	        return new Object[][] { {"minutely","61"} ,{"hourly","47"}, {"daily","8"}};
	 
	  }
	 
	 @Test(dataProvider = "Api data count")
		public void verifyResponseObjectDataCount(String jsonObj,String jsonObjDataCount){
		 boolean countMatch = false;
		 
		 countMatch = api.verifyJsonObjectDataCount( util.getTestData("apiUrl"), jsonObj, jsonObjDataCount);
		 
		 if(countMatch){

			 System.out.println("Test Passed.");
		 }
		 
		 Assert.assertTrue(countMatch, "Json object exists in the response.");
		 
	 }
	 
	 

	/*@Test(dataProvider = "Api Object verification")
	public void verifyResponseObjects(String jsonExpObj) {

		Response rsp = RestAssured
				.get("https://api.darksky.net/forecast/8874af6be307b40e41b535ce8eb32979/37.8267,-122.4233");

		String respData = rsp.asString();

		System.out.println("Response data is: " + respData);

		JSONObject jsonObject = new JSONObject(respData);

		check(jsonObject);
		
		

	}

	private void check(JSONObject jsonObject) {
		Iterator<String> iterator = jsonObject.keys();

		while (iterator.hasNext()) {
			String key = iterator.next();
			System.out.print(key + " ");
			if (jsonObject.get(key) instanceof JSONArray) {
				JSONArray jsonArray = (JSONArray) jsonObject.get(key);
				System.out.println("lenght= " + jsonArray.length());
				for (Object jsonObject2 : jsonArray) {
					check((JSONObject) jsonObject2);
				}

			} else {
				if (jsonObject.get(key) instanceof JSONObject)
					check(jsonObject.getJSONObject(key));
				else
					System.out.println(jsonObject.get(key));
			}
			System.out.println();
		}
	}
*/
}
