package com.haley.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.haley.board.domain.BoardVO;

// BoardDao �������̽��� implements �ϴ� Ŭ����

@Repository
public class BoardDaoImpl implements BoardDao {

	@Autowired
	private SqlSession sqlSession;

	// 1. �Խ��� �۾��� ����
	@Override
	public int writeBoard(BoardVO boardVO) {

		return sqlSession.insert("board.writeBoard", boardVO);
	}

	// 2. �Խ��� ��Ϻ��� ����
	@Override
	public List<BoardVO> boardList() {

		return sqlSession.selectList("board.boardList");
	}

}