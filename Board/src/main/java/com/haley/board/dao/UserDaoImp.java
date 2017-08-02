package com.haley.board.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.haley.board.domain.UserVO;

@Repository
public class UserDaoImp implements UserDao {

	@Autowired
	private SqlSession sqlSession;

	// 1. 로그인 처리
	@Override
	public UserVO login(UserVO userVO) {
		// id & pw 로 검색 했을 때 나올 수 있는 값은 1개 -> selectOne
		return sqlSession.selectOne("user.login", userVO);
	}

	// 2. 아이디 중복 체크
	@Override
	public String idCheck(String id) {

		return sqlSession.selectOne("user.idCheck", id);
	}

}
