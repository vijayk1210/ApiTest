package com.testautomation.TestAuto;

import java.io.IOException;

import org.codehaus.jackson.map.util.JSONPObject;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

/**
 * Hello world!
 *
 */
public class App {

	static OkHttpClient client = new OkHttpClient();
	static Example ex;
	

	public static void main(String[] args) {
		try {
			Response response = whenGetRequest_thenCorrect();
	        Gson gson = new GsonBuilder().create();
	        JSONObject json=new JSONObject(response.body().string());
	        System.out.println(json.get("latitude"));
	        Example ex=new Example();
	        ex.setLatitude(json.getDouble("latitude"));
			Example exs = gson.fromJson(response.body().string(), Example.class);
			System.out.println(ex.getHourly().getData().size());


			
			System.out.println("test");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// code request code here

		
		
		
	}
	public static Response whenGetRequest_thenCorrect() throws IOException {
	    Request request = new Request.Builder()
	      .url( "https://api.darksky.net/forecast/8874af6be307b40e41b535ce8eb32979/37.8267,-122.4233")
	      
	      .build();
	 
	    Call call = client.newCall(request);
	    return call.execute();
	 
	}

}
