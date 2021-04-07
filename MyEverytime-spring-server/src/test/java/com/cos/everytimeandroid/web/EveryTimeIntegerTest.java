package com.cos.everytimeandroid.web;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;

import com.cos.everytimeandroid.domain.user.User;
import com.cos.everytimeandroid.domain.user.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class EveryTimeIntegerTest {
	
	@Autowired
	private MockMvc mockMvc;

	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EntityManager entityManager;

	
	
	@Test
	public void 회원가입테스트() throws Exception {
		// given : 테스트 시작 전, 테스트용 데이터를 만드는 단계
		User user = new User().builder()
				.username("testUsername")
				.password("testPassword")
				.email("test@test.com")
				.university("testUniv")
				.entranceYear(2021)
				.build();
		
		// 자바 객체 (user) 를 Json으로 매핑해줌
		String content = new ObjectMapper().writeValueAsString(user);
		
		// when 을 수행하면 thenReturn 을 돌려받아야 한다.
		// 결과값은 개발자가 스스로 예측
	
		
		// when 실행 ( 주소 요청 )
		ResultActions resultActions = mockMvc.perform(post("/user")
				.contentType(MediaType.APPLICATION_JSON_UTF8) // 요청하는 데이터 타입
				.content(content) // 실제 던지는 데이터
				.accept(MediaType.APPLICATION_JSON_UTF8)); // 응답받을 데이터 타입
		
		resultActions
		.andExpect(jsonPath("$.code")
				.value("100")) // 기대한 CMRespDto의 code값
		.andDo(MockMvcResultHandlers.print()); // 결과를 콘솔에 보여준다.
		
	}


}
