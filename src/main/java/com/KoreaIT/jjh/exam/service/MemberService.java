package com.KoreaIT.jjh.exam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.KoreaIT.jjh.exam.repository.MemberRepository;
import com.KoreaIT.jjh.exam.vo.Member;
import com.KoreaIT.jjh.exam.vo.ResultData;

@Service
public class MemberService {

	@Autowired
	MemberRepository memberRepository;

	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	public ResultData join(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email) {
		Member member = getMemberByLoginId(loginId);
		
		if(member != null) {
			return ResultData.from("F-A", "이미 사용중인 아이디입니다");
		}
		
		member = getMemberByEmail(email);
		
		if(member != null) {
			return ResultData.from("F-B", "이미 사용중인 이메일 입니다");
		}
		
		memberRepository.join(loginId, loginPw, name, nickname, cellphoneNum, email);
		
		return ResultData.from("S-1", "회원가입");
	}

	private Member getMemberByEmail(String email) {
		
		return memberRepository.getMemberByEmail(email);
	}

	public Member getMemberByLoginId(String loginId) {
		
		return memberRepository.getMemberByLoginId(loginId);
	}

}
