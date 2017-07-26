<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 상단의 공통된 디자인 적용 -->
<%@include file="../include/header.jsp"%>

<section class="content">
	<!-- 제목 만들기 -->
	<div class="box-header">
		<h3 class="box-title">게시판 글쓰기</h3>
	</div>

	<!-- textarea가 있기 때문에 전송 방식 post -->
	<!-- action이 요청을 처리할 서버의 주소, 없으면 이 페이지가 올 때 주소가 처리할 서버의 주소가 됨 -->
	<!-- 이 경우 board/register -->
	<form role="form" method="post">
		<div class="box-body">
			<div class="form-group">
				<label>제목</label> <input type="text" name='title'
					class="form-control" placeholder="제목을 입력하세요" required>
			</div>
			<div class="form-group">
				<label>내용</label>
				<textarea class="form-control" name="content" rows="5"
					placeholder="내용을 입력하세요" required></textarea>
			</div>

			<div class="form-group">
				<label>작성자</label> <input type="text" name="id" class="form-control"
					placeholder="작성자를 입력하세요" required>
			</div>
		</div>

		<div class="box-footer">
			<button type="submit" class="btn btn-primary">작성완료</button>
		</div>
	</form>
</section>

<!-- 하단의 공통된 디자인 적용 -->
<%@include file="../include/footer.jsp"%>
