package com.haley.board.service;

import com.haley.board.domain.UserVO;

public interface UserService {

	// 1. 로그인 처리 메소드 선언
	public UserVO login(UserVO userVO);

	// 2. 아이디 중복 체크 메소드 선언
	// -> 아이디 존재 여부만 알려주면 되므로 - 중복 됐을 때 false, 중복이 아닐때 true 리턴
	public boolean idCheck(String id);
}
