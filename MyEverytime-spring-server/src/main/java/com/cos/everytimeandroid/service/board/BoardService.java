package com.cos.everytimeandroid.service.board;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.everytimeandroid.domain.board.Board;
import com.cos.everytimeandroid.domain.board.BoardRepository;
import com.cos.everytimeandroid.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardService {

	private final BoardRepository boardRepository;
	
	public List<Board> 전체보기(){
		return boardRepository.findAll();
	}
	
	@Transactional
	public Board 글저장하기(Board board) {
		return boardRepository.save(board);		
	}

	public Board 글상세보기(Long id) {
		return boardRepository.findById(id).get();
	}
	
	@Transactional
	public void 글삭제하기(Long id) {
		boardRepository.deleteById(id);
		
	}
	
	@Transactional
	public Board 글수정하기(Long id, Board board) {
		// 1. 영속화		
		Board boardEntity = boardRepository.findById(id).get();
		
		// 2. 영속화 된 객체를 수정
		boardEntity.setTitle(board.getTitle());
		boardEntity.setContent(board.getContent());
		
		return boardEntity;
	}// 서비스 종료시에 영속성 컨텍스트가 변경을 감지해서 flush()로 DB에 반영함
	
	
}
