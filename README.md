# Spring-EveryTime-Android
``` yml
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
  
두 번째 커밋  
글 전체 조회 ( getFreeBoard )  
글 한 개 조회 ( getOneFreeBoard )  
글 저장 ( saveFreeBoard )  
글 삭제 ( deleteOneFreeBoard )  

