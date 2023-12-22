package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login.me")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
		// 1) 전달값에 한글이 있을 경우 인코딩처리를 해야함
		request.setCharacterEncoding("UTF-8");
		
		// 2) 요청시 전달한 값을 꺼내서 변수에 기록
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		
		// 3) SELETE * FROM MEMBER WHERE USER_ID = ? AND USER_PWD = ? AND STATUS = 'Y'
		Member loginUser = new MemberService().loginMember(userId, userPwd);
	
		// 4) 처리된 결과를 가지고 사용자가 보게될 화면을 지정
//		System.out.println(loginUser);
	
	
		/*
			* 응답페이지에 전달할 값이 있을 경우 값을 어딘가에 담아야 함
				(담을 수 있는 Servlet Scope 내장객체 4종류)
			1) application :application에 담은 데이터는 웹 애플리케이션 전역에서 다 꺼내 쓸 수 있다. 
			2) session : session에 담은 데이터는 모든 jsp와 servlet에서 꺼내 쓸 수 있다.
						한 번 담은 데이터는 직접 지우기전까지, 서버가 멈추기전까지, 브라우저가 종료되기 전까지 사용가능
			3) request : request에 담은 데이터는 해당 request를 포워딩한 jsp에서만 꺼내쓸 수 있다.
			4) page : 해당 jsp에서만 꺼내쓸 수 있다.
			
			* 모든 Servlet Scope에 데이터를 담고자 한다면 .setAttribute("키","밸류");
								데이터를 꺼내고자 한다면 .getAttribude("키");
								데이터를 지우고자 한다면 .removeAttribute("키");
		 */
	
		// 로그인실패
		if(loginUser == null) {
			
			request.setAttribute("errorMsg", "로그인에 실패했습니다.");
			
			RequestDispatcher view =  request.getRequestDispatcher("views/common/errorPage.jsp");
			view.forward(request, response);
			
		}else {
			// 로그인성공 => index페이지로 이동
			// 로그인한 회원의 정보를 사용자가 로그아웃하기 전까지 쓸 수 있도록 session scope에 담아주기
			// Servelt에서 JSP내장객체인 session에 접근하고자 한다면 session객체를 얻어와야 한다.
			HttpSession session;
			session = request.getSession();
			
			session.setAttribute("loginUser", loginUser);
			session.setAttribute("alertMsg", "성공적으로 로그인 되었습니다.");
			
			// 1. 포워딩방식 응답뷰 출력
			// index.jsp
			// index.jsp로 포워딩 되긴 하지만 url에는 servlet 매핑주소가 그대로 남아있고
			// 요청시 전달한 내용들도(request) 그대로 남아있다.
//			RequestDispatcher view = request.getRequestDispatcher("index.jsp");	// localhost:8082/jsp
//			view.forward(request, response);
			// => 새로고침을 해도 계속 로그인요청이 들어감
			// 로그인 후 url => http://localhost:8082/jsp/login.me
			
			
			// 2. url재요청방식(sendRedirect)
			//	/jsp => request.getContextPath()
			response.sendRedirect(request.getContextPath());
			// 로그인 후 url => http://localhost:8082/jsp/
		}
		
		
		
		
		
		
		
	}

}
