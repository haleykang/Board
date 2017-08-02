package com.haley.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haley.board.dao.UserDao;
import com.haley.board.domain.UserVO;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	// 1. 로그인 처리 메소드 구현
	@Override
	public UserVO login(UserVO userVO) {

		return userDao.login(userVO);
	}

	// 2. 아이디 중복 체크
	@Override
	public boolean idCheck(String id) {
		
		// 1) 아이디 확인
		String result = userDao.idCheck(id);
		// 2) 아이디가 없으면 true
		if (result == null) {
			return true;
		}
		// 3) 동일한 아이디가 있으면 false
		return false;
	}

}
