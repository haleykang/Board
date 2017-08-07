package com.haley.myboard.service;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haley.myboard.dao.UserDao;
import com.haley.myboard.domain.UserDTO;
import com.haley.myboard.domain.UserVO;

@Service // 서비스 클래스 선언
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	// 1. 로그인 처리 메소드
	@Override
	public UserVO login(UserVO userVO) {

		return userDao.login(userVO);
	}

	// 2. 아이디 중복 체크
	@Override
	public boolean idCheck(String id) {
		// 1) userDao.iDcheck 실행해서 리턴 값 가져오기
		String result = userDao.idCheck(id);
		// 2) 중복 여부 확인
		if (result == null) {
			// 중복되는 아이디가 없는 경우 result == null
			return true;
		}
		// result 가 null이 아니면 아이디 중복
		return false;
	}

	// 3. 회원가입 처리 메소드 구현 ** 중요
	@Override
	public void insertUser(UserDTO dto, HttpServletRequest request) {
		// 1) 사용자가 입력한 정보를 테이블에 저장하기 위해서 사용할 UserVO 객체 생성
		UserVO userVo = new UserVO();

		// 2) 파일을 업로드할 경로의 절대 경로 찾아오기(request 객체 사용)
		// - /userimage 이 폴더를 webcontent(또는 webapp)에 생성해줘야함
		String uploadPath = request.getServletContext().getRealPath("/userimage");

		// 3) 업로드하려고 사용자가 선택한 파일의 이름 찾아오기
		String filename = dto.getImage().getOriginalFilename();

		// 4) 유일한 파일 이름을 만들기위해서 유일한 문자열 만들기(UUID 클래스 사용)
		try {
			// 선택한 파일이 있는 경우에만 수행
			if (filename.length() != 0) {
				// (1) 랜덤 문자열 생성
				UUID uuid = UUID.randomUUID(); // 절대 중복되지 않는 문자열을 생성해줌
				// (2) 파일 이름 만들기
				String fname = uuid + filename;
				// (3) 업로드할 파일 경로 만들기 -> 맥에서 사용할 때는 / 로 사용
				// 파일 경로를 설정 할 때는 디렉토리와 파일 이름 사이에 \(원 표시)가 하나 있어야하는데 하나만 적으면
				// 스프링에서 인식을 제대로 못해서 두개..
				String filepath = uploadPath + "\\" + fname;
				// (4) 파일 업로드
				File file = new File(filepath);
				dto.getImage().transferTo(file);
				// (5) 파일 이름을 VO에 저장
				userVo.setImage(fname);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		// 5) 입력 받은 데이터를 VO에 저장
		userVo.setId(dto.getId());
		userVo.setPw(dto.getPw());
		userVo.setName(dto.getName());

		// 6) Dao 메소드 호출
		userDao.insertUser(userVo);
	}

}
