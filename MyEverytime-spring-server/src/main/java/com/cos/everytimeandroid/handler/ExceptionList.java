package com.cos.everytimeandroid.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cos.everytimeandroid.domain.errorlog.ErrorLog;

import lombok.Data;

@Data
@Service
public class ExceptionList {
	public List<ErrorLog> exList = new ArrayList<>();

	public void addExceptionList(ErrorLog log) {
		exList.add(log);
	}
}
