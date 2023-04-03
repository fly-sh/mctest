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
	public String test(@RequestBody HashMap<String, Object> infoVO) {
		
		
		System.out.println("/call/test call success :: ");
		System.out.println("infoVO.get(\"mode\") :: "+infoVO.get("mode"));
		System.out.println("infoVO.get(\"keyValue\") :: "+infoVO.get("keyValue"));
		System.out.println("infoVO.get(\"inArguments\") :: "+infoVO.get("inArguments"));
		return "result 200";
	}
	@RequestMapping(value="/save", method = {RequestMethod.POST})
	public String save(@RequestBody HashMap<String, Object> infoVO) {

		return "result 200";
	}
}
