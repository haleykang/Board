package com.haley.board.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor {

	// Controller�� ��û ó�� �� �並 ���� �� �� ȣ��Ǵ� �޼ҵ�
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	// 2. Controller�� ��û ó�� �޼ҵ带 ���� �� �� �並 �����ϱ� �� ȣ��Ǵ� �޼ҵ�
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {

		// 1) �α��� ���� ���θ� �Ǵ��ϱ� ���� model�� ������ �����͸� ��������
		// -> �츮�� loginPost ��û�� ���ؼ� model�� "login" userVO ������ �����߾���
		ModelMap modelMap = arg3.getModelMap();
		Object login = modelMap.get("login");
		if (login == null) {
			// login == null --> �α��� ����
			arg1.sendRedirect(""); // �α��� ���� �� �̵��� ��û ����
		} else {
			// login != null --> �α��� ����
			// Session�� �α��� ���� �����ϰ� �ٸ� �������� �����̷�Ʈ
			HttpSession session = arg0.getSession();
			session.setAttribute("login", login);
			
			// ���ǿ��� "dest" Ű ���� �ִ� ������ ������
			String dest = (String)session.getAttribute("dest");
			if (dest == null) {
				// dest -> ������ ������ ������ �������� �̵�
				// dest �� null �̴� -> �α��� ��û���� �� �� 
				arg1.sendRedirect("/board");
			} else {
				// dest -> ������ ������ ������ �� �������� �̵�
				// ���� ���, �۾��� ��û�� ������ �� �α����� �ȵǾ��־ �α��� �������� �̵� �ߴٸ�
				// �α����� �� �� ���� �������� �ƴ� �۾��� �������� �ٷ� �̵��ϵ��� ����
				arg1.sendRedirect(dest);
			}
			// arg1.sendRedirect("/board"); // �α��� ���� �� �̵��� ��û ���� - �� ������Ʈ �ּҷ�

		}

	}

	// 1. Controller�� �������� ȣ��Ǵ� �޼ҵ�
	// true ���� - Controller�� ���� ������ ó�� ����
	// false ���� - Controller�� �̵� ����
	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		// �α��� ó�� ���� ���� �α��� ������ ������ ����
		// -> ��, �α����ϱ� ���� �̹� �α��� �� ������ ������ ����
		HttpSession session = arg0.getSession();
		if (session.getAttribute("login") != null) {
			session.removeAttribute("login");
		}
		return true;
	}

}
