package com.myweb.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.myweb.board.service.ContentServiceimpl;
import com.myweb.board.service.DeleteServiceImpl;
import com.myweb.board.service.GetListServiceimpl;
import com.myweb.board.service.IBoardService;
import com.myweb.board.service.RegistServiceimpl;
import com.myweb.board.service.UpdateServiceImpl;


@MultipartConfig(
		fileSizeThreshold = 1024*1024,
		maxFileSize = 1024*1024*50,
		maxRequestSize = 1024*1024*50*5
)

@WebServlet("*.board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public BoardController() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}
	
	// .board로 끝나는 요청들은 이 컨트롤러로 들어오게 설정...
	// 1. get, post 방식으로 요청시 하나의 메서드로 연결 -> doAction()
	// 2. 컨텍스트 apth를 제거 /board/list.board 요청으로 들어오면 board_list(view)화면으로 이동
	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 컨트롤러로 들어온 요청 명령어를 구분해서 처리
		request.setCharacterEncoding("utf-8");
		
		String uri = request.getRequestURI();
		String path = request.getContextPath();
		String command = uri.substring(path.length());
		System.out.println(command);
		
		// 서비스 객체를 생성
		IBoardService service = null;
		
		// command에 따른 동작 구현....
		if(command.equals("/board/list.board")) {
			// 게시글 목록 데이터를 전달 작업해야 함
			service = new GetListServiceimpl();
			service.execute(request, response);
			
			// GetListServiceImpl로 전달 받은 request 강제 저장된 list를 포워드 처리...
			RequestDispatcher dp = request.getRequestDispatcher("board_list.jsp");
			dp.forward(request, response);
		}else if(command.equals("/board/write.board")) {
			// 글 작성 페이지(view)로 이동
			response.sendRedirect("board_write.jsp");
		}else if(command.equals("/board/register.board")) {
			// 파일 작업------------------------------------------------------------
			Part filePart = request.getPart("file");
			String fileName = filePart.getSubmittedFileName();
			InputStream fis = filePart.getInputStream();
			String realPath = request.getServletContext().getRealPath("/img");
			System.out.println(fileName);
			System.out.println(realPath);
			
			String filePath = realPath + File.separator + fileName;
			System.out.println(filePath);
			FileOutputStream fos = new FileOutputStream(filePath);
			
			byte[] buf = new byte[1024];
			int size= 0 ;
			while((size=fis.read(buf)) != -1) {
				fos.write(buf,0,size);
							
			}
			fos.close();
			fis.close();
			//-------------------------------------------------------------------
			// 서비스 객체를 생성
			service = new RegistServiceimpl();
			service.execute(request, response);
			
			//목록으로 이동
			response.sendRedirect("list.board"); // 다시 컨트롤러로 이동
		}else if(command.equals("/board/content.board")) {
			
			service = new ContentServiceimpl();
			service.execute(request, response);
			
			// 내용 출력
			RequestDispatcher dp = request.getRequestDispatcher("board_content.jsp");
			dp.forward(request, response);
			
		}else if(command.equals("/board/modify.board")) {
			service = new ContentServiceimpl();
			service.execute(request, response);
			
			// 내용 출력
			RequestDispatcher dp = request.getRequestDispatcher("board_modify.jsp");
			dp.forward(request, response);
		}else if(command.equals("/board/update.board")) {
			// form으로부터 정보 받고, UpdateServiceImple...
			/*
			 * 1. UpdateServiceImpl을 생성
			 * 2. 서비스 영역에서 num, title, content를 받아서 update() 메서드(DAO) 실행
			 * 3. DAO에서 update() 메서드 생성. SQL의 update구문을 사용하여 데이터 수정
			 * 4. 페이지 이름이 상세보기인 화면으로(content.board)으로 설정
			 *	(주의 사항, 필요한 값을 전달해야 함)
			 *	sql = "update board set title=?, content=? where num=?;
			 */
			service = new UpdateServiceImpl();
			service.execute(request, response);
			
			String num = request.getParameter("num");
			
			response.sendRedirect("content.board?num="+num);
			
		}else if(command.equals("/board/delete.board")) {
			/*
			 * 1. DeleteServiceImpl을 생성
			 * 2. 서비스 영역에서 num을 받아서 delete()메서드를 실행
			 * 3. DAO에 있는 delete()에서 delete구문 실행
			 * 4. 페이지 이동을 목록으로 이동
			 * 
			 *  sql = "delete from board where num = ?";
			 */
			service = new DeleteServiceImpl();
			service.execute(request, response);
			
			response.sendRedirect("list.board");
			
		}
	}

}
