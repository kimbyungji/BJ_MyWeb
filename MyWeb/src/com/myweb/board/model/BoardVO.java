package com.myweb.board.model;

import java.sql.Timestamp;

public class BoardVO {
	private int num;
	private String writer;
	private String title;
	private String content;
	private Timestamp regdate;
	private int hit;
	private String file;
	
	// 기본 생성자
	public BoardVO() {}

	public BoardVO(int num, String writer, String title, String content, Timestamp regdate, int hit, String file) {
		super();
		this.num = num;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.regdate = regdate;
		this.hit = hit;
		this.file = file;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getRegdate() {
		return regdate;
	}

	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}
	
	

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	@Override
	public String toString() {
		return "BoardVO [num=" + num + ", writer=" + writer + ", title=" + title + ", content=" + content + ", regdate="
				+ regdate + ", hit=" + hit + ", file=" + file + "]";
	}
	
}
