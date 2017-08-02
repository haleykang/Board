package com.haley.board.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.haley.board.service.UserService;

// 메소드에서 return하는 값을 JSON 형식의 문자열로 리턴해주는 컨트롤러
// return한 이름의 뷰 페이지로 이동 X
@RestController
public class JSONController {

	@Autowired
	private UserService userService;

	// 1. 아이디 중복 확인
	@RequestMapping("user/idCheck")
	public Map<String, Object> idCheck(@RequestParam("id") String id) {

		// 1) 아이디 중복 확인 결과 가져와서 저장
		boolean result = userService.idCheck(id);

		// 2) Map을 만들어서 result 키에 1)의 결과를 저장
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", result + "");

		// 3) map에 저장한 결과를 return (String또는 JSON 문자열로)
		return map;
	}

}
