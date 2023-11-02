package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.UsersDAO;
import model.UsersDTO;

/**
 * Servlet implementation class LoginService
 */
@WebServlet("/LoginService")
public class LoginService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	      //포스트방식의 전송방식이므로
	      request.setCharacterEncoding("UTF-8");
		  	String id = request.getParameter("id");
		  	String pw = request.getParameter("pw");
		  	
		  	// System.out.println(id+pw);
		  	
	        UsersDAO dao = new UsersDAO(); 
	        UsersDTO dto = new UsersDTO(id, pw);
	        UsersDTO info =dao.login(dto);
	        // info에는 아이디, 닉네임, 관리자여부, 아파트 주소가 들어있음.
	        
	        //세션을 활용하기위해 세션에 저장하는 작업
	        HttpSession session = request.getSession();
	        
	        session.setAttribute("info", info);
	        
	        
	        
	        System.out.println(info);
	        //회원가입 후 index.jsp로 이동하기
	        response.sendRedirect("community.jsp");
	}

}
