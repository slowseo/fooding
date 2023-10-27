package com.fooding.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author ITWILL
 *
 */

// 인터페이스는 자생력이 없다(객체 생성 불가능)
// = 추상메서드를 포함하고 있기 때문
// => 추상메서드는 body가 없어서 실행구문이 없음(실행불가능)
public interface Action {
	// 인터페이스가 객체를 생성하지 못하는 이유
	// : 인터페이스 내에서 사용할 수 있는거(상수,추상메서드). 추상메서드땜에.
//	public /*abstract*/void method(); //abstract 생략 가능
//	public abstract void method2();
	
	// 인터페이스 내에 있는 메서드는 모두 추상메서드이기 때문에 안붙여도 추상메서드로 인식한다.
	
	public ActionForward execute(HttpServletRequest request,
								HttpServletResponse response) throws Exception;
}
