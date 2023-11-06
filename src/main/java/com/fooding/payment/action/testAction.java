package com.fooding.payment.action;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import java.io.BufferedReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fooding.util.Action;
import com.fooding.util.ActionForward;

public class testAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//
//		BufferedReader reader = request.getReader();
//	    StringBuilder sb = new StringBuilder();
//	    String line;
//	    while ((line = reader.readLine()) != null) {
//	        sb.append(line);
//	    }
//
//	    String postData = sb.toString();
//		
//		
		
		        try {
		            // req의 body에서 imp_uid, merchant_uid 추출
		            BufferedReader reader = request.getReader();
		            String line;
		            StringBuilder requestBody = new StringBuilder();
		            while ((line = reader.readLine()) != null) {
		                requestBody.append(line);
		            }

		            // 액세스 토큰(access token) 발급 받기
		            String getTokenUrl = "https://api.iamport.kr/users/getToken";
		            URL url = new URL(getTokenUrl);
		            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		            connection.setRequestMethod("POST");
		            connection.setRequestProperty("Content-Type", "application/json");
		            connection.setDoOutput(true);

		            String jsonInputString = "{\"imp_key\": \"imp_apikey\", \"imp_secret\": \"ekKoeW8RyKuT0zgaZsUtXXTLQ4AhPFW3ZGseDA6bkA5lamv9OqDMnxyeB9wqOsuO9W3Mx9YSJ4dTqJ3f\"}";

		            try (OutputStream os = connection.getOutputStream()) {
		                byte[] input = jsonInputString.getBytes("UTF-8");
		                os.write(input, 0, input.length);
		            }

		            int responseCode = connection.getResponseCode();
		            if (responseCode == HttpURLConnection.HTTP_OK) {
		                InputStreamReader reader = new InputStreamReader(connection.getInputStream());
		                BufferedReader in = new BufferedReader(reader);
		                String inputLine;
		                StringBuilder resp = new StringBuilder();

		                while ((inputLine = in.readLine()) != null) {
		                    response.append(inputLine);
		                }
		                in.close();

		                // TODO: 이후 로직 작성
		            } else {
		                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		            }
		        } catch (Exception e) {
		            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		        }
		    }
		}

		
		return null;
	}

}
