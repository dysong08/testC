package com.kh.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Board;
import com.kh.common.model.vo.PageInfo;

/**
 * Servlet implementation class boardListController
 */
@WebServlet("/list.bo")
public class boardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public boardListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// ------------------------ 페이징처리 -----------------------
		int listCount;	// 게시글 갯수
		int currentPage;	// 현재 요청한 페이지
		int pageLimit;	// 페이지바 하단에 보여질 페이징바의 페이지 최대 갯수
		int boardLimit;	// 한 페이지에 보여질 게시글의 최대 갯수
		
		int maxPage;	// 가장 마지막 페이지가 몇번 페이지인지(총 페이지 수)
		int startPage;	// 페이지 하단에 보여질 페이징바의 시작수
		int endPage;	// 페이징 하단에 보여질 페이징바의 끝수
		
		
		// * listCount : 총 개시글 갯수
		listCount = new BoardService().selectListCount();
		
		// * currentPage : 사용자가 요청한 페이지 -> 처음엔 선택한 값이 없기때문에(null) 1번으로 기본값설정함
		currentPage = Integer.parseInt(request.getParameter("currentPage") == null ? 
				"1" : request.getParameter("currentPage"));
		
		// * pageLimit : 한 페이지에 보여질 페이징바의 최대개수
		pageLimit = 10;
		
		// * boardLimit : 한 페이지에 보여질 게시글의 최대개수
		boardLimit = 10;
		
		
		// * maxPage : 가장 마지막 페이지가 몇번 페이지인지(총 페이지 수)
		/*
			maxPage => listCount, boardLimit에 영향을 받음
			
			- 공식 구하기 (boardLimit가 10이라는 가정)
			
			총 개수				boardLimit				maxPage
			2000개		/			10개			/		200번 페이지
			2001개		/			10개			/		200.1번 페이지 => 올림처리(201페이지)
			2005개										200.5번 페이지 => 201페이지
			2010개										201번 페이지
			2011개										201.1번 페이지 => 올림처리(202페이지)
			-> 니눗셈 연산 결과를 올림처리하면 maxPage값이 나온다.
			
			1) listCount를 double로 형변환
			2) listCount / boardLimit
			3) 결과에 올림처리 후
			4) 결과값을 int로 형변환
		*/
		maxPage = (int)Math.ceil( (double)listCount / boardLimit);
				
		
		// * startPage : 페이지 하단에 보여질 페이징바의 시작수
		/*
			startPage => pageLimit, currentPage에 영향을 받음
			
			 - 공식 구하기 (pageLimit가 10이라는 가정)
			 
			 startPage : 1, 11, 21, 31, ... n => n*10+1 
			 
			 만약 pageLimit가 5라면 ? 1, 6, 11, 16 .. n => n*5+1
			 
			 즉 => n * pageLimit + 1
			 
			  currentPage 		startPage
			  		1				1
			  		5				1
			  		10				1
			  		
			  		11				11
			  		15				11
			  		20				11
			  		
			  		21				21
		
				1~10  => 1페이지 => n * pageLimit + 1 ==> n = 0
				11~20 => 2페이지 => n * pageLimit + 1 ==> n = 1
				21~30 => 3페이지 => n * pageLimit + 1 ==> n = 2
				
				n = (currentPage -1) / pageLimit 
					0~9   / 10 = 0
					10~19 / 10 = 1
					20~29 / 10 = 2
				startPage = n * pageLimit + 1
				=>  (currentPage -1) / pageLimit * pageLimit + 1
		*/
		startPage = (currentPage -1) / pageLimit * pageLimit + 1;
				
		
		// * endPage : 페이징 하단에 보여질 페이징바의 끝수
		
		/*
			endPage => startPage, pageLimit, maxPage 에 영향을 받음
			
			- 공식 구하기 (pageLimit가 10이라는 가정)
			
			startPage :  1 => 끝수 : 10
			startPage : 11 => 끝수 : 20
			startPage : 21 => 끝수 : 30
			
			endPage = startPage - pageLimit - 1;
		*/
		endPage = startPage + pageLimit - 1;
		
		
		if(endPage > maxPage) {
			// endPage가 maxPage보다 많으면 안됨!!
			endPage = maxPage;
		}
		
		
		
		// 페이지 정보들을 하나의 공간에 담아서 보내기
		// 페이지 정보들을 담아줄 VO클래스가 필요함
		// -> 사진게시판, 공지사항게시판, 그외 추가될 수 있는 게시판에서도 쓰일 것이므로 common패키지에 만들기
		
		// 1. 페이징바 만들때 필요한 객체 생성
		PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit, 
				maxPage, startPage, endPage);
		
		// 2. 현재 사용자가 요청한 페이지에 보여질 게시글 리스트
		ArrayList<Board> list = new BoardService().selectList(pi);
		
		request.setAttribute("pi", pi);
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("views/board/boardListView.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
