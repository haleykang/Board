package com.haley.board.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.haley.board.domain.BoardVO;

// Service 인터페이스  - SQL로 받아올 수 없는 값을 서비스 인터페이스에서 처리해서 가져와야 함
public interface BoardService {

	// 1. 글쓰기를 처리할 메소드
	// 매개변수 HttpServletRequest -> 파라미터도 읽어야하고 아이피를 찾아와야해서
	// 아이피를 찾아오는 것처럼 HttpServletRequest가 꼭 있어야 하는 경우가 아니라면
	// 보통 Dao와 매개 변수가 같음
	public int writeBoard(HttpServletRequest request);

	// 2. 글 목록보기 처리 메소드 -> 매개변수 없으니 간단!
	public List<BoardVO> boardList();

}
