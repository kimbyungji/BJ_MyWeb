package com.myweb.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myweb.board.model.BoardDAO;

public class UpdateServiceImpl implements IBoardService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String num = (String)request.getParameter("num");
		String title = (String)request.getParameter("title");
		String content = (String)request.getParameter("content");
		String file = (String)request.getParameter("file");
		BoardDAO dao = BoardDAO.getInstance();
		dao.update(num,title,content,file);

	}

}
