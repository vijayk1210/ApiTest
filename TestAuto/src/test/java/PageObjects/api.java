package PageObjects;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class api {

	public static String getResponseData(String apiUrl) {
		String data = null;
		Response rsp = RestAssured.get(apiUrl);

		data = rsp.asString();

		return data;

	}

	public static boolean verifyJsonObjects(String expVal, String apiUrl) {

		boolean objectPresent = false;

		JSONObject jsonObject = new JSONObject(getResponseData(apiUrl));

		Iterator<String> iterator = jsonObject.keys();

		while (iterator.hasNext()) {

			String key = iterator.next();

			if (expVal.equals(key)) {

				objectPresent = true;
			}

		}

		return objectPresent;

	}

	public static int check(JSONObject jsonObject, String objName, Integer count) {
		int num = count;
		Iterator<String> iterator = jsonObject.keys();
		while (iterator.hasNext()) {
			String key = iterator.next();
			if (key.equals(objName)) {
				if (jsonObject.get(key) instanceof JSONObject) {
					JSONObject dataObj = jsonObject.getJSONObject(key);
					Iterator<String> iterator2 = dataObj.keys();
					while (iterator2.hasNext()) {
						String keyData = iterator2.next();
						if (keyData.equals("data") && dataObj.get(keyData) instanceof JSONArray) {
							JSONArray jsonArray = (JSONArray) dataObj.get(keyData);
							num = num + jsonArray.length();

							System.out.println("lenght required = " + jsonArray.length() + " " + objName + " " + key);

						}
					}
				}

			}
		}
		return num;
	}

	public static boolean verifyJsonObjectDataCount(String apiUrl, String objName, String expCountVal) {
		boolean countMatch = false;
		int dataLenActual = 0;
		int expCount = Integer.parseInt(expCountVal);

		JSONObject jsonObject = new JSONObject(getResponseData(apiUrl));

		int count = 0;
		dataLenActual = check(jsonObject, objName, count);

		System.out.println("Data count is" + dataLenActual);

		if (expCount == dataLenActual) {

			countMatch = true;

		}

		return countMatch;

	}

}
