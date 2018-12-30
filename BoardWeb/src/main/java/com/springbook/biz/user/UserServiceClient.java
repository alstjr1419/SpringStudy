package com.springbook.biz.user;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class UserServiceClient {
	public static void main(String[] args) {
		//1.Spring 컨테이너 구동
		AbstractApplicationContext container = new GenericXmlApplicationContext("applicationContext.xml");
		
		//2.Spring 컨테이너로부터 UserServiceImpl 객체 lookup
		UserService userServie = (UserService)container.getBean("userService");
		
		//3.로그인 기능 테스트
		UserVO vo = new UserVO();
		vo.setId("test");
		vo.setPassword("test1234");
		
		UserVO user = userServie.getUser(vo);
		if(user != null) {
			System.out.println(user.getName() + " 님 환영합니다.");
		}else {
			System.out.println("로그인 실패");
		}
		
		//4.Spring컨테이너를 종료한다.
		container.close();
	}
}
