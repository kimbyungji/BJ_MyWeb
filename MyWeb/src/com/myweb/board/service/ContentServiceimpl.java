package com.myweb.board.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myweb.board.model.BoardDAO;
import com.myweb.board.model.BoardVO;

public class ContentServiceimpl implements IBoardService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		/*
		 * 1. DAO에 getCotent(num) 메서드를 생성하고, 호출
		 * 2. getContent() 메서드에는 num을 가지고, 게시글에 대한 정보를 조회 vo에 담아서 반환
		 * 3. 메서드 리턴 타입 BoardVO
		 * 4. 화면 전송을 위해서 리턴 타입의 값을 vo라는 이름으로 강제 저장
		 */
		String num = (String)request.getParameter("num");
		
		BoardDAO dao = BoardDAO.getInstance();
		
		// 3. 쿠키는 서버로 전송될 때 자동으로 request에 담아져서 들어옴. 쿠카 검사
		Cookie[] arr = request.getCookies();
		
		// 조회수 업데이트 : dao.upHit(num)
		
		// 검증 후 조회스 업데이트 처리
		boolean bool = true;
		for(Cookie c : arr) {
			if(c.getName().equals("hitNum"+num)) {	// 쿠키 있으면 이미 가지고 있다면...
				bool = false;
				break;
				
			}
		}
		if(bool) dao.upHit(num);	// bool 값이 true라면 읽은 적 없음, false면 읽은 적 있음		
		
		BoardVO vo = dao.getContent(num);
		
		request.setAttribute("vo", vo);
		
		// 2. 중복 증가 방지를 위한 쿠키 생성
		Cookie hitCoo = new Cookie("hitNum"+num, num);	// num은 게시글 번호
		hitCoo.setMaxAge(60);
		response.addCookie(hitCoo);
		
	}

}
