package com.KoreaIT.jjh.exam.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.KoreaIT.jjh.exam.vo.Article;

@Mapper
public interface ArticleRepository {
	
	
	@Insert("""
			INSERT INTO article
			SET regDate = NOW(),
			updateDate = NOW(),
			memberId = #{actorId},
			title =#{title},
			`body`= #{body}
			""")
	public void writeArticle(int actorId, String title, String body);
	
	
	@Select("""
			SELECT LAST_INSERT_ID()
			""")
	public int getLastInsertId();

	
	@Select("""
			SELECT COUNT(*)
			FROM article
			""")
	public int getArticlesCount();
	
	
	@Select("""
			SELECT a.*, m.name AS extra__writer
			FROM article a
			INNER JOIN `member` m
			ON a.memberId = m.id
			ORDER BY a.id DESC
			LIMIT #{limitFrom}, #{limit}
			""")
	public List<Article> getForPrintArticles(int limit, int limitFrom);

	@Select("""
			SELECT a.*, m.name AS extra__writer
			FROM article a
			INNER JOIN `member` m
			ON m.id = a.memberId
			WHERE a.id = #{id}
			""")
	public Article getForPrintArticle(int id);

	
}
