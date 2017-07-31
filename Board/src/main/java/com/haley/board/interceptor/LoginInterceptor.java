package com.haley.board.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor {

	// Controller가 요청 처리 후 뷰를 리턴 한 후 호출되는 메소드
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	// 2. Controller가 요청 처리 메소드를 실행 한 후 뷰를 리턴하기 전 호출되는 메소드
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {

		// 1) 로그인 성공 여부를 판단하기 위해 model에 저장한 데이터를 가져오기
		// -> 우리가 loginPost 요청을 통해서 model에 "login" userVO 정보를 저장했었음
		ModelMap modelMap = arg3.getModelMap();
		Object login = modelMap.get("login");
		if (login == null) {
			// login == null --> 로그인 실패
			arg1.sendRedirect(""); // 로그인 실패 시 이동할 요청 지정
		} else {
			// login != null --> 로그인 성공
			// Session에 로그인 정보 저장하고 다른 페이지로 리다이렉트
			HttpSession session = arg0.getSession();
			session.setAttribute("login", login);
			
			// 세션에서 "dest" 키 값에 있는 정보를 가져옴
			String dest = (String)session.getAttribute("dest");
			if (dest == null) {
				// dest -> 목적지 정보가 없으면 메인으로 이동
				// dest 가 null 이다 -> 로그인 요청으로 온 것 
				arg1.sendRedirect("/board");
			} else {
				// dest -> 목적지 정보가 있으면 그 페이지로 이동
				// 예를 들어, 글쓰기 요청을 눌렀을 때 로그인이 안되어있어서 로그인 페이지로 이동 했다면
				// 로그인을 한 후 메인 페이지가 아닌 글쓰기 페이지로 바로 이동하도록 구현
				arg1.sendRedirect(dest);
			}
			// arg1.sendRedirect("/board"); // 로그인 성공 시 이동할 요청 지정 - 내 프로젝트 주소로

		}

	}

	// 1. Controller로 가기전에 호출되는 메소드
	// true 리턴 - Controller에 가서 나머지 처리 수행
	// false 리턴 - Controller로 이동 안함
	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		// 로그인 처리 전에 기존 로그인 정보가 있으면 삭제
		// -> 즉, 로그인하기 전에 이미 로그인 된 정보가 있으면 삭제
		HttpSession session = arg0.getSession();
		if (session.getAttribute("login") != null) {
			session.removeAttribute("login");
		}
		return true;
	}

}
