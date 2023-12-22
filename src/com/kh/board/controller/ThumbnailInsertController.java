package com.kh.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Attachment;
import com.kh.board.model.vo.Board;
import com.kh.common.MyFileRenamePolicy;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class ThumbnailInsertController
 */
@WebServlet("/insert.th")
public class ThumbnailInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ThumbnailInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("views/board/thumbnailEnrollForm.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		if(ServletFileUpload.isMultipartContent(request)) {
			
			// 용량제한
			int maxSize = 20 * 1024*1024;
			
			// 저장할 파일의 물리적인 경로
			String savePath = request.getSession()
						.getServletContext().getRealPath("/resources/thumbnail_upfiles/");
			
			
			MultipartRequest multi = new MultipartRequest(
					request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			
			// DB에 전달할 데이터 추출
			// BOARD => 게시글작성자, 제목, 내용
			Board b = new Board.Builder()
						.boardWriter(multi.getParameter("userNo"))
						.boardTitle(multi.getParameter("boardTitle"))
						.boardContent(multi.getParameter("boardContent"))
						.build();
			
			// ATTACHMENT 테이블에 여러번 insert할 데이터뽑기(최소 1번이상)
			ArrayList<Attachment> list = new ArrayList();
			
			for(int i = 1; i<=4; i++) {	// 4회반복
				
				// file1, file2, file3, file4
				String key = "file" + i;
				
				if(multi.getOriginalFileName(key) != null) {
					// 첨부파일이 있을 경우
					// Attachment객체 생성 + 원본명, 수정명, 저장경로, 파일레벨 담아줄 예정
					Attachment at = new Attachment();
					at.setOriginName(multi.getOriginalFileName(key));
					at.setChangeName(multi.getFilesystemName(key));
					at.setFilePath("resources/thumbnail_upfiles/");
					
					if(i == 1) {
						at.setFileLevel(1);
					}else {
						at.setFileLevel(2);
					}
					
					list.add(at);
				}
				
			}
			
			int result = new BoardService().insertThumbnailBoard(b, list);
			
			if(result > 0) {
				// 성공시에 list.th 재요청
				request.getSession().setAttribute("alertMsg", "업로드 완료!");
				response.sendRedirect("list.th");
			}else {
				// 실패시 
				request.setAttribute("errorMsg", "업로드 실패!");
				request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
			}
		}
		
	}

}
