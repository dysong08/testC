package com.kh.board.model.vo;

import java.sql.Date;

public class Board {

//	BOARD_NO	NUMBER
//	BOARD_TYPE	NUMBER
//	CATEGORY_NO	NUMBER
//	BOARD_TITLE	VARCHAR2(100 BYTE)
//	BOARD_CONTENT	VARCHAR2(4000 BYTE)
//	BOARD_WRITER	NUMBER
//	COUNT	NUMBER
//	CREATE_DATE	DATE
//	STATUS	VARCHAR2(1 BYTE)
	
	private int boardNo;
	private int boardType;
	private int categotyNo;
	private String boardTitle;
	private String boardContent;
	private String boardWriter;	
	// 쿼리문 작성시 회원번호를 활용하여 join예정,
	// 조회결과 작성한 사용자의 이름 or id를 저장하기 위한 필드
	private int count;
	private Date creatDate;
	private String status;
	
	private String categoryName;
	// Board테이블에 존재하지 않는 칼럼 (CATEGORY테이블의 칼럼)
	
	private Attachment attachment;
	
	public Board() {
		
	}

	
	public static class Builder{
		
		private int boardNo;
		private int boardType;
		private int categotyNo;
		private String boardTitle;
		private String boardContent;
		private String boardWriter;	
		private int count;
		private Date creatDate;
		private String status;
		private String categoryName;
		private Attachment attachment;
		
		
		public Board build() {
			Board b = new Board();
			b.boardNo = boardNo;
			b.boardType = boardType;
			b.categotyNo = categotyNo;
			b.boardTitle = boardTitle;
			b.boardContent = boardContent;
			b.boardWriter = boardWriter;
			b.count = count;
			b.creatDate = creatDate;
			b.status = status;
			b.categoryName = categoryName;
			b.attachment = attachment;
			
			return b;
		}
		
		public Builder boardNo(int boardNo) {
			this.boardNo = boardNo;
			return this;
		}
		
		public Builder boardType(int boardType) {
			this.boardType = boardType;
			return this;
		}
		
		public Builder categotyNo(int categotyNo) {
			this.categotyNo = categotyNo;
			return this;
		}
		
		public Builder boardTitle(String boardTitle) {
			this.boardTitle = boardTitle;
			return this;
		}
		
		public Builder boardContent(String boardContent) {
			this.boardContent = boardContent;
			return this;
		}
		
		public Builder boardWriter(String boardWriter) {
			this.boardWriter = boardWriter;
			return this;
		}
		
		public Builder count(int count) {
			this.count = count;
			return this;
		}
		
		public Builder creatDate(Date creatDate) {
			this.creatDate = creatDate;
			return this;
		}
		
		public Builder status(String status) {
			this.status = status;
			return this;
		}
		
		public Builder categoryName(String categoryName) {
			this.categoryName = categoryName;
			return this;
		}
		
		public Builder attachment(Attachment attachment) {
			this.attachment = attachment;
			return this;
		}
		
	}
	
	

	public Attachment getAttachment() {
		return attachment;
	}


	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}


	public int getBoardNo() {
		return boardNo;
	}


	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}


	public int getBoardType() {
		return boardType;
	}


	public void setBoardType(int boardType) {
		this.boardType = boardType;
	}


	public int getCategotyNo() {
		return categotyNo;
	}


	public void setCategotyNo(int categotyNo) {
		this.categotyNo = categotyNo;
	}


	public String getBoardTitle() {
		return boardTitle;
	}


	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}


	public String getBoardContent() {
		return boardContent;
	}


	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}


	public String getBoardWriter() {
		return boardWriter;
	}


	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}


	public int getCount() {
		return count;
	}


	public void setCount(int count) {
		this.count = count;
	}


	public Date getCreatDate() {
		return creatDate;
	}


	public void setCreatDate(Date creatDate) {
		this.creatDate = creatDate;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getCategoryName() {
		return categoryName;
	}


	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}


	@Override
	public String toString() {
		return "Board [boardNo=" + boardNo + ", boardType=" + boardType + ", categotyNo=" + categotyNo + ", boardTitle="
				+ boardTitle + ", boardContent=" + boardContent + ", boardWriter=" + boardWriter + ", count=" + count
				+ ", creatDate=" + creatDate + ", status=" + status + ", categoryName=" + categoryName + ", attachment="
				+ attachment + "]";
	}


	
	
	
	
	
	
	
	
}
