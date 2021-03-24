package com.cos.everytimeandroid.domain.board;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import com.cos.everytimeandroid.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity // persistence
public class Board {
	
	@Id //pk
	@GeneratedValue(strategy = GenerationType.IDENTITY) // autoIncrease
	private Long id;
	
	private String title;
    private String content;
     
    @CreationTimestamp
    private Timestamp time; 
    
//    @ManyToOne
//    @JoinColumn(name = "userId")
//    private User user;
     
    private String writer = "익명"; 
    
    @ColumnDefault("0")
    private int like_num;
    
    @ColumnDefault("0")
    private int comment_num;

    
}
