package com.haley.myboard.service;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haley.myboard.dao.UserDao;
import com.haley.myboard.domain.UserDTO;
import com.haley.myboard.domain.UserVO;

@Service // ���� Ŭ���� ����
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	// 1. �α��� ó�� �޼ҵ�
	@Override
	public UserVO login(UserVO userVO) {

		return userDao.login(userVO);
	}

	// 2. ���̵� �ߺ� üũ
	@Override
	public boolean idCheck(String id) {
		// 1) userDao.iDcheck �����ؼ� ���� �� ��������
		String result = userDao.idCheck(id);
		// 2) �ߺ� ���� Ȯ��
		if (result == null) {
			// �ߺ��Ǵ� ���̵� ���� ��� result == null
			return true;
		}
		// result �� null�� �ƴϸ� ���̵� �ߺ�
		return false;
	}

	// 3. ȸ������ ó�� �޼ҵ� ���� ** �߿�
	@Override
	public void insertUser(UserDTO dto, HttpServletRequest request) {
		// 1) ����ڰ� �Է��� ������ ���̺� �����ϱ� ���ؼ� ����� UserVO ��ü ����
		UserVO userVo = new UserVO();

		// 2) ������ ���ε��� ����� ���� ��� ã�ƿ���(request ��ü ���)
		// - /userimage �� ������ webcontent(�Ǵ� webapp)�� �����������
		String uploadPath = request.getServletContext().getRealPath("/userimage");

		// 3) ���ε��Ϸ��� ����ڰ� ������ ������ �̸� ã�ƿ���
		String filename = dto.getImage().getOriginalFilename();

		// 4) ������ ���� �̸��� ��������ؼ� ������ ���ڿ� �����(UUID Ŭ���� ���)
		try {
			// ������ ������ �ִ� ��쿡�� ����
			if (filename.length() != 0) {
				// (1) ���� ���ڿ� ����
				UUID uuid = UUID.randomUUID(); // ���� �ߺ����� �ʴ� ���ڿ��� ��������
				// (2) ���� �̸� �����
				String fname = uuid + filename;
				// (3) ���ε��� ���� ��� ����� -> �ƿ��� ����� ���� / �� ���
				// ���� ��θ� ���� �� ���� ���丮�� ���� �̸� ���̿� \(�� ǥ��)�� �ϳ� �־���ϴµ� �ϳ��� ������
				// ���������� �ν��� ����� ���ؼ� �ΰ�..
				String filepath = uploadPath + "\\" + fname;
				// (4) ���� ���ε�
				File file = new File(filepath);
				dto.getImage().transferTo(file);
				// (5) ���� �̸��� VO�� ����
				userVo.setImage(fname);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		// 5) �Է� ���� �����͸� VO�� ����
		userVo.setId(dto.getId());
		userVo.setPw(dto.getPw());
		userVo.setName(dto.getName());

		// 6) Dao �޼ҵ� ȣ��
		userDao.insertUser(userVo);
	}

}
