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

	// 3-1) 게시판 상세보기 구현
	@Override
	public BoardVO getBoard(int bno) {

		return sqlSession.selectOne("board.getBoard", bno);
	}

	// 3-2) 조회수 1 증가 SQL
	@Override
	public int updateCount(int bno) {

		return sqlSession.update("board.updateCount", bno);
	}

	// 4. 게시글 삭제 SQL 실행
	@Override
	public int deleteBoard(int bno) {

		return sqlSession.delete("board.deleteBoard", bno);
	}

	// 5. 게시글 수정 SQL 구현
	@Override
	public int update(BoardVO vo) {

		return sqlSession.update("board.update", vo);
	}

}
