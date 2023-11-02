package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UsersDAO;
import model.UsersDTO;

/**
 * Servlet implementation class userJoin
 */
@WebServlet("/userJoin")
public class userJoin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// post방식이므로 utf-8로 받기
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String id = request.getParameter("id"); // 아이디
		String pw = request.getParameter("pw"); // 비밀번호
		String nickname = request.getParameter("nickname"); // 닉네임
		String name = request.getParameter("name");
		String phone = request.getParameter("phone"); // 전화번호
		String email = request.getParameter("email"); // 이메일
		String birthdate = request.getParameter("birthdate"); // 생년월일
		String gender = request.getParameter("gender"); // 성별
		String addr = request.getParameter("addr"); // 관리할 아파트
		String dongho = request.getParameter("dongho"); // 자기집 동호수

		UsersDTO dto = new UsersDTO(id, pw, nickname, name, phone, email, birthdate, gender, addr, dongho); // dto 객체 생성
		UsersDAO dao = new UsersDAO(); // dao 객체 생성
		
		int cnt = dao.userJoin(dto);
		
		if(cnt>0) {
			System.out.println("회원가입 성공");
			response.sendRedirect("index.jsp");
		}
		else {
			System.out.println("회원가입 실패");
			response.sendRedirect("index.jsp");
		}

	}
}
