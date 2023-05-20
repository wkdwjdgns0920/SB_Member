package com.KoreaIT.jjh.exam.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.KoreaIT.jjh.exam.vo.Member;

@Mapper
public interface MemberRepository {

	@Insert("""
			INSERT INTO `member`
			SET regDate = NOW(),
			updateDate = NOW(),
			email = #{email},
			loginPw = #{loginPw},
			`name` = #{name},
			nickname = #{nickname},
			cellphoneNum = #{cellphoneNum}
			""")
	public void join(String email, String loginPw, String name, String nickname, String cellphoneNum);

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

	@Select("""
			SELECT *
			FROM `member`
			WHERE id = #{id}
			""")
	public Member getMemberById(int id);
	
	
	@Update("""
			UPDATE `member`
			SET loginPw = #{loginPw},
			name = #{name},
			nickname = #{nickname},
			cellphoneNum = #{cellphoneNum}
			WHERE id = #{id}
			""")
	public void modifyMember(String loginPw,  String name, String nickname, String cellphoneNum,
			int id);

}
