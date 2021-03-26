# Spring-EveryTime-Android

### 시연영상  
https://youtu.be/AfCitIk8Dag

### DB Setting
```
create user 'everytime'@'%' identified by 'bitc5600';
GRANT ALL PRIVILEGES ON *.* TO 'everytime'@'%';
create database everytime;
```
``` 
server:
  port: 8000
  servlet:
    context-path: /
    
spring:
      
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/everytime?serverTimezone=Asia/Seoul
    username: everytime
    password: bitc5600
```


구현된 기능  
회원가입 ( SignUp )  
로그인 ( SignIn ) 
로그인정보 기억하기 ( SharedPreference )  
  
두 번째 커밋 ( retrofit Call 함수 이름 )  
글 전체 조회 ( getFreeBoard )  
글 한 개 조회 ( getOneFreeBoard )  
글 저장 ( saveFreeBoard )  
글 삭제 ( deleteOneFreeBoard )  
글 수정 ( updateFreeBoard )

