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

	// 3-1 상세보기 처리 함수(글 번호 기준)
	public BoardVO getBoard(int bno);

	// 3-2 상세보기 시 조회수를 1 증가 시키는 메소드
	public int updateCount(int bno);

	// 4. 게시글 삭제 메소드
	public int deleteBoard(int bno);

	// 5. 게시글 수정 메소드 -> insert와 같은 모양
	public int update(BoardVO vo);

}
