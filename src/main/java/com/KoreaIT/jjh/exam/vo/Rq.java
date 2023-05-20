package com.KoreaIT.jjh.exam.vo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.KoreaIT.jjh.exam.service.MemberService;

import lombok.Getter;

public class Rq {

	@Getter
	private boolean isLogined;
	@Getter
	private int loginedMemberId;
	@Getter
	private Member loginedMember;

	private HttpServletRequest req;
	private HttpServletResponse resp;
	private HttpSession session;
	
	public Rq(HttpServletRequest req) {
		boolean isLogined = false;
		int loginedMemberId = 0;
		Member loginedMember = null;
		this.req = req;
		this.session = req.getSession();
		
		if (session.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			loginedMemberId = (int) session.getAttribute("loginedMemberId");
		}
		
		this.isLogined = isLogined;
		this.loginedMemberId = loginedMemberId;
		this.loginedMember = loginedMember;
		
		this.req.setAttribute("rq", this);
	}

	public Rq(HttpServletRequest req, HttpServletResponse resp, MemberService memberService) {
		boolean isLogined = false;
		int loginedMemberId = 0;
		Member loginedMember = null;
		this.req = req;
		this.resp = resp;
		this.session = req.getSession();
		
		if (session.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			loginedMemberId = (int) session.getAttribute("loginedMemberId");
			loginedMember = memberService.getMemberById(loginedMemberId);
		}
		
		this.isLogined = isLogined;
		this.loginedMemberId = loginedMemberId;
		this.loginedMember = loginedMember;
		
		
		this.req.setAttribute("rq", this);
	}
	
	public String jsHistoryBackOnView(String msg) {
		
		if(msg == null) {
			msg = "";
		}
		
		req.setAttribute("msg", msg);
		
		
		return "usr/common/js";
	}
	
	public void doLogin(Member member) {
		session.setAttribute("loginedMemberId", member.getId());
	}
	
	public void logout() {
		session.removeAttribute("loginedMemberId");
	}
	
	public String getCurrentUri() {
		String currentUri = req.getRequestURI();
		String queryString = req.getQueryString();

		if (queryString != null && queryString.length() > 0) {
			currentUri += "?" + queryString;
		}
		
		return currentUri;
	}
}
