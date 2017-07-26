package com.haley.board.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.haley.board.domain.BoardVO;

// Service �������̽�  - SQL�� �޾ƿ� �� ���� ���� ���� �������̽����� ó���ؼ� �����;� ��
public interface BoardService {

	// 1. �۾��⸦ ó���� �޼ҵ�
	// �Ű����� HttpServletRequest -> �Ķ���͵� �о���ϰ� �����Ǹ� ã�ƿ;��ؼ�
	// �����Ǹ� ã�ƿ��� ��ó�� HttpServletRequest�� �� �־�� �ϴ� ��찡 �ƴ϶��
	// ���� Dao�� �Ű� ������ ����
	public int writeBoard(HttpServletRequest request);

	// 2. �� ��Ϻ��� ó�� �޼ҵ� -> �Ű����� ������ ����!
	public List<BoardVO> boardList();

	// 3. �󼼺��� ����
	public BoardVO getBoard(int bno);

	// 4. �Խñ� ���� ����
	public int deleteBoard(int bno);

	// 5. �Խñ� ���� ���� - �ϳ��� �����͸� �������� �޼ҵ�
	public BoardVO updateBoard(int bno);

	// 6. ������ ������ ���� �ϴ� �޼ҵ� ����
	public int update(BoardVO vo);
}
