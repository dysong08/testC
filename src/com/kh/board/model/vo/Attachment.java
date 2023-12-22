package com.kh.board.model.vo;

import java.sql.Date;

public class Attachment {

//	FILE_NO	NUMBER
//	REF_BNO	NUMBER
//	ORIGIN_NAME	VARCHAR2(255 BYTE)
//	CHANGE_NAME	VARCHAR2(255 BYTE)
//	FILE_PATH	VARCHAR2(1000 BYTE)
//	UPLOAD_DATE	DATE
//	FILE_LEVEL	NUMBER
//	STATUS	VARCHAR2(1 BYTE)
	
	private int fileNo;
	private int refBno;
	private String originName;
	private String changeName;
	private String filePath;
	private Date uploadDate;
	private int fileLevel;
	private String status;
	
	
	public Attachment() {
		
	}


	public Attachment(int fileNo, int refBno, String originName, String changeName, String filePath, Date uploadDate,
			int fileLevel, String status) {
		super();
		this.fileNo = fileNo;
		this.refBno = refBno;
		this.originName = originName;
		this.changeName = changeName;
		this.filePath = filePath;
		this.uploadDate = uploadDate;
		this.fileLevel = fileLevel;
		this.status = status;
	}


	public int getFileNo() {
		return fileNo;
	}


	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}


	public int getRefBno() {
		return refBno;
	}


	public void setRefBno(int refBno) {
		this.refBno = refBno;
	}


	public String getOriginName() {
		return originName;
	}


	public void setOriginName(String originName) {
		this.originName = originName;
	}


	public String getChangeName() {
		return changeName;
	}


	public void setChangeName(String changeName) {
		this.changeName = changeName;
	}


	public String getFilePath() {
		return filePath;
	}


	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}


	public Date getUploadDate() {
		return uploadDate;
	}


	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}


	public int getFileLevel() {
		return fileLevel;
	}


	public void setFileLevel(int fileLevel) {
		this.fileLevel = fileLevel;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	@Override
	public String toString() {
		return "Attachment [fileNo=" + fileNo + ", refBno=" + refBno + ", originName=" + originName + ", changeName="
				+ changeName + ", filePath=" + filePath + ", uploadDate=" + uploadDate + ", fileLevel=" + fileLevel
				+ ", status=" + status + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
