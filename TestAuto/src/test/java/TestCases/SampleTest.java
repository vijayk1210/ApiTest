package TestCases;

import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class SampleTest {
	
	@Test
	public void verifyResponseObjects() {

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
}
