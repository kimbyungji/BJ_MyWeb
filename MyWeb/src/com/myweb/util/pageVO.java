package com.myweb.util;

public class pageVO {
	// 페이징 계산 처리 클래스
	// 1. total : 게시판 글 전체 갯수
	// 2. endPage : 게시판을 화면에 보여질 마지막 페이지 번호
	// 3. startPage : 게시판 화면에 보여질 첫번 째 페이지 번호
	// 4. realEnd : 게시판의 실제 마지막 페이지 번호
	// 5. prev : 이전 페이지 버튼 활성화 여부
	// 6. next : 다음 페이지 버튼 활성화 여부
	
	// 화면에 그려질 버튼들의 갯수를 계산하는 클래스
	private int startPage;	// 시작번호
	private int endPage;	// 끝번호
	private int total;		// 전체 게시글 수
	
	private int pageNum;	// 현재 조회하는 페이지
	
	private boolean next;	// 다음 버튼 활성화 여부
	private boolean prev;	// 이전 버튼 활성화 여부
	
	private Criteria cri;	// 페이징 기준
	
	// 기본 생성자로 생성X, total과 Criteria를 받아서 계산하도록 선언
	public pageVO(int total, Criteria cri) {
		// 전달되는 매개변수에서 초기값을 설정
		this.total = total;
		this.cri = cri;
		this.pageNum = cri.getPageNum();
		
		// 끝 페이지 계산 - endPage
		// 1 ~ 10 => 10, 11 ~ 20 => 20
		// Math.ceil 올림 메서드
		// 계산식= (int)Math.ceil(조회하는 페이지 번호/(double)10)*10
		this.endPage = (int)Math.ceil(cri.getPageNum()/(double)10)*10;
		
		// 시작 페이지 계산
		// 계산식 = endPage -9(표시할 페이지 수 -1);
		this.startPage = endPage-9;
		
		// RealEnd 페이지	(int)Math.ceil(총 게시물 수/(double)페이지당 게시글 수);
		int realEnd = (int)Math.ceil(this.total/(double)cri.getCount());
		
		// 마지막 페이지 세팅에서 보여줘야 하는 번호...
		if(this.endPage>realEnd) {
			this.endPage = realEnd;
		}
		// 이전 페이지 버튼
		// startPage => 1, 11, 21, 31, ...
		this.prev = startPage > 1;
		
		// 다음 페이지 버튼
		this.next = realEnd > this.endPage;
	}
	
	// getter, setter
	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public Criteria getCri() {
		return cri;
	}

	public void setCri(Criteria cri) {
		this.cri = cri;
	}
	
}
