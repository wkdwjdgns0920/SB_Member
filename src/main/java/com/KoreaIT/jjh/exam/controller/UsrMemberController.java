package com.KoreaIT.jjh.exam.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.KoreaIT.jjh.exam.service.MemberService;
import com.KoreaIT.jjh.exam.util.Ut;
import com.KoreaIT.jjh.exam.vo.Member;
import com.KoreaIT.jjh.exam.vo.ResultData;
import com.KoreaIT.jjh.exam.vo.Rq;

@Controller
public class UsrMemberController {

	@Autowired
	MemberService memberService;

	@RequestMapping("usr/member/doModify")
	@ResponseBody
	public String doModify(HttpServletRequest req, int id, String loginPw, String loginPwConfirm, String name,
			String nickname, String cellphoneNum) {
		Rq rq = new Rq(req);

		if (rq.isLogined() == false) {
			return Ut.jsHistoryBack("F-1", "로그인후에 이용해주세요");
		}

		if (rq.getLoginedMemberId() != id) {
			return Ut.jsHistoryBack("F-2", "권한이 없습니다");
		}

		if (Ut.empty(loginPw)) {
			return Ut.jsHistoryBack("F-3", "변경할 비밀번호를 입력해주세요");
		}

		if (loginPw.equals(loginPwConfirm) == false) {
			return Ut.jsHistoryBack("F-4", "비밀번호가 일치하지 않습니다");
		}

		if (Ut.empty(name)) {
			return Ut.jsHistoryBack("F-5", "비밀번호확인을 입력해주세요");
		}

		if (Ut.empty(name)) {
			return Ut.jsHistoryBack("F-6", "변경할 이름을 입력해주세요");
		}
		if (Ut.empty(nickname)) {
			return Ut.jsHistoryBack("F-7", "변경할 닉네임을 입력해주세요");
		}
		if (Ut.empty(cellphoneNum)) {
			return Ut.jsHistoryBack("F-8", "변경할 전화번호를 입력해주세요");
		}

		memberService.modifyMember(loginPw, name, nickname, cellphoneNum, id);

		return Ut.jsReplace("회원정보수정", Ut.f("profile?id=%d", id));
	}

	@RequestMapping("usr/member/modify")
	public String modify(HttpServletRequest req, Model model, int id) {
		Rq rq = new Rq(req);

		if (rq.isLogined() == false) {
			return rq.jsHistoryBackOnView("로그인후에 이용해주세요");
		}

		if (rq.getLoginedMemberId() != id) {
			return rq.jsHistoryBackOnView("권한이 없습니다");
		}

		Member member = memberService.getMemberById(id);

		model.addAttribute("member", member);

		return "usr/member/modify";
	}

	@RequestMapping("usr/member/doCheckPw")
	@ResponseBody
	public String doCheckPw(HttpServletRequest req, Model model, int id, String loginPw) {
		Rq rq = new Rq(req);

		if (rq.isLogined() == false) {
			return Ut.jsHistoryBack("F-1", "로그인후에 이용해주세요");
		}

		if (rq.getLoginedMemberId() != id) {
			return Ut.jsHistoryBack("F-2", "권한이 없습니다");
		}

		Member member = memberService.getMemberById(id);

		if (member.getLoginPw().equals(loginPw) == false) {
			return Ut.jsHistoryBack("F-3", "비밀번호가 틀렸습니다");
		}

		return Ut.jsReplace("비밀번호확인 성공", Ut.f("../member/modify?id=%d", id));
	}

	@RequestMapping("usr/member/checkPw")
	public String checkPw(HttpServletRequest req, Model model, int id) {
		Rq rq = new Rq(req);

		if (rq.isLogined() == false) {
			return rq.jsHistoryBackOnView("로그인후에 이용해주세요");
		}

		if (rq.getLoginedMemberId() != id) {
			return rq.jsHistoryBackOnView("권한이 없습니다");
		}

		Member member = memberService.getMemberById(id);

		model.addAttribute("member", member);

		return "usr/member/checkPw";
	}

