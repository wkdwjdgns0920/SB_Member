package com.KoreaIT.jjh.exam.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.KoreaIT.jjh.exam.service.MemberService;
import com.KoreaIT.jjh.exam.util.Ut;
import com.KoreaIT.jjh.exam.vo.Member;
import com.KoreaIT.jjh.exam.vo.ResultData;

@Controller
public class UsrMemberController {

	@Autowired
	MemberService memberService;
	
	

	@RequestMapping("usr/member/getLoginIdDup")
	@ResponseBody
	public ResultData getLoginIdDup(String loginId) {

		if (Ut.empty(loginId)) {

			return ResultData.from("F-1", "아이디를 입력해주세요");
		}

		Member existsMember = memberService.getMemberByLoginId(loginId);

		if (existsMember != null) {
			return ResultData.from("F-2", "사용중인 아이디", "loginId", loginId);
		}

		return ResultData.from("S-1", "사용가능한 아이디", "loginId", loginId);
	}

	@RequestMapping("usr/member/join")
	public String join(HttpSession httpsession) {
		
		boolean isLogined = false;

		if (httpsession.getAttribute("loginedMemberId") != null) {
			isLogined = true;
		}
		

		if(isLogined) {
			return "redirect:/usr/common/js";
		}

		return "usr/member/join";
	}

	@RequestMapping("usr/member/doJoin")
	@ResponseBody
	public String doJoin(HttpSession httpsession, String loginId, String loginPw, String loginPwConfirm, String name, String nickname,
			String cellphoneNum, String email) {
		
		boolean isLogined = false;

		if (httpsession.getAttribute("loginedMemberId") != null) {
			isLogined = true;
		}

		if (isLogined) {
			return Ut.jsHistoryBack("F-1", "이미 로그인 상태입니다");
		}
		
		if (Ut.empty(loginId)) {
			return Ut.jsHistoryBack("F-2", "아이디를 입력해주세요");
		}
		if (Ut.empty(loginPw)) {
			return Ut.jsHistoryBack("F-3", "비밀번호를 입력해주세요");
		}
		if(Ut.validationPasswd(loginPw)) {
			return Ut.jsHistoryBack("F-10", "최소 8자리에 숫자, 문자, 특수문자 사용해주세요");
		}
		if (Ut.empty(loginPwConfirm)) {
			return Ut.jsHistoryBack("F-4", "비밀번호확인을 입력해주세요");
		}
		if (loginPw.equals(loginPwConfirm) == false) {
			return Ut.jsHistoryBack("F-5", "비밀번호가 일치하지 않습니다");
		}
		if (Ut.empty(name)) {
			return Ut.jsHistoryBack("F-6", "이름을 입력해주세요");
		}
		if (Ut.empty(nickname)) {
			return Ut.jsHistoryBack("F-7", "닉네임을 입력해주세요");
		}
		if (Ut.empty(cellphoneNum)) {
			return Ut.jsHistoryBack("F-8", "전화번호를 입력해주세요");
		}
		if (Ut.empty(email)) {
			return Ut.jsHistoryBack("F-9", "이메일을 입력해주세요");
		}
		if(Ut.isValidEmail(email) == false) {
			return Ut.jsHistoryBack("F-11", "이메일형식을 확인해주세요");
		}

		ResultData joinRd = memberService.join(loginId, loginPw, name, nickname, cellphoneNum, email);

		if (joinRd.isFail()) {
			return Ut.jsHistoryBack(joinRd.getResultCode(), joinRd.getMsg());
		}

		return Ut.jsReplace(joinRd.getMsg(), "login");
	}
	
	@RequestMapping("usr/member/doLogout")
	@ResponseBody
	public String doLogout(HttpSession httpsession) {
		boolean isLogined = true;
		
		if (httpsession.getAttribute("loginedMemberId") == null) {
			isLogined = false;
		}
		
		if (isLogined == false) {
			return Ut.jsHistoryBack("F-1", "로그아웃 상태입니다");
		}
		
		httpsession.removeAttribute("loginedMemberId");
		
		return Ut.jsReplace("로그아웃", "login");
	}

	@RequestMapping("/usr/member/doLogin")
	@ResponseBody
	public String doLogin(HttpSession httpsession, String loginId, String loginPw) {

		boolean isLogined = false;

		if (httpsession.getAttribute("loginedMemberId") != null) {
			isLogined = true;
		}

		if (isLogined) {
			return Ut.jsHistoryBack("F-1", "이미 로그인 상태입니다");
		}

		if (Ut.empty(loginId)) {
			return Ut.jsHistoryBack("F-2", "아이디를 입력해주세요");
		}
		if (Ut.empty(loginPw)) {
			return Ut.jsHistoryBack("F-3", "비밀번호를 입력해주세요");
		}

		Member member = memberService.getMemberByLoginId(loginId);

		if (member == null) {
			return Ut.jsHistoryBack("F-4", "존재하지 않는 아이디입니다");
		}

		if (member.getLoginPw().equals(loginPw) == false) {
			return Ut.jsHistoryBack("F-4", "비밀번호가 일치하지 않습니다");
		}

		httpsession.setAttribute("loginedMemberId", member.getId());

		return Ut.jsReplace("로그인", "/");
	}

	@RequestMapping("usr/member/login")
	public String login(HttpSession httpsession,Model model ,String loginId, String loginPw) {
		boolean isLogined = false;

		if (httpsession.getAttribute("loginedMemberId") != null) {
			isLogined = true;
		}
		

		if(isLogined) {
			return "redirect:/usr/common/js";
		}
		
		return "usr/member/login";
	}
	
	@RequestMapping("usr/common/js")
	public String js(HttpSession httpsession) {
		
		return "usr/common/js";
	}

}
