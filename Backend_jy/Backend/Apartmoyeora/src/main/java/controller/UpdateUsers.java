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
import model.UsersDTO;

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
	response.setContentType("text/html;charset=UTF-8");	
	
	request.setCharacterEncoding("UTF-8");
	
	HttpSession session = request.getSession();
	
	UsersDTO info = (UsersDTO)session.getAttribute("info");
	
	// 입력폼을 통해 수정할 정보 받기
	
	String pw= request.getParameter("pw"); // 비밀번호
	String nickname=request.getParameter("nickname"); // 닉네임
	String email=request.getParameter("email"); // email
	String phone=request.getParameter("phone"); // 전화번호
	
	
	UsersDAO dao= new UsersDAO();
	// info에서 id가져오기
	
	
	String id= info.getId();
	
	UsersDTO dto = new UsersDTO(pw, nickname, phone, email, id);
	int cnt =dao.updateUsers(dto);
	
	if(cnt>0) {
		
		// 회원정보를 수정하고 나서 새롭게 info가 수정되어 세션이 업데이트 되는 과정이 필요!
		String UpdateMessage = "회원정보 수정 완료.";
		
		dto = new UsersDTO(id, dao.getPw(id));
		info = dao.login(dto);
		
		
		
		session.setAttribute("info", info);
		info = (UsersDTO)session.getAttribute("info");
		
    	String redirectScript = "<script>alert('" + UpdateMessage + "'); location.href='destination.jsp';</script>";
    	response.getWriter().write(redirectScript);
    	
    	
	}
	else {
		String NotUpdateMessage = "회원정보 수정 실패.";
    	String redirectScript = "<script>alert('" + NotUpdateMessage + "'); location.href='destination.jsp';</script>";
    	response.getWriter().write(redirectScript);
	}
	
	
	
	
	}

}
