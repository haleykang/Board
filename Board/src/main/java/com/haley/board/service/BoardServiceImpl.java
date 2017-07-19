package com.haley.board.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haley.board.dao.BoardDao;
import com.haley.board.domain.BoardVO;

// 서비스 클래스
@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDao boardDao;

	// 1. 글쓰기를 처리하는 함수
	@Override
	public int writeBoard(HttpServletRequest request) {
		// 1) register.jsp 페이지에서 입력 받는 항목의 name에 입력된 값을 가져오기
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String id = request.getParameter("id");

		// 2) 클라이언트 IP 가져오기 - getRemoteAddr() 함수 사용
		String ip = request.getRemoteAddr();

		// 3) DAO에서 사용할 매개변수 만들기
		BoardVO boardVO = new BoardVO();
		boardVO.setTitle(title);
		boardVO.setContent(content);
		boardVO.setId(id);
		boardVO.setIp(ip);

		// 4) DAO 메소드 호출
		return boardDao.writeBoard(boardVO);
	}

	// 2. 글 목록 보기 처리 메소드
	@Override
	public List<BoardVO> boardList() {

		return boardDao.boardList();
	}

}
