package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.UsersDAO;
import values.UsersDTO;

/**
 * Servlet implementation class UpdateUsers
 */
@WebServlet("/UpdateUsers")
public class UpdateUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	request.setCharacterEncoding("UTF-8");
	HttpSession session = request.getSession();
	PrintWriter out= response.getWriter();
	// 입력폼을 통해 수정할 정보 받기
	
	String pw= request.getParameter("pw"); // 비밀번호
	String email=request.getParameter("email"); // email
	String phone=request.getParameter("phone"); // 전화번호
	String nickname=request.getParameter("nickname"); // 닉네임
	UsersDAO dao= new UsersDAO();
	// info에서 id가져오기
	
	UsersDTO info = (UsersDTO)session.getAttribute("info");
	String id= info.getId();
	if(pw == null) { //유저가 폼에 입력하지 않은 경우 , 단 세션에도 비밀번호는 저장되지 않으므로 세션에서 가져올 수 없음.
		pw =dao.getPw(id);
	}
	if(phone ==null) {//유저가 폼에 입력하지 않은 경우 , 단 세션에도 폰번호는 저장되지 않으므로 세션에서 가져올 수 없음.
		pw =dao.getPhone(id);
	}
	if(nickname == null) {
		nickname = info.getNickname();
	}
	if(email == null) {
		email= info.getNickname();
	}
	
	UsersDTO dto = new UsersDTO(id ,pw, nickname, email, phone);
	int cnt =dao.updateUsers(dto);
	
	if(cnt!=0) {
		out.print("<script>회원정보 수정 완료 ");
		out.print("location href= 'index.jsp' </script>");
	}else {
		out.print("<script>회원정보 수정 실패 ");
		out.print("location href= 'index.jsp' </script>");
	}
	
	
	
	
	}

}
