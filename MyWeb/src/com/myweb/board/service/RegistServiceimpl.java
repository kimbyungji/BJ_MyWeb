package com.myweb.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myweb.board.model.BoardDAO;

public class RegistServiceimpl implements IBoardService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// 폼에서 전달된 값을 처리...
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		System.out.println("["+writer+", "+title+", "+content+"]");
		// DAO객체 생성
		// DAO 객체에 등록 메서드 regist 메서드를 호출
		BoardDAO dao = BoardDAO.getInstance();	
		dao.regist(writer,title,content);

	}

}
