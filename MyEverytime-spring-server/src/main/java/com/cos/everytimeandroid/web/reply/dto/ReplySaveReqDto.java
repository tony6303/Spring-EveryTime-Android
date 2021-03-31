package com.cos.everytimeandroid.web.reply.dto;



import com.cos.everytimeandroid.domain.reply.Reply;

import lombok.Data;

@Data
public class ReplySaveReqDto {

	private String content;
	
	public Reply toEntity() {
		return Reply.builder()
			.content(content)
			.build();
	}
}
