package com.haley.board.dao;

import com.haley.board.domain.UserVO;

// SpringUser 테이블에 대한 데이터베이스 작업을 수행하는 메소드를 소유하는 인터페이스
public interface UserDao {

	// 1. 로그인 처리 메소드
	public UserVO login(UserVO userVO);

}
