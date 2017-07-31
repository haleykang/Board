<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- session 값 사용을 위해 삭제 -->
<%-- <%@ page session="false"%> --%>

<!-- header.jsp 파일의 내용을 가져와서 삽입 -->
<%@include file="include/header.jsp"%>
<section class="content">
	<div class="box">

		<!-- 로그인 화면으로 이동하는 링크 생성 -->
		<div class="box-header with-border">

			<!-- session으로 전달한 login 값이 null이면 로그인이 필요한 상태 -> 로그인 출력  -->
			<c:if test="${login == null}">
				<a href="user/login"><h3 class="box-title">로그인</h3></a>
			</c:if>
			<!-- session으로 전달한 login 값이 null이 아니면 로그인 된 상태 -> 로그아웃 출력 -->
			<c:if test="${login != null}">
				<a href="user/logout"><h3 class="box-title">로그아웃</h3></a>
			</c:if>
           
		</div>

		<!-- 1. 게시판 글쓰기 구현 -->
		<div class="box-header with-border">
			<a href="board/register"><h3 class="box-title">게시판 글쓰기</h3></a>
		</div>
		<!-- 2. 게시판 목록 보기 구현 -->
		<div class="box-header with-border">
			<a href="board/list"><h3 class="box-title">게시판 목록보기</h3></a>
		</div>
	</div>
</section>
<!-- footer.jsp 파일의 내용을 가져와서 삽입 -->
<%@include file="include/footer.jsp"%>