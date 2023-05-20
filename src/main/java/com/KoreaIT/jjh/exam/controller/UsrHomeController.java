package com.KoreaIT.jjh.exam.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.KoreaIT.jjh.exam.vo.Rq;

@Controller
public class UsrHomeController {
	
	@RequestMapping("/usr/home/main")
	public String showMain(HttpServletRequest req) {
		Rq rq = new Rq(req);
		
		return "usr/home/main";
	}
	
	@RequestMapping("/")
	public String showRoot() {
		return "redirect:/usr/home/main";
	}
}
