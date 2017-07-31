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
@RequestMapping("/user/*") // user/�� �����ϴ� ��û�� �̰����� ó��
public class UserController {

	@Autowired
	private UserService userService;

	// 1. �α��� ȭ������ �̵��ϴ� ��û ó��
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String goLogin() {

		// �ܼ� ������ �̵�
		return "user/login";

	}

	// 2. �α��� ��û ó��
	// �α��� ó�� �� ���� �α��� ���� ����� HttpSession�� �����ؾ��� --> �߿�!
	@RequestMapping(value = "loginPost", method = RequestMethod.POST)
	// public String login(UserVO vo, HttpSession session)
	public void login(UserVO vo, Model model) {
		// 1) �Ķ���͸� ���� ���� ������ �α����� �õ��ؼ� ����� userVO�� ����
		// �α��ο� �����ϸ� userVO�� �����Ͱ� ����ǰ� �����ϸ� null�� ����
		UserVO userVO = userService.login(vo);

		model.addAttribute("login", userVO);

		// Intercepter ������ ���� ����
		/*
		 * // 2) �α��� ���� ���� ���� // 2-1) userVO ���� null�̸� �α��� ���� if (userVO ==
		 * null) { // (1)�α��� ���� �� session ��ü�� null ���� ����
		 * session.setAttribute("login", null); // (2) �α��� �������� �ٽ� �̵� return
		 * "redirect:login"; } // 2-2) userVO���� null ���� �ƴ� ��� �α��� ���� else { //
		 * (1) �α��� ���� �� session ��ü�� userVO ���� ���� session.setAttribute("login",
		 * userVO); // (2) ���� �������� �̵� return "redirect:../"; // }
		 */
		// return Ÿ���� void�̸� ��û�� �ּҰ� View�̸��� ��
		// ���⼭�� user/loginPost

	}

	// 3. �α׾ƿ� ��û ó��
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		// ���� �ʱ�ȭ
		session.invalidate();
		// ���� �������� �̵�
		return "redirect:/";
	}
}
