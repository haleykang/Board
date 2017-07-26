package com.haley.board.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	public String login(UserVO vo, HttpSession session) {
		// 1) �Ķ���͸� ���� ���� ������ �α����� �õ��ؼ� ����� userVO�� ����
		UserVO userVO = userService.login(vo);

		// 2) �α��� ���� ���� ����
		// 2-1) userVO ���� null�̸� �α��� ����
		if (userVO == null) {
			// (1)�α��� ���� �� session ��ü�� null ���� ����
			session.setAttribute("login", null);
			// (2) �α��� �������� �ٽ� �̵�
			return "redirect:login";
		}
		// 2-2) userVO���� null ���� �ƴ� ��� �α��� ����
		else {
			// (1) �α��� ���� �� session ��ü�� userVO ���� ����
			session.setAttribute("login", userVO);
			// (2) ���� �������� �̵�
			return "redirect:../"; //
		}

	}
}
