package com.haley.board.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class AuthInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	// Controller���� ��û ó�� �� �� �������� �̵��ϱ� �� ȣ��Ǵ� �޼ҵ�
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	// Controller���� ��û�� ó���ϱ� ���� ȣ��Ǵ� �޼ҵ�
	// false - Controller ó�� ����
	// true - Controller ó�� ����
	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		// �۾��� �̵� ���� �α��� ���θ� Ȯ���ؾ��ϱ� ������ preHandle���� ó��
		// 1) ���� �������� - ������Ʈ�� ������ ����ٴ� ���� �� ����� �� !!
		// -> ���ǿ� �����ϴ� ����! request ��ü�� ������ ��Ŀ����� ���� ����
		// -> �츮�� �����̷�Ʈ�� �̵��ϱ� ������ session ��ü�� ���� �����ؾ���
		HttpSession session = arg0.getSession();
		// 2) �α��� ���� Ȯ��
		// 2-1) �α��� �ȵǾ����� ��
		if (session.getAttribute("login") == null) {
			// (1)�α��� �������� �̵��ϱ� ���� ��û�ߴ� ��θ� ���ǿ� ����
			String uri = arg0.getRequestURI(); // Ŭ���̾�Ʈ�� ��û ��θ� ������

			// (2) �Ķ���� ���ڿ� ��������
			String query = arg0.getQueryString(); // ������Ʈ�� ��������
			// (3) query�� ������ query�� ""���� ����, �׷��� ������ ? ���·� ���̱�
			if (query == null || query.equals("null")) {
				query = "";
			} else {
				query = "?" + query;
			}
			// (4) ���ǿ� uri�� query�� ���� - dest �̸����� �����ϱ�
			session.setAttribute("dest", uri + query);

			// (2)�α��� �������� �����̷�Ʈ
			arg1.sendRedirect("/board/user/login");
			// (3)��û���� �̵� ����
			return false;
		}
		// 2-2) �α��� �Ǿ����� �� - ������ ��û�� ó���ϵ���
		return true;
	}

}
