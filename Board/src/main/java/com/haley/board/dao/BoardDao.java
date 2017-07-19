package com.haley.board.dao;

import java.util.List;

import com.haley.board.domain.BoardVO;

// 게시판에 필요한 메소드 선언할 Interface
public interface BoardDao {

	// 1. 글 쓰기 처리 함수
	public int writeBoard(BoardVO boardVO);

	// 2. 글 목록 보기 처리 함수
	// -> select는 where 절의 유무에 따라 매개변수가 결정됨
	public List<BoardVO> boardList();

}
