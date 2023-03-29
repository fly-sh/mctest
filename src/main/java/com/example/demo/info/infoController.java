package com.example.demo.info;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.info.model.CallModel;

@RequestMapping("/call")
@RestController
public class infoController {

	@RequestMapping(value="/test", method = {RequestMethod.POST})
	public String test(@RequestBody CallModel infoVO) {
		System.out.println("infoVO :: "+infoVO.toString());
		System.out.println("/call/test call success");
		return "result 200";
	}
	@RequestMapping(value="/save", method = {RequestMethod.POST})
	public String save(@RequestBody CallModel infoVO) {
		
		System.out.println("/call/save call success");
		return "result 200";
	}
}
