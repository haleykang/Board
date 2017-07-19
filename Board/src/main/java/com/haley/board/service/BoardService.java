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

}
