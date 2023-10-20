<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<%@ include file="/include/header.jsp" %>
		<section>
			<div class="container">
				<h3>MyWeb 게시판</h3>
				
				<table class="table table-bordered">
					<!-- 게시글 -->
					<thead>
						<tr>
							<th>글 번호</th>
							<th>작성자</th>
							<th>제목</th>
							<th>날짜</th>
							<th>조회수</th> 
						</tr>
					</thead>
					<tbody>
						<c:forEach var="vo" items="${list }">
							<tr>
								<td>${vo.num }</td>
								<td>${vo.writer }</td>
								<td>
									<a href="content.board?num=${vo.num }&pageNum=${pageVO.pageNum}">${vo.title }</a>
								</td>
								<td>${vo.regdate }</td>
								<td>${vo.hit }</td>
							</tr>
						</c:forEach>	
					</tbody>
					<!-- 작성글 검색 및 글작성 메뉴 -->
					<tbody>
						<tr>
							<td colspan="5" align="right">
								<form action="" class="form-inline">
									<div class="form-group">
										<input type="text" name="search" placeholder="제목 검색" class="form-control">
										<input type="submit" value="검색" class="btn btn-default">
										<input type="button" value="글작성" class="btn btn-primary" onclick="location.href='write.board'">
									</div>
								</form>
							</td>
						</tr>
					</tbody>
				</table>
				<!-- 페이지 작업하는 공간 -->
				<div align="center">
					<ul class="pagination pagination-sm">
						<!-- 이전 버튼 활성화 여부 -->
						<c:if test="${pageVO.prev }">
							<li><a href="list.board?pageNum=${pageVO.startPage-1 }">이전</a></li>
						</c:if>
						<!-- 1. 페이지 번호 처리 -->
						<c:forEach var="num" begin="${pageVO.startPage }" end="${pageVO.endPage }">
						<li class="${num == pageVO.pageNum ? 'active':'' }">
						<a href="list.board?pageNum=${num }">${num }</a>
						</li>
						</c:forEach>
						<!-- 이전 버튼 활성화 여부 -->
						<c:if test="${pageVO.next }">
							<li><a href="list.board?pageNum=${pageVO.endPage+1 }">다음</a></li>
						</c:if>
					</ul>
				</div>
			</div>
		</section>
<%@ include file="/include/footer.jsp" %>