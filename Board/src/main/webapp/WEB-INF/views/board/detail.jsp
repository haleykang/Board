<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>상세보기</title>
</head>
<body>
	<!-- 상단 공통 디자인 삽입 -->
	<%@ include file="../include/header.jsp"%>
	<section class="content">
	<div class="box">
		<div class="box-header">
			<h3 class="box-title">상세보기</h3>
		</div>

		<div class="box-body">
			<div class="form-group">
				<label>제목</label> <input type="text" name="title"
					class="form-control" value="${vo.title}" readonly="readonly" />
			</div>
			<div class="form-group">
				<label>내용</label>
				<textarea name="content" class="form-control" rows="5"
					readonly="readonly">${vo.content}</textarea>
			</div>
			<div class="form-group">
				<label>작성자</label> <input type="text" name="id" class="form-control"
					value="${vo.id}" readonly="readonly" />
			</div>
		</div>
		<!-- 필요한 버튼 추가 -->
		<div class="box-footer">
			<button id="mainBtn" class="btn btn-success">메인</button>
			<button id="listBtn" class="btn btn-warning">목록</button>
			<button id="updateBtn" class="btn btn-danger">수정</button>
			<button id="deleteBtn" class="btn btn-primary">삭제</button>
		</div>
	</div>

	<!-- 버튼 처리를 위한 스크립트 --> <script>
		// jquery에서 body의 모든 내용을 읽고 나면
		$(function() {

			// 메인으로 이동하기
			$('#mainBtn').on('click', function() {
				location.href = "../";
			});
			// 목록 보기로 이동
			$('#listBtn').on('click', function() {
				// 같은 디렉토리에 있으니까 board/생략 
				location.href = "list";
			});

			// 수정버튼 클릭 처리
			$('#updateBtn').on('click', function() {
				location.href = "update?bno=${vo.bno}";

			});
			// 삭제버튼 클릭 처리 - > 요청 번호 뒤에 bno 값을 파라미터로 보내기 꼭!
			$('#deleteBtn').on('click', function() {
				location.href = "delete?bno=${vo.bno}";
			});

		});
	</script> </section>

	<!-- 하단 공통 디자인 삽입 -->
	<%@ include file="../include/footer.jsp"%>

</body>
</html>