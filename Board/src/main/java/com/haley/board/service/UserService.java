package com.haley.board.service;

import com.haley.board.domain.UserVO;

public interface UserService {

	// 1. �α��� ó�� �޼ҵ� ����
	public UserVO login(UserVO userVO);

	// 2. ���̵� �ߺ� üũ �޼ҵ� ����
	// -> ���̵� ���� ���θ� �˷��ָ� �ǹǷ� - �ߺ� ���� �� false, �ߺ��� �ƴҶ� true ����
	public boolean idCheck(String id);
}
