package com.cos.everytimeandroid.web.board;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.everytimeandroid.domain.board.Board;
import com.cos.everytimeandroid.service.board.BoardService;
import com.cos.everytimeandroid.web.dto.CMRespDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class BoardController {

	private final BoardService boardService;
	
	@GetMapping("/board")
	public CMRespDto<?> findAll() {
		return new CMRespDto<>(100, boardService.전체보기());
		// 안드로이드에서 요청했을 때 1이 안나온다? -> 무조건 오류
	}
	
	@PostMapping("/board")
	public CMRespDto<?> save(@RequestBody Board board){
		return new CMRespDto<>(100, boardService.글저장하기(board));
	}
	
	@GetMapping("/board/{id}")
	public CMRespDto<?> findById(@PathVariable Long id){
		return new CMRespDto<>(100, boardService.글상세보기(id));
	}
	
	@DeleteMapping("/board/{id}")
	public CMRespDto<?> deleteById(@PathVariable Long id){
		boardService.글삭제하기(id);
		return null;
	}
	
	@PutMapping("/board/{id}")
	public CMRespDto<?> updateById(@PathVariable Long id, @RequestBody Board board){
		return new CMRespDto<>(100, boardService.글수정하기(id, board));
	}
}
