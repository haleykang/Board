package com.haley.myboard.service;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.haley.myboard.dao.BoardDao;
import com.haley.myboard.domain.BoardVO;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDao boardDao;

	// 1. �۾��� ó�� �Լ� writeBoard
	@Override
	public int writeBoard(MultipartHttpServletRequest request) {

		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String id = request.getParameter("id");
		// Ŭ���̾�Ʈ IP ��������
		String ip = request.getRemoteAddr();
		// Dao���� ����� �Ű����� �����
		BoardVO vo = new BoardVO();
		vo.setTitle(title);
		vo.setContent(content);
		vo.setId(id);
		vo.setIp(ip);
		MultipartFile image = request.getFile("image");
		String uploadPath = request.getServletContext().getRealPath("/boardimage");
		// ���� �̸� �����
		UUID uid = UUID.randomUUID();
		String filename = image.getOriginalFilename();
		if (filename.length() != 0) {
			filename = uid + "_" + filename;
			// ����� ���� ��� �����
			String filepath = uploadPath + "\\" + filename;
			// ���� ���ε�
			File file = new File(filepath);
			try {
				image.transferTo(file);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			vo.setImage(filename);
		}

		else {
			vo.setImage("");
		}
		// Dao �޼ҵ� ȣ��
		return boardDao.writeBoard(vo);
	}

	// 2. �� ��� ���� ó�� �Լ�
	@Override
	public List<BoardVO> showList() {

		return boardDao.showList();
	}

	// 3. �Խñ� �󼼺��� ó��
	@Override
	public BoardVO getBoard(int bno) {
		// 1) ��ȸ�� 1 ����
		boardDao.updateCount(bno);
		// 2) �� ��ȣ�� ���� ���̺� ������ ��ȯ
		return boardDao.getBoard(bno);
	}

	// 4. �Խñ� ���� ó�� �Լ�
	@Override
	public int deleteBoard(int bno) {

		return boardDao.deleteBoard(bno);
	}

	// 5. �Խñ� ���� ó�� �Լ�
	@Override
	public BoardVO updateBoard(int bno) {
		// getBoard() �Լ��� �����ؼ� �� ��ȣ�� ��ġ�ϴ� ���̺� �� ��������
		return boardDao.getBoard(bno);
	}

	// 6. �Խñ� ���� ���� ó�� �Լ�
	@Override
	public int myUpdate(BoardVO vo) {

		return boardDao.myUpdate(vo);
	}

}
