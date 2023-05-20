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

	public ResultData join(String email, String loginPw, String name, String nickname, String cellphoneNum) {
		Member member = getMemberByEmail(email);

		if (member != null) {
			return ResultData.from("F-A", "이미 사용중인 이메일입니다");
		}

		memberRepository.join(email, loginPw, name, nickname, cellphoneNum);

		return ResultData.from("S-1", "회원가입");
	}

	public Member getMemberByEmail(String email) {

		return memberRepository.getMemberByEmail(email);
	}

	public Member getMemberByLoginId(String loginId) {

		return memberRepository.getMemberByLoginId(loginId);
	}

	public Member getMemberById(int id) {
		return memberRepository.getMemberById(id);
	}

	public void modifyMember(String loginPw, String name, String nickname, String cellphoneNum,
			int id) {
		memberRepository.modifyMember(loginPw, name, nickname, cellphoneNum, id);

	}
}
