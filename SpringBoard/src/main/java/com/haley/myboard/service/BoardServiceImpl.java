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

	// 1. 글쓰기 처리 함수 writeBoard
	@Override
	public int writeBoard(MultipartHttpServletRequest request) {

		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String id = request.getParameter("id");
		// 클라이언트 IP 가져오기
		String ip = request.getRemoteAddr();
		// Dao에서 사용할 매개변수 만들기
		BoardVO vo = new BoardVO();
		vo.setTitle(title);
		vo.setContent(content);
		vo.setId(id);
		vo.setIp(ip);
		MultipartFile image = request.getFile("image");
		String uploadPath = request.getServletContext().getRealPath("/boardimage");
		// 파일 이름 만들기
		UUID uid = UUID.randomUUID();
		String filename = image.getOriginalFilename();
		if (filename.length() != 0) {
			filename = uid + "_" + filename;
			// 저장된 파일 경로 만들기
			String filepath = uploadPath + "\\" + filename;
			// 파일 업로드
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
		// Dao 메소드 호출
		return boardDao.writeBoard(vo);
	}

	// 2. 글 목록 보기 처리 함수
	@Override
	public List<BoardVO> showList() {

		return boardDao.showList();
	}

	// 3. 게시글 상세보기 처리
	@Override
	public BoardVO getBoard(int bno) {
		// 1) 조회수 1 증가
		boardDao.updateCount(bno);
		// 2) 글 번호에 따른 테이블 데이터 반환
		return boardDao.getBoard(bno);
	}

	// 4. 게시글 삭제 처리 함수
	@Override
	public int deleteBoard(int bno) {

		return boardDao.deleteBoard(bno);
	}

	// 5. 게시글 수정 처리 함수
	@Override
	public BoardVO updateBoard(int bno) {
		// getBoard() 함수를 재사용해서 글 번호가 일치하는 테이블 값 가져오기
		return boardDao.getBoard(bno);
	}

	// 6. 게시글 실제 수정 처리 함수
	@Override
	public int myUpdate(BoardVO vo) {

		return boardDao.myUpdate(vo);
	}

}
