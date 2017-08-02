package com.haley.board.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.haley.board.domain.UserVO;

@Repository
public class UserDaoImp implements UserDao {

	@Autowired
	private SqlSession sqlSession;

	// 1. �α��� ó��
	@Override
	public UserVO login(UserVO userVO) {
		// id & pw �� �˻� ���� �� ���� �� �ִ� ���� 1�� -> selectOne
		return sqlSession.selectOne("user.login", userVO);
	}

	// 2. ���̵� �ߺ� üũ
	@Override
	public String idCheck(String id) {

		return sqlSession.selectOne("user.idCheck", id);
	}

}
