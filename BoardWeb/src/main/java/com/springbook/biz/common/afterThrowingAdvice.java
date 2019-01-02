package com.springbook.biz.common;

import org.aspectj.lang.JoinPoint;

public class afterThrowingAdvice {
	public void exceptionLog(JoinPoint jp, Exception exceptObj) {
		String method = jp.getSignature().getName();
		System.out.println(method + "() 수행중 예외 발생!");
		if(exceptObj instanceof IllegalArgumentException) {
			System.out.println("부적합한 값이 입력되었습니다.");
		} else if(exceptObj instanceof NumberFormatException) {
			System.out.println("숫자형식의 값이 아닙니다.");
		} else if(exceptObj instanceof Exception) {
			System.out.println("문제가 발생했습니다.");
		}
		
		System.out.println("[예외 처리] " + method +"() 메소드 수행 중 발생된 예외 메세지 : " + exceptObj.getMessage());
	}
}
