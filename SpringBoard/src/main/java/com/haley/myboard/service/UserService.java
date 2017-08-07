package com.haley.myboard.service;

import javax.servlet.http.HttpServletRequest;

import com.haley.myboard.domain.UserDTO;
import com.haley.myboard.domain.UserVO;

public interface UserService {

	// 1. 로그인 처리 메소드 선언
	public UserVO login(UserVO userVO);

	// 2. 아이디 중복체크 처리
	// 아이디 중복 -> false, 중복아님 true로 리턴 예정
	public boolean idCheck(String id);

	// 3. 회원가입 메소드 선언
	// 회원가입 폼에서 받은 데이터와 파일을 업로드 하기 위해서 2개의 매개변수를 필요
	// 폼의 데이터를 저장하고 있는 UserDTO 클래스와 HttpServletRequest
	public void insertUser(UserDTO dto, HttpServletRequest request);

}
