package com.KoreaIT.jjh.exam.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.KoreaIT.jjh.exam.service.ArticleService;
import com.KoreaIT.jjh.exam.service.ReplyService;
import com.KoreaIT.jjh.exam.util.Ut;
import com.KoreaIT.jjh.exam.vo.Article;
import com.KoreaIT.jjh.exam.vo.Reply;
import com.KoreaIT.jjh.exam.vo.Rq;

@Controller
public class UsrArticleController {

	@Autowired
	ArticleService articleService;
	@Autowired
	ReplyService replyService;

	@RequestMapping("/usr/article/detail")
	public String detail(Model model, HttpServletRequest req, int id, @RequestParam(defaultValue = "1") int page) {
		Rq rq = new Rq(req);

		Article article = articleService.getForPrintArticle(id);
		
		if(article == null) {
			return rq.jsHistoryBackOnView("게시글이 존재하지 않습니다");
		}

		int repliesCount = replyService.getRepliesCount(id);

		int itemInAPage = 5;
		int pagesCount = (int) Math.ceil(repliesCount / (double) itemInAPage);

		List<Reply> replies = replyService.getForPrintReplies(id, itemInAPage, page);

		model.addAttribute("article", article);
		model.addAttribute("replies", replies);
		model.addAttribute("itemInAPage", itemInAPage);
		model.addAttribute("pagesCount", pagesCount);

		return "usr/article/detail";
	}

	@RequestMapping("usr/article/list")
	public String list(Model model, @RequestParam(defaultValue = "1") int page) {

		int articlesCount = articleService.getArticlesCount();

		int itemInAPage = 5;
		int pagesCount = (int) Math.ceil(articlesCount / (double) itemInAPage);

		List<Article> articles = articleService.getForPrintArticles(itemInAPage, page);

		model.addAttribute("page", page);
		model.addAttribute("itemInAPage", itemInAPage);
		model.addAttribute("pagesCount", pagesCount);
		model.addAttribute("articlesCount", articlesCount);
		model.addAttribute("articles", articles);

		return "usr/article/list";
	}

	@RequestMapping("usr/article/doWrite")
	@ResponseBody
	public String doWrite(HttpServletRequest req, String title, String body) {

		Rq rq = new Rq(req);

		if (rq.isLogined() == false) {
			return Ut.jsHistoryBack("F-1", "로그인 후에 이용해주세요");
		}

		if (Ut.empty(title)) {
			return Ut.jsHistoryBack("F-1", "제목을 입력해주세요");
		}
		if (Ut.empty(body)) {
			return Ut.jsHistoryBack("F-2", "내용을 입력해주세요");
		}

		articleService.writeArticle(rq.getLoginedMemberId(), title, body);

		int id = articleService.getLastInsertId();

		return Ut.jsReplace("S-1", Ut.f("detail?id=%d", id));
	}

	@RequestMapping("usr/article/write")
	public String write(HttpServletRequest req) {

		Rq rq = new Rq(req);
		if (rq.isLogined() == false) {
			return rq.jsHistoryBackOnView("로그인 후에 이용해주세요");
		}

		return "usr/article/write";
	}

}