	@RequestMapping("usr/member/profile")
	public String showProfile(HttpServletRequest req, Model model, int id) {
		Rq rq = new Rq(req);

		if (rq.isLogined() == false) {
			return rq.jsHistoryBackOnView("로그인후에 이용해주세요");
		}

		if (rq.getLoginedMemberId() != id) {
			return rq.jsHistoryBackOnView("프로필권한이 없습니다");
		}

		Member member = memberService.getMemberById(rq.getLoginedMemberId());

		model.addAttribute("member", member);

		return "usr/member/profile";
	}

	@RequestMapping("usr/member/getEmailDup")
	@ResponseBody
	public ResultData getEmailDup(String email) {

		if (Ut.empty(email)) {
			return ResultData.from("F-1", "이메일을 입력해주세요");
		}

		Member existsMember = memberService.getMemberByEmail(email);

		if (Ut.isValidEmail(email) == false) {
			return ResultData.from("F-2", "이메일형식을 확인해주세요");
		}

		if (existsMember != null) {
			return ResultData.from("F-3", "사용중인 이메일", "email", email);
		}

		return ResultData.from("S-1", "사용가능한 이메일", "email", email);
	}

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
	public String join(HttpServletRequest req) {
		Rq rq = new Rq(req);

		if (rq.isLogined() == true) {
			return rq.jsHistoryBackOnView("로그인상태입니다");
		}

		return "usr/member/join";
	}

	@RequestMapping("usr/member/doJoin")
	@ResponseBody
	public String doJoin(HttpServletRequest req, String email, String loginPw, String loginPwConfirm, String name,
			String nickname, String cellphoneNum) {

		Rq rq = new Rq(req);

		if (rq.isLogined() == true) {
			return rq.jsHistoryBackOnView("로그인상태입니다");
		}

		if (Ut.empty(email)) {
			return Ut.jsHistoryBack("F-9", "이메일을 입력해주세요");
		}
		if (Ut.isValidEmail(email) == false) {
			return Ut.jsHistoryBack("F-11", "이메일형식을 확인해주세요");
		}
		if (Ut.empty(loginPw)) {
			return Ut.jsHistoryBack("F-3", "비밀번호를 입력해주세요");
		}
		if (Ut.validationPasswd(loginPw)) {
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

		ResultData joinRd = memberService.join(email, loginPw, name, nickname, cellphoneNum);

		if (joinRd.isFail()) {
			return Ut.jsHistoryBack(joinRd.getResultCode(), joinRd.getMsg());
		}

		return Ut.jsReplace(joinRd.getMsg(), "login");
	}

	@RequestMapping("usr/member/doLogout")
	@ResponseBody
	public String doLogout(HttpServletRequest req) {
		Rq rq = new Rq(req);

		if (rq.isLogined() == false) {
			return Ut.jsHistoryBack("F-1", "로그아웃상태입니다");
		}

		rq.logout();

		return Ut.jsReplace("로그아웃", "login");
	}

	@RequestMapping("/usr/member/doLogin")
	@ResponseBody
	public String doLogin(HttpServletRequest req, String email, String loginPw) {

		Rq rq = new Rq(req);

		if (rq.isLogined() == true) {
			return Ut.jsHistoryBack("F-1", "로그인상태입니다");
		}

		if (Ut.empty(email)) {
			return Ut.jsHistoryBack("F-2", "아이디를 입력해주세요");
		}
		if (Ut.empty(loginPw)) {
			return Ut.jsHistoryBack("F-3", "비밀번호를 입력해주세요");
		}

		Member member = memberService.getMemberByEmail(email);

		if (member == null) {
			return Ut.jsHistoryBack("F-4", "존재하지 않는 이메일입니다");
		}

		if (member.getLoginPw().equals(loginPw) == false) {
			return Ut.jsHistoryBack("F-4", "비밀번호가 일치하지 않습니다");
		}

		rq.doLogin(member);

		return Ut.jsReplace("로그인", "/");
	}

	@RequestMapping("usr/member/login")
	public String login(HttpServletRequest req) {
		Rq rq = new Rq(req);

		if (rq.isLogined() == true) {
			return rq.jsHistoryBackOnView("로그인상태입니다");
		}

		return "usr/member/login";
	}

}
