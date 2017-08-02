package com.haley.board.dao;

import com.haley.board.domain.UserVO;

// SpringUser 테이블에 대한 데이터베이스 작업을 수행하는 메소드를 소유하는 인터페이스
public interface UserDao {

	// 1. 로그인 처리 메소드
	public UserVO login(UserVO userVO);

	// 2. 회원가입 시 아이디 중복 검사를 위한 메소드
	public String idCheck(String id);

}
