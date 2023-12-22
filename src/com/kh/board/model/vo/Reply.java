package com.kh.board.model.vo;

import java.sql.Date;

public class Reply {
	
	
	private int replyNo;
	private String replyContent;
	private int refBoardNo;
	private int replyWriter;
	private Date createDate;
	private String status;
	
	private String userId;

	public Reply() {
		
	}

	public Reply(int replyNo, String replyContent, int refBoardNo, int replyWriter, Date createDate, String status,
			String userId) {
		super();
		this.replyNo = replyNo;
		this.replyContent = replyContent;
		this.refBoardNo = refBoardNo;
		this.replyWriter = replyWriter;
		this.createDate = createDate;
		this.status = status;
		this.userId = userId;
	}

	public int getReplyNo() {
		return replyNo;
	}

	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public int getRefBoardNo() {
		return refBoardNo;
	}

	public void setRefBoardNo(int refBoardNo) {
		this.refBoardNo = refBoardNo;
	}

	public int getReplyWriter() {
		return replyWriter;
	}

	public void setReplyWriter(int replyWriter) {
		this.replyWriter = replyWriter;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Reply [replyNo=" + replyNo + ", replyContent=" + replyContent + ", refBoardNo=" + refBoardNo
				+ ", replyWriter=" + replyWriter + ", createDate=" + createDate + ", status=" + status + ", userId="
				+ userId + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
