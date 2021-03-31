package com.cos.everytimeandroid.service.reply;

import org.springframework.stereotype.Service;

import com.cos.everytimeandroid.domain.board.BoardRepository;
import com.cos.everytimeandroid.domain.reply.Reply;
import com.cos.everytimeandroid.domain.reply.ReplyRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReplyService {
	private ReplyRepository replyRepository;
	private BoardRepository boardRepository;
	
	public Reply 댓글저장하기(Long id, Reply reply) {
		reply.setBoard(boardRepository.findById(id).get()); // ajax url에 있는 boardId로 board 를 찾음
		
		return null;
	}
}
