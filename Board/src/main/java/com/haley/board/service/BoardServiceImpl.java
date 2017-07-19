package com.haley.board.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haley.board.dao.BoardDao;
import com.haley.board.domain.BoardVO;

// ���� Ŭ����
@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDao boardDao;

	// 1. �۾��⸦ ó���ϴ� �Լ�
	@Override
	public int writeBoard(HttpServletRequest request) {
		// 1) register.jsp ���������� �Է� �޴� �׸��� name�� �Էµ� ���� ��������
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String id = request.getParameter("id");

		// 2) Ŭ���̾�Ʈ IP �������� - getRemoteAddr() �Լ� ���
		String ip = request.getRemoteAddr();

		// 3) DAO���� ����� �Ű����� �����
		BoardVO boardVO = new BoardVO();
		boardVO.setTitle(title);
		boardVO.setContent(content);
		boardVO.setId(id);
		boardVO.setIp(ip);

		// 4) DAO �޼ҵ� ȣ��
		return boardDao.writeBoard(boardVO);
	}

	// 2. �� ��� ���� ó�� �޼ҵ�
	@Override
	public List<BoardVO> boardList() {

		return boardDao.boardList();
	}

}
