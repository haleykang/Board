package com.haley.board.dao;

import com.haley.board.domain.UserVO;

// SpringUser ���̺� ���� �����ͺ��̽� �۾��� �����ϴ� �޼ҵ带 �����ϴ� �������̽�
public interface UserDao {

	// 1. �α��� ó�� �޼ҵ�
	public UserVO login(UserVO userVO);

	// 2. ȸ������ �� ���̵� �ߺ� �˻縦 ���� �޼ҵ�
	public String idCheck(String id);

}
