package com.KoreaIT.jjh.exam.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.KoreaIT.jjh.exam.vo.Member;

@Mapper
public interface MemberRepository {
	
	
	@Insert("""
			INSERT INTO `member`
			SET regDate = NOW(),
			updateDate = NOW(),
			loginId = #{loginId},
			loginPw = #{loginPw},
			`name` = #{name},
			nickname = #{nickname},
			cellphoneNum = #{cellphoneNum},
			email = #{email}
			""")
	public void join(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email);
	
	
	@Select("""
			SELECT *
			FROM `member`
			WHERE loginId = #{loginId}
			""")
	public Member getMemberByLoginId(String loginId);

	
	@Select("""
			SELECT *
			FROM `member`
			WHERE email = #{email}
			""")
	public Member getMemberByEmail(String email);

}
