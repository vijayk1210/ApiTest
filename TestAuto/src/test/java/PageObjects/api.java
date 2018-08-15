package PageObjects;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class api {
	
	public static String getResponseData(String apiUrl){
		String data = null;
		Response rsp = RestAssured.get(apiUrl);

		data = rsp.asString();
		
		return data;
		
	}
	
	public static boolean verifyJsonObjects(String expVal , String apiUrl){
		
		boolean objectPresent = false;

		JSONObject jsonObject = new JSONObject(getResponseData(apiUrl));
		
		Iterator<String> iterator = jsonObject.keys();

		while (iterator.hasNext()) {
			
			String key = iterator.next();
			
			if(expVal.equals(key)){
				
				objectPresent = true;
			}
			
		}
		
		return objectPresent;
		
		
	}
	
	public static int check(JSONObject jsonObject,String objName) {
		int count = 0;
		
			if (jsonObject.get(objName) instanceof JSONArray) {
				JSONArray jsonArray = (JSONArray) jsonObject.get(objName);
				count  = jsonArray.length();
				
				System.out.println("lenght= " + jsonArray.length());
				
				for (Object jsonObject2 : jsonArray) {
					
					check((JSONObject) jsonObject2,objName);
				}

			} else {
				if (jsonObject.get(objName) instanceof JSONObject)
					check(jsonObject.getJSONObject(objName),objName);
				else
					System.out.println(jsonObject.get(objName));
			}
			
			return count;
		}
		
		
	
	
	public static boolean verifyJsonObjectDataCount(String apiUrl,String objName,String expCountVal){
		boolean countMatch = false;
		int dataLenActual = 0;
		int expCount = Integer.parseInt(expCountVal);
		
        JSONObject jsonObject = new JSONObject(getResponseData(apiUrl));
		
		dataLenActual = check(jsonObject,objName);

		if(expCount==dataLenActual){
			
			countMatch = true;
			
		}
		
		return countMatch;
		
	}

}
