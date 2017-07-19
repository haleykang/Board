package com.haley.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.haley.board.domain.BoardVO;

// BoardDao 인터페이스를 implements 하는 클래스

@Repository
public class BoardDaoImpl implements BoardDao {

	@Autowired
	private SqlSession sqlSession;

	// 1. 게시판 글쓰기 구현
	@Override
	public int writeBoard(BoardVO boardVO) {

		return sqlSession.insert("board.writeBoard", boardVO);
	}

	// 2. 게시판 목록보기 구현
	@Override
	public List<BoardVO> boardList() {

		return sqlSession.selectList("board.boardList");
	}

}
