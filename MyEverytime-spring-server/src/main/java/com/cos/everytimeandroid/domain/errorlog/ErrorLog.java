package com.cos.everytimeandroid.domain.errorlog;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity // persistence
public class ErrorLog {
	@Id //pk
	@GeneratedValue(strategy = GenerationType.IDENTITY) // autoIncrease
	private Long id;
	
	private String log;
	
	@CreationTimestamp
	private Timestamp createDate;

}
