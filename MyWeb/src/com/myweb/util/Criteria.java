package com.myweb.util;

public class Criteria {
	// select * from board order by num desc limit ?(시작위치), ?(갯수);
	
	private int pageNum; // 페이지 번호
	private int count;	// 페이지 당 게시물 수
	
	public Criteria() {
		// 최초 게시판에 진입할 때 기본값 1번 페이지, 10개를 데이터로 세팅
		this.pageNum = 1;
		this.count = 10;
	}

	public Criteria(int pagNum, int count) {
		// 매개변수를 전달받아 페이지 출력 값을 계산 처리하게 함
		this.pageNum = pagNum;
		this.count = count;
	}
	
	public int getPageStart() {	// limit ?(*), ?(count)?
		return (pageNum-1)*count;
	}
	
	public int getPageNum() {	
		return pageNum;
	}

	public void setPageNum(int pagNum) {
		this.pageNum = pagNum;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	
}
