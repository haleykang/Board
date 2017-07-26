package com.haley.board.service;

import com.haley.board.domain.UserVO;

public interface UserService {
	
	// 1. 로그인 처리 메소드 선언
	public UserVO login(UserVO userVO);

}
