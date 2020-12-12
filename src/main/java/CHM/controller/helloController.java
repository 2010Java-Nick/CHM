package CHM.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class helloController {

	@RequestMapping(path = "/hello", method = RequestMethod.GET)
	@ResponseBody
	public String hello() {
		
		System.out.println("Hit hello endpoint");

		return "hello!";
		
	}
	
}
