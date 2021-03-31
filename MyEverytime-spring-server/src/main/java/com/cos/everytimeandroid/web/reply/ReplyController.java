package com.cos.everytimeandroid.web.reply;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.everytimeandroid.service.reply.ReplyService;
import com.cos.everytimeandroid.web.dto.CMRespDto;
import com.cos.everytimeandroid.web.reply.dto.ReplySaveReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ReplyController {

	private final ReplyService replyService;
	
	@PostMapping("/reply/{boardId}")
	public CMRespDto<?> saveReply(@PathVariable int boardId,@RequestBody ReplySaveReqDto replySaveReqDto){
		System.out.println(boardId);
		System.out.println(replySaveReqDto);
		
		return new CMRespDto<>(100, null);
	}
}
