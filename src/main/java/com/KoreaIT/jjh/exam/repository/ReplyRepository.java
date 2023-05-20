package com.KoreaIT.jjh.exam.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.KoreaIT.jjh.exam.vo.Reply;

@Mapper
public interface ReplyRepository {
	
	
	@Insert("""
			INSERT INTO reply
			SET regDate = NOW(),
			updateDate = NOW(),
			memberId = #{actorId},
			relId =#{relId},
			`body`= #{body}
			""")
	void writeReply(int actorId, int relId, String body);
	
	
	@Select("""
			SELECT r.*, m.name AS extra__writer
			FROM `reply` r
			INNER JOIN `member` m
			ON r.memberId = m.id
			WHERE relId = #{relId}
			ORDER BY r.id ASC
			LIMIT #{limitFrom}, #{limit}
			""")
	List<Reply> getForPrintReplies(int relId, int limitFrom, int limit);

	
	@Select("""
			SELECT COUNT(*)
			FROM reply
			WHERE relId = #{relId}
			""")
	int getRepliesCount(int relId);
	
	
	
}
