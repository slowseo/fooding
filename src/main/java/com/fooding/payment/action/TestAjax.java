package com.fooding.payment.action;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.eclipse.jdt.internal.compiler.batch.Main;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import netscape.javascript.JSObject;

public class TestAjax{
	public static void main(String[] args) {
		JsonObject a = new JsonObject();
		JsonArray jarr = new JsonArray();
		
		for(int i =1; i<3;i++) {
			JsonObject data = new JsonObject();
			data.put 
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//   public JsonObject action() {
//	   HttpURLConnection conn = null;
//	   String access_token = null;
//	   try {
//		   URL url = new URL("https://api.iamport.kr/users/getToken");
//		   conn= (HttpURLConnection) url.openConnection();
//		   
//		   conn.setRequestMethod("POST");
//		   // Header ?
//		   conn.setRequestProperty("Content-Type", "application/json");
//		   conn.setRequestProperty("Accept", "application/json");
//		   
//		   conn.setDoOutput(true); // outputStream으로 POST 데이터를 넘겨주겠대
//		   
//		   JsonObject obj = new JsonObject();
//		   obj.addProperty("imp_key", "1500428451315773");
//		   obj.addProperty("imp_secret", "T1rLNPm2YQzEDqYOEmXYGFthFdwEi15aqlhCtkrB2C2pJW455YVrfltaCLqiO7IJw1z8PS0tLtWzcr6B");
//		   
//		   BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
//		   bw.write(obj.toString());
//		   bw.flush();
//		   bw.close();
//		   
//		   int result = 0;
//		   int responseCode = conn.getResponseCode();
//		   System.out.println("응답코드 = "+responseCode);
//		   
//	} catch (Exception e) {
//	}
//	return null;
//   };
}
	

