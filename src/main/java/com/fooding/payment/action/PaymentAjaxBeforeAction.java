package com.fooding.payment.action;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/preparePayment")
public class PaymentAjaxBeforeAction extends HttpServlet {
	 protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
		 
	        response.setContentType("application/json"); // 응답 형식을 JSON으로 설정
	        response.setCharacterEncoding("UTF-8");

	        String apiUrl = "https://api.iamport.kr/payments/prepare"; // 대상 API 엔드포인트
	        String impKey = "imp75410442"; // Imp Key 값
	        String impSecret = "T1rLNPm2YQzEDqYOEmXYGFthFdwEi15aqlhCtkrB2C2pJW455YVrfltaCLqiO7IJw1z8PS0tLtWzcr6B"; // Imp Secret 값

	        try {
	            URL url = new URL(apiUrl);
	            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

	            // POST 요청 설정
	            conn.setRequestMethod("POST");
	            conn.setRequestProperty("Content-Type", "application/json");
	            conn.setRequestProperty("Accept", "application/json");
	            conn.setRequestProperty("Authorization", getAuthorizationHeader(impKey, impSecret));
	            conn.setDoOutput(true);

	            // POST 요청 데이터 준비
	            String postData = "{\"merchant_uid\":\"Your_Merchant_UID\", \"amount\":420000}";
	            try (OutputStream os = conn.getOutputStream()) {
	                byte[] input = postData.getBytes("UTF-8");
	                os.write(input, 0, input.length);
	            }

	            // 응답 읽기
	            try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"))) {
	                String line;
	                StringBuilder responseText = new StringBuilder();
	                while ((line = br.readLine()) != null) {
	                    responseText.append(line);
	                }
	                response.getWriter().write(responseText.toString());
	            }

	            conn.disconnect();
	        } catch (IOException e) {
	            e.printStackTrace();
	            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	            response.getWriter().write("{\"error\":\"An error occurred\"}");
	        }
	    }

	    private String getAuthorizationHeader(String impKey, String impSecret) {
	        String authString = impKey + ":" + impSecret;
	        return "Basic " + java.util.Base64.getEncoder().encodeToString(authString.getBytes());
	    }
	}

