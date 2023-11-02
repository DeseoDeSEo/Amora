package controller;

import java.io.IOException;
import java.net.HttpCookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ComcDAO;
import model.ComcDTO;
import model.ShcDAO;
import model.ShcDTO;
import model.UsersDTO;

/**
 * Servlet implementation class DeleteComment
 */
@WebServlet("/DeleteBoardComment")
public class DeleteBoardComment extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	HttpSession session = request.getSession();
	
	UsersDTO info = (UsersDTO)session.getAttribute("info");
	String nickname = info.getNickname();
	int com_num = Integer.parseInt(request.getParameter("com_num"));	// COM_NUM은 겟방식으로 이미 가잇음
	ComcDAO comc =new ComcDAO(); // 
	comc.comcDelete(com_num);
	
	
	
	
	}

}
