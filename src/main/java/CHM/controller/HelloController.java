package CHM.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@RequestMapping(path = "/hello", produces = "text/plain")
	public String hello() {
		
		System.out.println("Hit hello endpoint");

		return "hello!";
		
	}
	
}
