package com.cos.everytimeandroid.handler;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.everytimeandroid.domain.errorlog.ErrorLog;
import com.cos.everytimeandroid.web.dto.CMRespDto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Configuration
@RequiredArgsConstructor
@RestController
@ControllerAdvice
public class MyExceptionHandler {
	private final ExceptionList exceptionList;

	@ExceptionHandler(value = Exception.class)
	public CMRespDto<?> saveException(Exception e) {
		new ErrorLog();
		ErrorLog log = ErrorLog.builder()
				.log(e.getMessage())
				.build();
		exceptionList.addExceptionList(log);
		System.out.println("=================");
		System.out.println("@@@@@에러발생@@@@@");
		System.out.println("=================");
		System.out.println(log);
		
		return new CMRespDto<>(-1, "오류남");
		
	}
}
