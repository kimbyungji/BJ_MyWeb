package com.myweb.board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.myweb.util.Criteria;
import com.myweb.util.JdbcUtil;

public class BoardDAO {
	
	// 디자인 패턴 : Singleton 패턴
	private DataSource ds;
	
	// 1. 스스로 객체를 멤버변수로 선언하고 1개로 제한
	private static BoardDAO instance = new BoardDAO();
	
	// 2. 외부에서 객체를 생성할 수 없게 private로 설정
	private BoardDAO() {
		// 커넥션풀에서 DB연결 객체 정보를 불러옴
		try {
			InitialContext ctx = new InitialContext();	// Context.xml값에 저장된 내용을 불러와 사용하기 위해서 생성한 객체
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/mysql");
		} catch (Exception e) {
			System.out.println("커넥션 풀링 에러 발생");
		}
	}
	// 3. 외부에서 객체를 요구할 때 getter메서드를 통해서 반환

	public static BoardDAO getInstance() {
		return instance;
	}
	// 중복되는 코드를 멤버변수로 선언
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	//------------ DAO 동작 메서드 구현 ...CRUD ------------
	public void regist(String writer,String title,String content, String file) {
				
		String sql = "insert into board(writer, title, content, file) values(?,?,?,?)";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, writer);
			pstmt.setString(2, title);
			pstmt.setString(3, content);
			pstmt.setString(4, file);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		
	}
//	// 게시물 목록 조회 메서드
//	public List<BoardVO> getList(){
//		List<BoardVO> list = new ArrayList<>();
//		
//		String sql = "select * from board order by num desc";
//		
//		try {
//			conn = ds.getConnection();
//			pstmt = conn.prepareStatement(sql);
//			rs = pstmt.executeQuery();
//			
//			// BoardVO에 레코드 정보를 저장하고, 이를 List에 넣기...
//			// rs에는 Query의 결과가 있음
//			
//			while(rs.next()) {
//				BoardVO vo = new BoardVO();
//				vo.setNum(rs.getInt("num"));
//				vo.setWriter(rs.getString("writer"));
//				vo.setTitle(rs.getString("title"));
//				vo.setContent(rs.getString("content"));
//				vo.setRegdate(rs.getTimestamp("regdate"));
//				vo.setHit(rs.getInt("hit"));
//			
//				list.add(vo);
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally {
//			JdbcUtil.close(conn, pstmt, rs);
//		}
//		
//		return list;
//	}
	
	// 페이징 처리 - 게시물 목록 조회 메서드
	
	public List<BoardVO> getList(Criteria cri){
		List<BoardVO> list = new ArrayList<>();
		
		String sql = "select * from board order by num desc limit ?, ?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cri.getPageStart());	// 시작 번호
			pstmt.setInt(2, cri.getCount());		// 페이지당 게시물 수
			rs = pstmt.executeQuery();
			
			// BoardVO에 레코드 정보를 저장하고, 이를 List에 넣기...
			// rs에는 Query의 결과가 있음
			
			while(rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setNum(rs.getInt("num"));
				vo.setWriter(rs.getString("writer"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));;
				vo.setRegdate(rs.getTimestamp("regdate"));
				vo.setHit(rs.getInt("hit"));
				vo.setFile(rs.getString("file"));
			
				list.add(vo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		
		return list;
	}
	
	// 총 게시물 수를 반환하는 메서드
	public int getTotal() {
		int result=0;
		
		String sql = "select count(*) total from board";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt("total");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		
		return result;
	}
		
	public BoardVO getContent(String num) {
		BoardVO vo = new BoardVO();
		String sql = "select * from board where num = ?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			rs = pstmt.executeQuery();
			
			// BoardVO에 레코드 정보를 저장하고, 이를 List에 넣기...
			// rs에는 Query의 결과가 있음
			
			if(rs.next()) {
				vo.setNum(rs.getInt("num"));
				vo.setWriter(rs.getString("writer"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setRegdate(rs.getTimestamp("regdate"));
				vo.setHit(rs.getInt("hit"));
				vo.setFile(rs.getString("file"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		
		return vo;
	}
	public void update(String num, String title, String content, String file) {
		String sql = "update board set title=?, content=? file=? where num=?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setString(3, num);
			pstmt.setString(4, file);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
	}
	public void delete(String num) {
		String sql = "delete from board where num = ?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		
	}
	public void upHit(String num) {
		String sql = "update board set hit= hit+1 where num=?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
	}
	
}