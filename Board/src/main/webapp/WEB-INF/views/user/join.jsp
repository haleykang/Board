<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원가입</title>
</head>
<body>
	<%@ include file="../include/header.jsp"%>

	<script>
		// 현재 페이지가 로드되고 난 후 function을 수행
		// 스트립트 내에서 $가 들어가는 문장은 모두 JQuery 문
		$(function() {
			// id가 imgInp인 객체의 내용이 변경되면 함수를 수행
			$('#imgInp').on('change', function() {
				// 파일 객체를 가지고 함수를 호출
				readURL(this);
			});
		});

		// 위에서 사용한 자바 스크립트 함수 만들기 -> 자바 스크립트 문
		// input 이름은 달라져도 됨 input 위에서 this
		function readURL(input) {
			// 파일을 선택 했다면
			if (input.files && input.files[0]) {
				// 파일의 이름 가져오기
				var filename = input.files[0].name;
				// 가장 뒤의 3자 가져오기 - 거의 모든 언어가 문자열 함수는 유사함 
				var ext = filename.substr(filename.length - 3, filename.length);
				// 그림 파일의 확장자인지 확인
				if (ext.toLowerCase() == 'jpg' || ext.toLowerCase() == 'gif'
						|| ext.toLowerCase() == 'png') {
					// 그림파일의 확장자가 맞으면 파일의 내용을 읽어서 img(id가 image) 태그에 출력
					var reader = new FileReader();
					// 파일을 다 읽으면 호출되는 함수 설정 
					reader.onload = function(e) {
						$('#image').attr('src', e.target.result);
					}
					// 선택한 파일을 읽기
					reader.readAsDataURL(input.files[0]);

				} else {
					// 확장자가 이미지가 아님 
					alert("이미지 파일만 등록 가능합니다(확장자 - jpg, gif, png )");
					return;
				}
			}
		}

		// 아이디 입력 창에서 포커스가 넘어가면 아이디 중복 체크 후 결과에 따라 값을 idDiv에 출력하는 함수
		function confirmId() {
			// 요청 주소
			var addr = '/board/user/idCheck';
			// 넘겨줄 파라미터 값 - 하기 코드에서 id가 id인 부분에  입력된 값을 가져옴
			var id = $('#id').val();

			// jquery의 ajax
			// url에는 요청 주소
			// data에는 {}로 파라미터 작성 - 없으면 생략 가능
			// dateType에는 받아오는 데이터의 종류 - xml, json, csv 등등
			// success에는 성공했을 때 호출될 함수 설정
			// error에는 실패했을 때 호출될 함수 설정
			$.ajax({
				url : addr,
				data : {
					'id' : id
				},
				dataType : 'json',
				success : function(data) {
					// 일단 테스트
					// alert(data); -->  [object Object] 이렇게 창이 뜨면ㅇㅋ
					// alert(data.result);	// -> result 값이 나오면 ㅇㅋ
					if (data.result == 'true') {
						// 중복이 아닌 경우 idDiv에 사용가능한 아이디 입니다. 출력
						$('#idDiv').html('사용 가능한 아이디입니다.');
						// 성공했을 때 문자 색은 파란색
						$('#idDiv').css('color', 'blue');
						// 아이디 중복 검사 통과 여부를 저장
						// <input type="hidden" id="idCheck" value="false" /> 이부분의 value 값을 true로 
						$('#idCheck').val('true');

					} else {
						// 중복인  경우 idDiv에 사용할 수 없는 아이디입니다. 출력
						$('#idDiv').html('사용할 수 없는 아이디입니다.');
						// 성공했을 때 문자 색은 빨간색
						$('#idDiv').css('color', 'red');
						// 아이디 중복 검사 통과 여부를 저장
						$('#idCheck').val('false');

					}
				}

			});
		}

		// 폼에서 전송 버튼 누르면 실행되는 check() 함수 생성
		// <form id="joinform" enctype="multipart/form-data" method="post" onsubmit="return check()">
		function check() {
			if ($('#idCheck').val() == 'false') {
				// 아이디가 idCheck인 값의 value 값이 false 인 경우 -> 아이디 중복됐는데 회원 가입 버튼 클릭
				$('#idDiv').html('이미 사용중인 아이디입니다.');
				// 빨간 색으로 메세지 표시 
				$('#idDiv').css('color', 'red');
				// 다음 페이지로 넘어갈 수 없도록 설정(submit 할 수 없도록)
				return false;
			}
		}

		// 스크립트
	</script>

	<section class="content">
		<div class="box">
			<div class="box-body">
				<div class="col-md-2"></div>
				<div class="col-md-8">
					<div class="box-header with-border">
						<!-- 회원가입 -->
						<!-- form에 action을 안 줬기 때문에 이쪽으로 이동할 때 요청이름과 동일한 요청이름 -->
						<!--파일 업로드 폼은 enctype을 설정해야하고, 전송방식은 꼭 post!! -->
						<!-- onsubmit은 전송 버튼을 누를 때 발생하는 이벤트 -->
						<!-- 아이디 중복 검사를 하지 않으면 전송하지 못하도록 하기위해 이벤트 연결 -->
						<form id="joinform" enctype="multipart/form-data" method="post"
							onsubmit="return check()">
							<!-- 아이디 중복검사 성공 여부를 저장하기 위한 변수 -->
							<input type="hidden" id="idCheck" value="false" />
							<p align="center">
							<table class="table table-striped centered">
								<tr>
									<td colspan="2" align="center"><h2>회원 가입</h2></td>
								</tr>
								<tr>
									<td rowspan="4" align="center"><img id="image" width="200"
										height="200" /> <input type='file' class="form-control"
										id="imgInp" name="image" /></td>
								</tr>
								<tr>
									<td bgcolor="#f5f5f5">&nbsp;&nbsp;&nbsp;&nbsp;<label
										for="id">아이디</label> <!-- onblur : 아이디 입력란에서 포커스가 이동할 때 confirmId() 함수 호출 -->
										<input class="form-control" type="text" name="id" id="id"
										size=" 20" maxlength=30 onblur="confirmId()"
										required="required" placeholder="아이디를 입력하세요" /> <!-- 아이디 중복검사 후 중복 결과를 출력할 영역 -->
										<div id="idDiv"></div>
									</td>
								</tr>
								<tr>
									<td bgcolor="#f5f5f5">&nbsp;&nbsp;&nbsp;&nbsp;<label
										for="pw">비밀번호</label> <input type="password"
										class="form-control" name="pw" id="pw" size=" 20" maxlength=30
										required="required" placeholder="비밀번호를 입력하세요" />
									</td>
								</tr>
								<tr>
									<td bgcolor="#f5f5f5">&nbsp;&nbsp;&nbsp;&nbsp;<label
										for="name">이름</label> <!-- pattern="([a-z, A-Z, 가-힣]){2,}" : 이름은 한글이나 영문이고 2글자 이상이어야함!!!  -->
										<input class="form-control" type="text" name="name" id="name"
										size=" 20" maxlength=30 pattern="([a-z, A-Z, 가-힣]){2,}"
										placeholder="이름을 입력하세요" />
									</td>
								</tr>

								<tr>
									<td colspan="2" align="center"><br /> <input
										type="submit" value="회원가입" class="btn btn-success" /> <input
										type="button" value="메인으로" class="btn btn-prmary"
										onclick="javascript:window.location='/board'"></td>
								</tr>
							</table>
						</form>
					</div>
					<div class="col-md-2"></div>
				</div>
			</div>
		</div>
	</section>
	<%@ include file="../include/footer.jsp"%>
</body>
</html>