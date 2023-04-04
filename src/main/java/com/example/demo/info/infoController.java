package com.example.demo.info;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;


@RequestMapping("/call")
@RestController
public class infoController {

	@RequestMapping(value="/send", method = {RequestMethod.POST})
	public String test(@RequestBody String infoVO) throws ParseException{
		
		JSONParser parser = new JSONParser();
	    Object obj  = parser.parse(infoVO);
	    JSONObject json = (JSONObject) obj;
		
	    JSONArray inArguments = (JSONArray)json.get("inArguments");
	    JSONObject inArgumentsTmp = (JSONObject)inArguments.get(0);
		JSONObject t_data = (JSONObject)inArgumentsTmp.get("t_data");
		
		// 발신 정보 set
		String snPhnum = "01020949987"; //발신번호
		String mbnum = inArgumentsTmp.get("phoneNumber").toString();	// 수신번호
		String msgCotn = t_data.get("message").toString();				// 내용
			
		System.out.println("mbnum 	::: "+mbnum);
		System.out.println("msgCotn ::: "+msgCotn);
		//sms 발송
		sendSMS(snPhnum, mbnum, msgCotn);
		
		return "result 200";
	}
	
	public void sendSMS(String snPhnum,String mbnum,String msgCotn) {
		// megabird api 호출
		String serviceURL = "https://api.megabird.co.kr:8080";
		
		String token = "eyJhbGciOiJIUzM4NCJ9.eyJ0b2tlbkRpdiI6IjNyZCIsIm1lbWJlcklkIjoiNDc2YWRlYTE4NzVjNDI4ZGI3NGJmNjAwN2ZkYWM2MTUiLCJpc0FjY2Vzc1Rva2VuIjp0cnVlLCJpYXQiOjE2Nzc4MjcxNTksImV4cCI6MzIzMzAyNzE1OX0.j0YkDKb0ERHQiNulIaaj4EoCPYY5WvLTM0KQ6qNihLecGiZn912KC0NWFoeUM5cC";
		
		try {
			String apiURL = serviceURL + "/v1/openapi/sms/send";
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("Authorization", token);
			
			// 메시지 수신자
			JSONArray messageReceiverList = new JSONArray();
			JSONObject messageReceiver = new JSONObject();
			messageReceiver.put("mbnum", mbnum);
			messageReceiverList.add(messageReceiver);
			
			// 메시지 항목
			JSONObject params = new JSONObject();
			params.put("svcKndCd", "SMS");
			params.put("msgCotn", msgCotn);
			params.put("adIncluYn", "N");
			params.put("snPhnum", snPhnum);
			params.put("messageReceiverList", messageReceiverList);
			
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.write(params.toString().getBytes(StandardCharsets.UTF_8));
			wr.flush();
			wr.close();

			int responseCode = con.getResponseCode();
			BufferedReader br;
			if(responseCode==200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {  // 에러 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}

			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}
			br.close();
			System.out.println(response.toString());
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	@RequestMapping(value="/save", method = {RequestMethod.POST})
	public String save(@RequestBody HashMap<String, Object> infoVO) {

		return "result 200";
	}
}
