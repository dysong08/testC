package com.kh.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class myPage
 */
@WebServlet("/myPage.me")
public class myPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public myPageController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// url로 직접 페이지 요청시 
		// 로그인하기 전 요청인지 => 메인페이지로 sendRedirect
		// 로그인한 후 요청인지 확인 => 마이페이지로 포워딩
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("loginUser") == null) {
			// 로그인 정보 없는 상태 => 로그인 전 상태
			session.setAttribute("alertMsg", "로그인 후 이용해주세요");
			response.sendRedirect(request.getContextPath());
		}else {
			// 로그인된 상태
			request.getRequestDispatcher("views/member/myPage.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		UPDATE MEMBER 
//		SET USER_NAME = ?,
//			PHONE = ?,
//			EMAIL = ?,
//			ADDRESS = ?,
//			INTEREST = ?,
//			MODIFY_DATE = SYSDATE
//		WHERE USER_ID = ?
		
		request.setCharacterEncoding("UTF-8");
		
		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String interest = String.join(",", request.getParameterValues("interest") == null ? 
				new String[]{} : request.getParameterValues("interest"));
				
		Member m = new Member(userId, userName, phone, email, address, interest);
		
		// 3) 요청처리
		Member updateMem = new MemberService().updateMember(m);
		
		// 4) 돌려받은 결과를 가지고 사용자가 보게될 응답화면 지정
		if(updateMem == null) {
			// 실패
			request.setAttribute("errorMsg", "회원정보 수정에 실패했습니다.");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
			
		}else {
			// 성공
			HttpSession session = request.getSession();
			session.setAttribute("alertMsg", "회원정보를 변경했습니다.");
			//session.removeAttribute("loginUser");
			session.setAttribute("loginUser", updateMem);
			// 같은 키값은 존재할 수 없다 => 덮어쓰기
			
			response.sendRedirect(request.getContextPath()+"/myPage.me"); //get방식
			
		}
	}
}
