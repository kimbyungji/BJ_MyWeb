<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<%@ include file="/include/header.jsp"%>
		<section>
			<div align="center">
				<form action="register.board" method="post" name="regform">
					<h2>게시판 글작성 페이지</h2>
					<table border="1" width="500">
						<tr>
							<td>작성자</td>
							<td><input type="text" name="writer" size="10"></td>
						</tr>
						<tr>
							<td>글제목</td>
							<td><input type="text" name="title"></td>
						</tr>
						<tr>
							<td>글내용</td>
							<td>
								<textarea rows="10" style="width:100%;" name="content"></textarea>
							</td>
						</tr>
						<tr>
							<!-- 글등록 메뉴 -->
							<td colspan="2" align="center">
								<input type="button" value="등록" onclick="registCheck()" >
								<input type="button" value="목록" onclick="location.href='list.board'">
							</td>
						</tr>
					</table>
				</form>
			</div>
		</section>
		
		<script>
			function registCheck(){
				if(document.regform.writer.value === ""){
					alert('작성자를 입력하세요.');
					return;
				}else if(document.regform.title.value === ""){
					alert('글제목을 입력하세요.');
					return;
				}else if(confirm("게시글을 등록하시겠습니까?")){
					document.regform.submit();
				}
				
			}
		</script>
	<%@ include file="/include/footer.jsp"%>