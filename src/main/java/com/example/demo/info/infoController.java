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

@RequestMapping("/call")
@RestController
public class infoController {

	@RequestMapping(value="/test", method = {RequestMethod.POST})
	public String test(@RequestBody CallModel infoVO) {
		
		
		System.out.println("/call/test call success :: "+infoVO.getPhoneNumber()+"|||"+infoVO.getMessage());
		return "result 200";
	}
	@RequestMapping(value="/save", method = {RequestMethod.POST})
	public String save(@RequestBody HashMap<String, Object> infoVO) {
		
//		JSONParser jsonParser = new JSONParser();
//		JSONObject jsonObj = (JSONObject) jsonParser.parse(infoVO);

		System.out.println("/call/save call success :: ");
		System.out.println("infoVO :: "+infoVO);
		return "result 200";
	}
}
