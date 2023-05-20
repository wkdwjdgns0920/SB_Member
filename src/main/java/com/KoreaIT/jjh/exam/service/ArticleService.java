package com.KoreaIT.jjh.exam.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.KoreaIT.jjh.exam.repository.ArticleRepository;
import com.KoreaIT.jjh.exam.vo.Article;
import com.KoreaIT.jjh.exam.vo.ResultData;

@Service
public class ArticleService {

	private ArticleRepository articleRepository;

	public ArticleService(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
	}

	public void writeArticle(int actorId, String title, String body) {
		articleRepository.writeArticle(actorId, title, body);
	}

	public int getLastInsertId() {
		return articleRepository.getLastInsertId();
	}

	public int getArticlesCount() {

		return articleRepository.getArticlesCount();
	}

	public List<Article> getForPrintArticles(int itemInAPage, int page) {
		int limitFrom = (page - 1) * itemInAPage;
		int limit = itemInAPage;

		return articleRepository.getForPrintArticles(limit,limitFrom);
	}

	public Article getForPrintArticle(int id) {
		return articleRepository.getForPrintArticle(id);
	}

}
