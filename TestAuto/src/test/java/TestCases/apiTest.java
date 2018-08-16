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

import com.relevantcodes.extentreports.LogStatus;

import PageObjects.api;
import Utility.Base;
import Utility.ExtentTestManager;
import Utility.util;



public class apiTest {
	
	
	
	 @DataProvider(name = "Api Object verification")
	  public static Object[][] jsonObjects() {
	 
	        return new Object[][] { {"firstObj","latitude"} ,{"SecondObj","longitude"},{"ThirdObj","timezone"} ,
	        	{"FourthObj","currently"},{"fifthObj","minutely"} ,{"sixthObj","hourly"},{"seventhObj","daily"} ,{"eightObj","flags"},{"ninthObj","offset"}};
	 
	  }
	 
	 
	 @Test(dataProvider = "Api Object verification")
		public void verifyResponseObjects(String jsonObj,String jsonExpObjVal){
		 boolean objExists = false;
		 
		 objExists = api.verifyJsonObjects(jsonExpObjVal, util.getTestData("apiUrl"));
		 
		 if(objExists){
			 
			System.out.println("Test passed for "+jsonExpObjVal);
		 }
		 
		 Assert.assertTrue(objExists, "Json object exists in the response.");
		 
	 }
	 
	 
	 
	 @DataProvider(name = "Api data count")
	  public static Object[][] jsonDataCount() {
	 
	        return new Object[][] { {"minutely","61"} ,{"hourly","49"}, {"daily","8"}};
	 
	  }
	 
	 @Test(dataProvider = "Api data count")
		public void verifyResponseObjectDataCount(String jsonObj,String jsonObjDataCount){
		 boolean countMatch = false;
		 
		 countMatch = api.verifyJsonObjectDataCount( util.getTestData("apiUrl"), jsonObj, jsonObjDataCount);
		 
		 if(countMatch){

			 System.out.println("Data count matched for "+jsonObj);
		 }
		 
		 Assert.assertTrue(countMatch, "Json object exists in the response.");
		 
	 }
	 
	 

}
