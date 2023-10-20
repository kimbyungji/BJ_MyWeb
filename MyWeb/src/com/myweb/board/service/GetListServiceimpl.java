package com.myweb.board.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myweb.board.model.BoardDAO;
import com.myweb.board.model.BoardVO;
import com.myweb.util.Criteria;
import com.myweb.util.pageVO;

public class GetListServiceimpl implements IBoardService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
//		// DAO객체 생성
//		BoardDAO dao = BoardDAO.getInstance();
//		List<BoardVO> list = dao.getList();	// 목록 조회 결과를 list형태로 반환
//		
//		// DB로부터 받은 데이터를 저장(request에 강제 저장)
//		request.setAttribute("list", list);
		
		// 페이징 처리
		// DAO객체 생성
		BoardDAO dao = BoardDAO.getInstance();
		
		// Criteria 객체 생성
		Criteria cri = new Criteria();
		
		// 페이지 값을 받아 처리하는 동작이 존재함
//		cri.setPageNum(2);
//		System.out.println(request.getParameter("pageNum"));
		if(request.getParameter("pageNum")!=null) {
			String pageNum = request.getParameter("pageNum");
			cri.setPageNum(Integer.parseInt(pageNum));
		}
		
		List<BoardVO> list = dao.getList(cri);
		
		request.setAttribute("list", list);
		
		// 화면에 보여질 페이지 버튼을 계산 처리
		// 1. 총 게시글
		int total = dao.getTotal();
		
		pageVO vo = new pageVO(total, cri);
		
		request.setAttribute("pageVO", vo);

	}

}
