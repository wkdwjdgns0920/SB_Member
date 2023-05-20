package com.KoreaIT.jjh.exam.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.KoreaIT.jjh.exam.repository.ReplyRepository;
import com.KoreaIT.jjh.exam.vo.Reply;
import com.KoreaIT.jjh.exam.vo.ResultData;

@Service
public class ReplyService {

	private ReplyRepository replyRepository;

	public ReplyService(ReplyRepository replyRepository) {
		this.replyRepository = replyRepository;
	}

	public void writeReply(int actorId, int relId, String body) {
		replyRepository.writeReply(actorId, relId, body);
	}

	public List<Reply> getForPrintReplies(int relId, int itemInAPage, int page) {
				
		int limitFrom = (page - 1) * itemInAPage;
		int limit = itemInAPage;
		
		
		return replyRepository.getForPrintReplies(relId, limitFrom, limit);
	}

	public int getRepliesCount(int relId) {
		
		return replyRepository.getRepliesCount(relId);
	}

}
