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

	// 3-1 �󼼺��� ó�� �Լ�(�� ��ȣ ����)
	public BoardVO getBoard(int bno);

	// 3-2 �󼼺��� �� ��ȸ���� 1 ���� ��Ű�� �޼ҵ�
	public int updateCount(int bno);

	// 4. �Խñ� ���� �޼ҵ�
	public int deleteBoard(int bno);

	// 5. �Խñ� ���� �޼ҵ� -> insert�� ���� ���
	public int update(BoardVO vo);

}
