package com.haley.myboard.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.haley.myboard.domain.UserVO;
import com.haley.myboard.service.UserService;

//�޼ҵ忡�� return�ϴ� ���� JSON ������ ���ڿ��� �������ִ� ��Ʈ�ѷ�
//return�� �̸��� �� �������� �̵� X
@RestController
public class JSONController {

	@Autowired
	private UserService service;

	// 1. ��û ���������� ���̵� �����ͼ� ���̵� �ߺ� Ȯ�� �� ����� MAP���� ��ȯ�ϴ� �޼ҵ�
	@RequestMapping(value = "user/idCheck", method = RequestMethod.GET)
	public Map<String, Object> idCheck(@RequestParam("id") String id) {
		// 1) UserService�� idCheck �޼ҵ带 �����ؼ� ��� ����
		boolean result = service.idCheck(id);

		// 2) Map�� �����ؼ� result�� ����� "result" Ű�� map�� ����
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", result);

		// 3) map�� ������ ����� ������
		return map;

	}

	// 2. �ȵ���̵� �α��� - ����Ͽ��� id�� pw�� �����ͼ� �α��� ���ɿ��� Ȯ�� �� ��� return���ִ� �޼ҵ�
	@RequestMapping("androidlogin")
	public Map<String, Object> login(@RequestParam("id") String id, @RequestParam("pw") String pw) {
		// 1) ����ڰ� �Է��� id�� pw�� ����
		UserVO login = new UserVO();
		login.setId(id);
		login.setPw(pw);
		// 2) service�� �α��� �޼ҵ� ����
		UserVO result = service.login(login);
		// �α��� ������ vo�� �����͸� �����ؼ� �����ϰ�
		// �α��ο� �����ϸ� null ����

		// 3) �α��� ����� map�� ����
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", result);

		// 4) ������ ���� ����
		return map;

	}

}
