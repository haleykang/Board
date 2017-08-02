package com.haley.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haley.board.dao.UserDao;
import com.haley.board.domain.UserVO;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	// 1. �α��� ó�� �޼ҵ� ����
	@Override
	public UserVO login(UserVO userVO) {

		return userDao.login(userVO);
	}

	// 2. ���̵� �ߺ� üũ
	@Override
	public boolean idCheck(String id) {
		
		// 1) ���̵� Ȯ��
		String result = userDao.idCheck(id);
		// 2) ���̵� ������ true
		if (result == null) {
			return true;
		}
		// 3) ������ ���̵� ������ false
		return false;
	}

}
