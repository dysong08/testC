package com.kh.board.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.board.model.dao.BoardDao;
import com.kh.board.model.vo.Attachment;
import com.kh.board.model.vo.Board;
import com.kh.board.model.vo.Category;
import com.kh.board.model.vo.Reply;
import com.kh.common.model.vo.PageInfo;

import static com.kh.common.JDBCTemplate.*;

public class BoardService {

	
	public int selectListCount() {
		
		Connection conn = getConnection();
		int listCount = new BoardDao().selectListCount(conn);
		
		close(conn);
		return listCount;
	}

	
	
	public ArrayList<Board> selectList(PageInfo pi) {
		
		Connection conn = getConnection();
		ArrayList<Board> list = new BoardDao().selectList(conn, pi);
		
		close(conn);
		
		return list;
	}



	public int increaseCount(int boardNo) {
		
		Connection conn = getConnection();
		int result = new BoardDao().increaseCount(conn, boardNo);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}



	public Board selectBoard(int boardNo) {
		
		Connection conn = getConnection();
		Board b = new BoardDao().selectBoard(conn, boardNo);
		
		close(conn);
		
		return b;
	}



	public Attachment selectAttachment(int boardNo) {
		
		Connection conn = getConnection();
		Attachment at = new BoardDao().selectAttachment(conn, boardNo);
		
		close(conn);
		
		return at;
	}



	public ArrayList<Category> selectCategoryList() {
		
		Connection conn = getConnection();
		ArrayList<Category> list = new BoardDao().selectCategoryList(conn);
		
		close(conn);
		
		return list;
	}



	public int insertBoard(Board b, Attachment at) {
		
		Connection conn = getConnection();
		
		int result1 = new BoardDao().insertBoard(conn, b);
		int result2 = 1;
		
		if(at != null) {
			result2 = new BoardDao().insertAttachment(conn, at);
		}
		
		// 트랜잭션 처리
		if(result1 > 0 && result2 > 0) {
			// 둘다 등록성공
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		
		return result1*result2;
		// 둘중 하나라도 실패시 0반환
	}



	public int updateBoard(Board b, Attachment at) {
		
		Connection conn = getConnection();
		
		int result1 = new BoardDao().updateBoard(conn, b);
		int result2 = 1;
		
		if(at != null) {
			// 새롭게 첨부된 파일이 있을 경우
			
			if(at.getFileNo() != 0) {
				// 기존에 첨부파일이 있었을 경우 
				result2 = new BoardDao().updateAttachment(conn, at);
			} else {
//				if(at.getRefBno() != 0) 
				// 기존에 첨부파일이 없었을 경우(Insert)
				result2 = new BoardDao().insertNewAttachment(conn, at);
						
			}
		}
		if(result1 > 0 && result2 > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result1 * result2;
	}



	public int deleteBoard(int boardNo) {
		
		Connection conn = getConnection();
		
		int result = new BoardDao().deleteBoard(conn, boardNo);
		
		new BoardDao().deleteAttachment(conn, boardNo);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}



	public ArrayList<Board> selectThumbnailList() {
		
		Connection conn = getConnection();
		
		ArrayList<Board> list = new BoardDao().selectThumbnailList(conn);
		
		close(conn);
		
		return list;
	}



	public int insertThumbnailBoard(Board b, ArrayList<Attachment> list) {
		
		Connection conn = getConnection();
		
		int result1 = new BoardDao().insertThumbnailBoard(conn, b);
		int result2 = new BoardDao().insertAttachmentList(conn, list);
		// 일반게시판에서 첨부파일은 선택사항이기 때문에 1로 초기화 했지만
		// 사진게시판에서는 1개이상 필수이기 때문에..
		
		if(result1 > 0 && result2 > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		
		return result1 * result2;
		
	}



	public ArrayList<Attachment> selectAttachmentList(int boardNo) {
		
		Connection conn = getConnection();
		
		ArrayList<Attachment> list = new BoardDao().selectAttachmentList(conn, boardNo);
		
		close(conn);
		
		return list;
	}



	public int insertReply(Reply r) {
		
		Connection conn = getConnection();
		
		int result = new BoardDao().insertReply(conn, r);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
				
		close(conn);
		
		return result;
	}



	public ArrayList<Reply> selectReplyList(int boardNo) {
		
		Connection conn = getConnection();
		
		ArrayList<Reply> list = new BoardDao().selectReplyList(conn, boardNo);
		
		close(conn);
		
		return list;
	}

	
	
	
	
	
	
	
	
	
	
}
