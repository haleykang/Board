package com.haley.board.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.haley.board.service.UserService;

// �޼ҵ忡�� return�ϴ� ���� JSON ������ ���ڿ��� �������ִ� ��Ʈ�ѷ�
// return�� �̸��� �� �������� �̵� X
@RestController
public class JSONController {

	@Autowired
	private UserService userService;

	// 1. ���̵� �ߺ� Ȯ��
	@RequestMapping("user/idCheck")
	public Map<String, Object> idCheck(@RequestParam("id") String id) {

		// 1) ���̵� �ߺ� Ȯ�� ��� �����ͼ� ����
		boolean result = userService.idCheck(id);

		// 2) Map�� ���� result Ű�� 1)�� ����� ����
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", result + "");

		// 3) map�� ������ ����� return (String�Ǵ� JSON ���ڿ���)
		return map;
	}

}
