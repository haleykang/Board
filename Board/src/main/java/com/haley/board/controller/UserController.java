package com.haley.board.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.haley.board.domain.UserVO;
import com.haley.board.service.UserService;

@Controller
@RequestMapping("/user/*") // user/로 시작하는 요청은 이곳에서 처리
public class UserController {

	@Autowired
	private UserService userService;

	// 1. 로그인 화면으로 이동하는 요청 처리
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String goLogin() {

		// 단순 페이지 이동
		return "user/login";

	}

	// 2. 로그인 요청 처리
	// 로그인 처리 할 때는 로그인 성공 결과를 HttpSession에 저장해야함 --> 중요!
	@RequestMapping(value = "loginPost", method = RequestMethod.POST)
	// public String login(UserVO vo, HttpSession session)
	public void login(UserVO vo, Model model) {
		// 1) 파라미터를 받은 것을 가지고 로그인을 시도해서 결과를 userVO에 저장
		// 로그인에 성공하면 userVO에 데이터가 저장되고 실패하면 null이 저장
		UserVO userVO = userService.login(vo);

		model.addAttribute("login", userVO);

		// Intercepter 적용을 위해 삭제
		/*
		 * // 2) 로그인 성공 실패 여부 // 2-1) userVO 값이 null이면 로그인 실패 if (userVO ==
		 * null) { // (1)로그인 실패 시 session 객체에 null 값을 저장
		 * session.setAttribute("login", null); // (2) 로그인 페이지로 다시 이동 return
		 * "redirect:login"; } // 2-2) userVO값이 null 값이 아닌 경우 로그인 성공 else { //
		 * (1) 로그인 성공 시 session 객체에 userVO 값을 저장 session.setAttribute("login",
		 * userVO); // (2) 메인 페이지로 이동 return "redirect:../"; // }
		 */
		// return 타입이 void이면 요청한 주소가 View이름이 됨
		// 여기서는 user/loginPost

	}

	// 3. 로그아웃 요청 처리
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		// 세션 초기화
		session.invalidate();
		// 시작 페이지로 이동
		return "redirect:/";
	}
}
