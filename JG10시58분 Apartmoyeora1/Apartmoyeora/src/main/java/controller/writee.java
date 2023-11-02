package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.BoardDAO;
import model.BoardDTO;
import model.UsersDAO;
import model.UsersDTO;

/**
 * Servlet implementation class writee
 */
@WebServlet("/writee")
public class writee extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		UsersDTO info = (UsersDTO) session.getAttribute("info");
		
		String com_Title = request.getParameter("com_Title");
		String com_Content = request.getParameter("com_Content");
		String com_Category = request.getParameter("com_Category");
		System.out.println(com_Title);
		System.out.println(com_Content);
		System.out.println(com_Category);

		if (info == null) {
			response.sendRedirect("index.jsp");
			System.out.println("실패");
		} else {
			BoardDAO dao = new BoardDAO();
			dao.write(info,com_Title, com_Content,com_Category);
			System.out.println("성공");
			System.out.println(com_Title);
			System.out.println(com_Content);
			System.out.println(com_Category);

			response.sendRedirect("community.jsp");
		}

	}
}
