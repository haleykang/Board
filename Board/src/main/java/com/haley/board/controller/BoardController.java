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
	public String registerPOST(HttpServletRequest request, RedirectAttributes attr) {

		int r = boardService.writeBoard(request);
		if (r < 1) {
			// �� ���� ���н� �̵��� ������ �ۼ�
			// -> �� ���� �������� �ٽ� �̵�
			return "redirect:register";
		} else {
			// �� ���� ������ �̵��� �ּ� �ۼ� -> insert �߱� ������ redirect!
			// -> ��� ���� �������� �̵� & ��� ���� ���� ���̶� �ϴ� ���� ȭ������ �̵�
			// ���� ������ ����� �޼��� ����
			attr.addFlashAttribute("msg", "�Խñ��� �ۼ��Ǿ����ϴ�.");
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

	// 4. �󼼺��� ó�� �޼ҵ�
	@RequestMapping(value = "detail", method = RequestMethod.GET)
	public String detail(@RequestParam("bno") int bno, Model model) {
		// System.out.println("�׽�Ʈ");
		// 1) �۹�ȣ �������� ���̺��� ������ �������� + ��ȸ�� ����
		BoardVO vo = boardService.getBoard(bno);
		// 2) ������ �����͸� ��� �������� �̵��ϱ� ���� ����
		model.addAttribute("vo", vo);
		// 3) ����� �������� �̵�
		return "board/detail";
	}

	// 5. �Խñ� ���� ó�� �޼ҵ�
	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public String delete(@RequestParam("bno") int bno, RedirectAttributes attr) {
		// 1) ���� SQL ����
		boardService.deleteBoard(bno);
		// 2) ���� ���� �޼��� ����
		attr.addFlashAttribute("msg", "�Խñ��� �����Ǿ����ϴ�.");
		// 3) redirect�� ��Ϻ��� �������� �̵� -> �����ͺ��̽��� ��ȭ�� ��� ������
		return "redirect:list";
	}

	// 6. �Խñ� ���� ó�� �޼ҵ� - �Խñ� ���� �������� �̵��ϴ� �޼ҵ�
	@RequestMapping(value = "update", method = RequestMethod.GET)
	public String update(@RequestParam("bno") int bno, Model model) {
		// 1) �� ��ȣ�� ���� ���̺� ������ ��� �� VO Ŭ������ ����
		BoardVO vo = boardService.updateBoard(bno);
		// 2) ������ ���� model ������ ����
		model.addAttribute("vo", vo);
		// 3) ��� ������ �̵�
		return "board/updateView";

	}

	// 7. ���� ���� ó�� �޼ҵ�
	@RequestMapping(value = "modify", method = RequestMethod.POST)
	public String doUpdate(BoardVO vo, RedirectAttributes attr) {
		// 1) ������Ʈ�ϱ�(jsp ���������� name�� ���� VO ������ �����)
		boardService.update(vo);
		// 2) msg �޼��� ����
		attr.addFlashAttribute("msg", "�Խñ��� �����Ǿ����ϴ�.");
		// 3) list �������� �̵�
		return "redirect:list";
	}

}
