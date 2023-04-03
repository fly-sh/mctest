package com.example.demo.info;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.example.demo.info.model.CallModel;
import com.fasterxml.jackson.annotation.JsonAlias;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@RequestMapping("/call")
@RestController
public class infoController {

	@RequestMapping(value="/test", method = {RequestMethod.POST})
	public String test(@RequestBody HashMap<String, Object> infoVO) {
		
		System.out.println("/call/test call success :: ");
		System.out.println("infoVO.get(\"mode\") :: "+infoVO.get("mode"));
		System.out.println("infoVO.get(\"keyValue\") :: "+infoVO.get("keyValue"));
		System.out.println("infoVO.get(\"inArguments\") :: "+infoVO.get("inArguments"));
		
		JSONObject json =  new JSONObject(infoVO);
		System.out.println("dataExtensionObj ::::: "+json.get("dataExtensionObj"));
		System.out.println("t_data :::: "+json.get("t_data"));
		
		// megabird api 호출
		String serviceURL = "https://api.megabird.co.kr:8080";
		
		String token = "eyJhbGciOiJIUzM4NCJ9.eyJ0b2tlbkRpdiI6IjNyZCIsIm1lbWJlcklkIjoiNDc2YWRlYTE4NzVjNDI4ZGI3NGJmNjAwN2ZkYWM2MTUiLCJpc0FjY2Vzc1Rva2VuIjp0cnVlLCJpYXQiOjE2Nzc4MjcxNTksImV4cCI6MzIzMzAyNzE1OX0.j0YkDKb0ERHQiNulIaaj4EoCPYY5WvLTM0KQ6qNihLecGiZn912KC0NWFoeUM5cC";
		
//		try {
//			String apiURL = serviceURL + "/api/v1/openapi/sms";
//			URL url = new URL(apiURL);
//			HttpURLConnection con = (HttpURLConnection)url.openConnection();
//			con.setRequestMethod("POST");
//			con.setRequestProperty("Content-Type", "application/json");
//			con.setRequestProperty("Authorization", token);
//			
//			// 메시지 수신자
//			JSONArray messageReceiverList = new JSONArray();
//			JSONObject messageReceiver = new JSONObject();
//			messageReceiver.put("mbnum", "01034563456");
//			messageReceiver.put("tgusNm", "김나라");
//			messageReceiverList.add(messageReceiver);
//			
//			// 메시지 항목
//			JSONObject params = new JSONObject();
//			params.put("svcKndCd", "SMS");
//			params.put("msgTtl", "메시지제목");
//			params.put("msgCotn", "메시지내용\n확인해주세요.");
//			params.put("adIncluYn", "N");
//			params.put("snPhnum", "01012341234");
//			params.put("messageReceiverList", messageReceiverList);
//			
//			con.setDoOutput(true);
//			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
//			wr.write(params.toString().getBytes(StandardCharsets.UTF_8));
//			wr.flush();
//			wr.close();
//
//			int responseCode = con.getResponseCode();
//			BufferedReader br;
//			if(responseCode==200) { // 정상 호출
//				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
//			} else {  // 에러 발생
//				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
//			}
//
//			String inputLine;
//			StringBuffer response = new StringBuffer();
//			while ((inputLine = br.readLine()) != null) {
//				response.append(inputLine);
//			}
//			br.close();
//			System.out.println(response.toString());
//			
//		} catch (Exception e) {
//			System.out.println(e);
//		}
		
		
		return "result 200";
	}
	@RequestMapping(value="/save", method = {RequestMethod.POST})
	public String save(@RequestBody HashMap<String, Object> infoVO) {

		return "result 200";
	}
}
