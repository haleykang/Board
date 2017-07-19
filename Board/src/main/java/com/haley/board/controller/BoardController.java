package com.haley.board.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.haley.board.domain.BoardVO;
import com.haley.board.service.BoardService;

// Controller ����
@Controller
// Controller�� ó���� ���� �ּ� ����
// -> /board�� �����ϴ� �ּҴ� �� ��Ʈ�ѷ��� ó��
@RequestMapping("/board/*")
public class BoardController {

	@Inject
	private BoardService boardService;

	// 1. ����ȭ�鿡�� �۾��� ��ư Ŭ�� �� - register.jsp�������� �̵��ϴ� �޼ҵ�
	// - > /board/register ��û�� ó��
	@RequestMapping(value = "register", method = RequestMethod.GET)
	// �ܼ��� ������ �̵��� �� ���� return Ÿ���� void�� ���� - ��û �ּҰ� view �̸��� ��
	// �� ��� �� �̸��� board/register�� ��
	public void registerGET() {

	}

	// 2. ����ڰ� �� �� �� "�ۼ��Ϸ�" ��ư Ŭ�� ��û �� ó���� �޼ҵ�
	// -> /board/regiser ��û�� ó�� : form���� action���� �� ��� ����
	// -> 1���� 2�� �޼ҵ� �̸��� ���Ƶ� ��(��, �Ű������� �޶����)
	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String registerPOST(HttpServletRequest request) {

		int r = boardService.writeBoard(request);
		if (r < 1) {
			// �� ���� ���н� �̵��� ������ �ۼ�
			// -> �� ���� �������� �ٽ� �̵�
			return "redirect:register";
		} else {
			// �� ���� ������ �̵��� �ּ� �ۼ� -> insert �߱� ������ redirect!
			// -> ��� ���� �������� �̵� & ��� ���� ���� ���̶� �ϴ� ���� ȭ������ �̵�
			return "redirect:list";

		}

	}

	// 3. ��� ���� ��û ó���� �޼ҵ�
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(Model model) {
		// 1) ���̺��� ������ ��������
		List<BoardVO> list = boardService.boardList();
		// 2) ������ ������ ����
		model.addAttribute("list", list);
		// 3) ����� �������� �̵�
		return "board/list";
	}

}
