package com.haley.board.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.haley.board.domain.BoardVO;
import com.haley.board.service.BoardService;

// Controller 선언
@Controller
// Controller가 처리할 공통 주소 설정
// -> /board로 시작하는 주소는 이 컨트롤러가 처리
@RequestMapping("/board/*")
public class BoardController {

	@Inject
	private BoardService boardService;

	// 1. 메인화면에서 글쓰기 버튼 클릭 시 - register.jsp페이지로 이동하는 메소드
	// - > /board/register 요청을 처리
	@RequestMapping(value = "register", method = RequestMethod.GET)
	// 단순히 페이지 이동을 할 때는 return 타임을 void로 설정 - 요청 주소가 view 이름이 됨
	// 이 경우 뷰 이름이 board/register가 됨
	public void registerGET() {

	}

	// 2. 사용자가 글 쓴 후 "작성완료" 버튼 클릭 요청 시 처리할 메소드
	// -> /board/regiser 요청을 처리 : form에서 action값을 안 줬기 때문
	// -> 1번과 2번 메소드 이름은 같아도 됨(단, 매개변수가 달라야함)
	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String registerPOST(HttpServletRequest request, RedirectAttributes attr) {

		int r = boardService.writeBoard(request);
		if (r < 1) {
			// 글 쓰기 실패시 이동할 페이지 작성
			// -> 글 쓰기 페이지로 다시 이동
			return "redirect:register";
		} else {
			// 글 쓰기 성공시 이동할 주소 작성 -> insert 했기 때문에 redirect!
			// -> 목록 보기 페이지로 이동 & 목록 보기 구현 전이라 일단 메인 화면으로 이동
			// 삽입 성공시 출력할 메세지 저장
			attr.addFlashAttribute("msg", "게시글이 작성되었습니다.");
			return "redirect:list";

		}

	}

	// 3. 목록 보기 요청 처리할 메소드
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(Model model) {
		// 1) 테이블에서 데이터 가져오기
		List<BoardVO> list = boardService.boardList();
		// 2) 가져온 데이터 저장
		model.addAttribute("list", list);
		// 3) 출력할 페이지로 이동
		return "board/list";
	}

	// 4. 상세보기 처리 메소드
	@RequestMapping(value = "detail", method = RequestMethod.GET)
	public String detail(@RequestParam("bno") int bno, Model model) {
		// System.out.println("테스트");
		// 1) 글번호 기준으로 테이블에서 데이터 가져오기 + 조회수 증가
		BoardVO vo = boardService.getBoard(bno);
		// 2) 가져온 데이터를 결과 페이지로 이동하기 위해 저장
		model.addAttribute("vo", vo);
		// 3) 출력할 페이지로 이동
		return "board/detail";
	}

	// 5. 게시글 삭제 처리 메소드
	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public String delete(@RequestParam("bno") int bno, RedirectAttributes attr) {
		// 1) 삭제 SQL 실행
		boardService.deleteBoard(bno);
		// 2) 삭제 성공 메세지 저장
		attr.addFlashAttribute("msg", "게시글이 삭제되었습니다.");
		// 3) redirect로 목록보기 페이지로 이동 -> 데이터베이스에 변화를 줬기 때문에
		return "redirect:list";
	}

	// 6. 게시글 수정 처리 메소드 - 게시글 수정 페이지로 이동하는 메소드
	@RequestMapping(value = "update", method = RequestMethod.GET)
	public String update(@RequestParam("bno") int bno, Model model) {
		// 1) 글 번호에 따른 테이블 데이터 출력 후 VO 클래스에 저장
		BoardVO vo = boardService.updateBoard(bno);
		// 2) 가져온 값을 model 변수에 저장
		model.addAttribute("vo", vo);
		// 3) 결과 페이지 이동
		return "board/updateView";

	}

	// 7. 수정 실제 처리 메소드
	@RequestMapping(value = "modify", method = RequestMethod.POST)
	public String doUpdate(BoardVO vo, RedirectAttributes attr) {
		// 1) 업데이트하기(jsp 페이지에서 name의 값이 VO 변수에 저장됨)
		boardService.update(vo);
		// 2) msg 메세지 전달
		attr.addFlashAttribute("msg", "게시글이 수정되었습니다.");
		// 3) list 페이지로 이동
		return "redirect:list";
	}

}
