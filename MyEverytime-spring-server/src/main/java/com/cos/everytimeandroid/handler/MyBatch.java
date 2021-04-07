package com.cos.everytimeandroid.handler;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cos.everytimeandroid.domain.errorlog.ErrorLog;
import com.cos.everytimeandroid.domain.errorlog.ErrorLogRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class MyBatch {
	private final ExceptionList exceptionList;
	private final ErrorLogRepository errorLogRepository;

	@Scheduled(fixedDelay = 1000*60) // Cron 정기적 실행
	public void excute() {
		List<ErrorLog> exList = exceptionList.getExList();
		// DB에 insert 하기
		for(int i=0; i<exList.size(); i++) {
			errorLogRepository.save(exList.get(i));
			
		}
		exList.clear();
	}
}
