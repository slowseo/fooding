package com.fooding.payment.action;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.jdt.internal.compiler.batch.Main;

import com.google.gson.JsonObject;

public class TestAjax{
	public static void main(String[] args) {
		try {
			URL url = new URL("https://api.iamport.kr/users/getToken");
			HttpURLConnection http = (HttpURLConnection) url.openConnection();
			
			JsonObject jsonData = new JsonObject();
			jsonData.addProperty("imp_key","1500428451315773");
			jsonData.addProperty("imp_secret","T1rLNPm2YQzEDqYOEmXYGFthFdwEi15aqlhCtkrB2C2pJW455YVrfltaCLqiO7IJw1z8PS0tLtWzcr6B");
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
