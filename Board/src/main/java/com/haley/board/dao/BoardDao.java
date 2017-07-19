package com.haley.board.dao;

import java.util.List;

import com.haley.board.domain.BoardVO;

// �Խ��ǿ� �ʿ��� �޼ҵ� ������ Interface
public interface BoardDao {

	// 1. �� ���� ó�� �Լ�
	public int writeBoard(BoardVO boardVO);

	// 2. �� ��� ���� ó�� �Լ�
	// -> select�� where ���� ������ ���� �Ű������� ������
	public List<BoardVO> boardList();

}
