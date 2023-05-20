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
import com.KoreaIT.jjh.exam.vo.ResultData;
import com.KoreaIT.jjh.exam.vo.Rq;

@Controller
public class UsrReplyController {

	@Autowired
	ReplyService replyService;
	
	@RequestMapping("usr/reply/doWrite")
	@ResponseBody
	public String doWrite(HttpServletRequest req, int relId, String body, @RequestParam(defaultValue = "/") String replaceUri) {
		Rq rq = new Rq(req);
		
		if (rq.isLogined() == false) {
			return Ut.jsHistoryBack("F-1", "로그인 후에 이용해주세요");
		}
		
		if (Ut.empty(body)) {
			return Ut.jsHistoryBack("F-2", "댓글을 입력해주세요");
		}
		
		replyService.writeReply(rq.getLoginedMemberId(),relId, body);
		
		return Ut.jsReplace("댓글작성", Ut.f("../article/detail?id=%d", relId));
	}

}
