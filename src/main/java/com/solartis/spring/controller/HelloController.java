package com.solartis.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

	@RequestMapping("/hello")
	public ModelAndView mymethod(){
		return new ModelAndView("hellopage","msg","Hello First Spring");
	}
	
	@RequestMapping("/welcome")
	public ModelAndView mymethod1(){
		return new ModelAndView("welcomepage");
	}
}
