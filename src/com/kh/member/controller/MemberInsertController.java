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
 * Servlet implementation class MemberInsertController
 */
@WebServlet("/enroll.me")
public class MemberInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/views/member/memberEnrollForm.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1) 인코딩작업
		request.setCharacterEncoding("UTF-8");
		
		// 2) 요청시 전달값을 뽑아서 변수 및 객체에 기록하기
		// 아이디, 비번, 이름, 전화번호, 이메일, 주소, 취미
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		String userName = request.getParameter("userName");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String[] interestArr = request.getParameterValues("interest");
		
		String interest = "";
		if(interestArr != null) {
			interest = String.join(",", interestArr);
		}
		
		// 매개변수 생성자를 이용해서 Member객체 생성하기
		Member m = new Member(userId, userPwd, userName, phone, email, address, interest);
		
				
		// 회원가입 요청처리
		int result = new MemberService().insertMember(m);
		
		// 처리 결과(result)를 가지고 사용자가 보게될 응답화면 지정
		if(result > 0) {
			// 회원가입 성공
			HttpSession session = request.getSession();
			session.setAttribute("alertMsg", "회원가입에 성공했습니다.");
			
			response.sendRedirect(request.getContextPath());
			// 입력한 데이터 비워주고 메인페이지로 이동
			
		}else {
			// 회원가입 실패
			request.setAttribute("errorMsg", "회원가입에 실패했습니다.");
			request.getRequestDispatcher("/views/common/errorPage.jsp").forward(request, response);
		}
		
		
	}

}
