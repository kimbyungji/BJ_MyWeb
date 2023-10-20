<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<%@ include file="/include/header.jsp"%>
		<section>
			<div align="center">
				<form action="update.board" method="post" name="regform">
					<h2>게시판 글수정 페이지</h2>
					<table border="1" width="500">
						<tr>
							<td>글번호</td>
							<td><input type="text" name="num" value="${vo.num }" readonly></td>
						</tr>
						<tr>
							<td>작성자</td>
							<td><input type="text" name="writer" value="${vo.writer }" readonly></td>
						</tr>
						<tr>
							<td>글제목</td>
							<td><input type="text" name="title" value="${vo.title }"></td>
						</tr>
						<tr>
							<td>글내용</td>
							<td>
								<textarea rows="10" style="width:100%;" name="content">${vo.content }</textarea>
							</td>
						</tr>
						<tr>
							<!-- 글등록 메뉴 -->
							<td colspan="2" align="center">
								<input type="button" value="수정하기" onclick="modifyCheck()" >
								<input type="button" value="목록" onclick="location.href='list.board?pageNum=${param.pageNum}'">
								<input type="button" value="삭제하기" onclick="location.href='delete.board?num=${vo.num}'" >
								
							</td>
						</tr>
					</table>
				</form>
			</div>
		</section>
		
		<script>
			function modifyCheck(){

				if(document.regform.title.value === ""){
					alert('글제목을 입력하세요.');
					return;
				}else if(confirm("게시글을 수정하시겠습니까?")){
					document.regform.submit();
				}
				
			}
		</script>
	<%@ include file="/include/footer.jsp"%>