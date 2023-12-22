package com.kh.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Attachment;
import com.kh.board.model.vo.Board;

/**
 * Servlet implementation class thumbnailDetailController
 */
@WebServlet("/detail.th")
public class thumbnailDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public thumbnailDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 조회수 증가처리
		int boardNo = Integer.parseInt(request.getParameter("bno"));
		int result = new BoardService().increaseCount(boardNo); // 조회수증가서비스
		
		String url = " ";
		if(result > 0) {
			// 증가되었다면
			Board b = new BoardService().selectBoard(boardNo);
			
			ArrayList<Attachment> list = new BoardService().selectAttachmentList(boardNo);
			
			System.out.println("b : " + b);
			System.out.println("list : " + list);
			
			request.setAttribute("b", b);
			request.setAttribute("list", list);
			url = "views/board/thumbnailDetailView.jsp";
			
		}else {
			request.setAttribute("errorMsg", "사진 게시글 조회 실패!");
			url = "views/common/errorPage.jsp";
			
		}
		request.getRequestDispatcher(url).forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
