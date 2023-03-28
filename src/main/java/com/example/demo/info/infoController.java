package com.example.demo.info;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/call")
@RestController
public class infoController {

	@GetMapping("/test")
	public String test() {
		return "result 200";
	}
}
