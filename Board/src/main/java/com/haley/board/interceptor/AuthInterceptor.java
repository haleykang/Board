package com.haley.board.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class AuthInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	// Controller에서 요청 처리 후 뷰 페이지로 이동하기 전 호출되는 메소드
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	// Controller에서 요청을 처리하기 전에 호출되는 메소드
	// false - Controller 처리 안함
	// true - Controller 처리 수행
	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		// 글쓰기 이동 전에 로그인 여부를 확인해야하기 때문에 preHandle에서 처리
		// 1) 세션 가져오기 - 리퀘스트로 세션을 만든다는 것을 꼭 기억할 것 !!
		// -> 세션에 저장하는 이유! request 객체는 포워딩 방식에서만 값을 전달
		// -> 우리는 리다이렉트로 이동하기 때문에 session 객체로 값을 저장해야함
		HttpSession session = arg0.getSession();
		// 2) 로그인 여부 확인
		// 2-1) 로그인 안되어있을 때
		if (session.getAttribute("login") == null) {
			// (1)로그인 페이지로 이동하기 전에 요청했던 경로를 세션에 저장
			String uri = arg0.getRequestURI(); // 클라이언트의 요청 경로를 가져옴

			// (2) 파라미터 문자열 가져오기
			String query = arg0.getQueryString(); // 쿼리스트링 가져오기
			// (3) query가 없으면 query를 ""으로 설정, 그렇지 않으면 ? 형태로 붙이기
			if (query == null || query.equals("null")) {
				query = "";
			} else {
				query = "?" + query;
			}
			// (4) 세션에 uri와 query를 저장 - dest 이름으로 저장하기
			session.setAttribute("dest", uri + query);

			// (2)로그인 페이지로 리다이렉트
			arg1.sendRedirect("/board/user/login");
			// (3)요청으로 이동 안함
			return false;
		}
		// 2-2) 로그인 되어있을 때 - 원래의 요청을 처리하도록
		return true;
	}

}
