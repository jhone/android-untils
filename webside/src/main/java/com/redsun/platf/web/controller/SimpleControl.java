package com.redsun.platf.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/views")
public class SimpleControl {
	
     public SimpleControl(){
    	 System.out.println("views inited");
     }
     //localhost:8080/webside/views/simple
	@RequestMapping(value="/simple", method=RequestMethod.DELETE )
	public @ResponseBody String simple() {
		return "Hello world!";
	}
	 //localhost:8080/webside/views/html
	//list
	@RequestMapping(value="/html", method=RequestMethod.GET)
	public String prepare(Model model) {
		model.addAttribute("foo", "bar");
		model.addAttribute("fruit", "apple");
		return "views/html";
	}
	
	@RequestMapping(value="/viewName", method=RequestMethod.GET)
	public void usingRequestToViewNameTranslator(Model model) {
		model.addAttribute("foo", "bar");
		model.addAttribute("fruit", "apple");
	}


	

}
