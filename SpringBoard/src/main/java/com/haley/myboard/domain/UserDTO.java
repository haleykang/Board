package com.haley.myboard.domain;

import org.springframework.web.multipart.MultipartFile;

// 회원가입 폼의 데이터를 저장할 수 있는 DTO 클래스 생성 -> 테이블과 연동 X
// 파일은 MultipartFile 자료형 사용 
public class UserDTO {

	// 1. 변수 선언
	private String id;
	private String pw;
	private String name;
	// 이미지 자료형을 MultipartFile로 받는 변수
	private MultipartFile image;

	// 2. Get Set
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}

	// 3. toString()
	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", pw=" + pw + ", name=" + name + ", image=" + image + "]";
	}

}
