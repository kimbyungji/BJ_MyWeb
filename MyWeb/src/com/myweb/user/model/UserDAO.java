package com.myweb.user.model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.web.util.JdbcUtil;

public class UserDAO {
	
	/*
	 * DAO은 싱글톤 방식으로 작성
	 * 왜? 여러개 생성되지 않게 하기 위해( 메모리 과부하 문제를 해결하기 위해서... )  
	 * 싱글톤으로 객체 1개로 제한
	 */
	
	//1. 스스로 객체를 멤버변수로 선언하고 1개로 제한
	private static UserDAO instance = new UserDAO();
	
	// Connection Pool을 위한 객체 생성
		private DataSource ds; //데이터 소스 객체 생성
		private Context ct; //javax.naming.*
	
	//2. 외부에서 객체를 생성할 수 없도록 생성자에 private
	private UserDAO() {
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//		} catch (Exception e) {
//			System.out.println("드라이버 로드 에러");
//		}
		try {
			ct = new InitialContext();//이니셜 컨텍스트 객체 생성
			ds = (DataSource)ct.lookup("java:comp/env/jdbc/mysql");//이니셜컨텍스트로부터 찾기
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//3. 외부에서 객체를 요구할 때 getter 메서드만 써서 반환
	public static UserDAO getInstance() {
		return instance;
	}
	
	// ----------------- 중복된 변수 및 객체를 작성(멤버 변수로)----------------
//	private String url = "jdbc:mysql://localhost:3306/test?serverTimezone=Asia/Seoul";
//	private String user = "mytest";
//	private String password = "mytest";
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	//id 검증 메서드
	public int idConfirm(String id) {
		int result = 0;
		
		String sql = "select * from users where id = ?";
		
		try {
			//conn = DriverManager.getConnection(url, user, password);
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) result = 1;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		
		return result;
	}
	
	//회원 가입 메서드
	public int join(UserVO vo) {
		int result = 0;
		
		String sql = "insert into users (id, pw, name, email, address) "
				+ "values(?, ?, ?, ?, ?)";
		
		try {
			//conn = DriverManager.getConnection(url, user, password);
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPw());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getEmail());
			pstmt.setString(5, vo.getAddress());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		
		return result;
	}
	
	//로그인 메서드
	public int login(String id, String pw) {
		int result = 0;
		
		String sql = "select * from users where id=? and pw=?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			
			rs = pstmt.executeQuery();
			if(rs.next()) result= 1;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		
		
		return result;
		
	}
	
	//회원정보 획득 메서드
	public UserVO getUserInfo(String id) {
		UserVO vo = null;
				
		String sql = "select * from users where id=?";
		try {
			conn= ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String id1 = rs.getString("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String address = rs.getString("address");
				Timestamp regdate = rs.getTimestamp("regdate");
				
				vo = new UserVO(id1, null, name, email, address, regdate);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
	
		return vo;
	}
	
	public int change(String id,String new_pw) {
		int result = 0;
		
		String sql = "update users set pw = ? where id = ?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, new_pw);
			pstmt.setString(2, id);
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		
		return result;
	}
	
	public String get_pw(String id) {
		String pw = null;
			
			String sql = "select pw from users where id=?";
			
			try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					pw = rs.getString("pw");
				}							
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				JdbcUtil.close(conn, pstmt, rs);
			}
		
		return pw;
	}
	
	// 패스워드 변경 메서드
	public int changePassword(String id, String pw) {
		int result = 0;
		
		String sql = "update users set pw=? where id = ?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pw);
			pstmt.setString(2, id);
			
			result = pstmt.executeUpdate();									
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		
		return result;
	}
	public int update(UserVO vo) {
		int result = 0;
		String sql = "update users set name=?, email=?, address=? where id=? ";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getAddress());
			pstmt.setString(4, vo.getId());
			
			result = pstmt.executeUpdate();									
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		
		
		return result;
	}
	// 회원 탈퇴 메서드
	public int delete(String id) {
		int result=0;
			String sql = "delete from users where id = ?";
			
			try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				
				result = pstmt.executeUpdate();
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				JdbcUtil.close(conn, pstmt, rs);
			}
		return result;
	}
	
}
