package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ComcDAO;

/**
 * Servlet implementation class DeleteComcReply
 */
@WebServlet("/DeleteComcReply")
public class DeleteComcReply extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		ComcDAO comcdao =new ComcDAO();
		int comNum = Integer.parseInt(request.getParameter("com_Num"));
		int comcNum = Integer.parseInt(request.getParameter("comc_Num"));
		int comLikes = Integer.parseInt(request.getParameter("com_Likes"));
		int cnt =comcdao.comcDelete(comcNum);
		if (cnt!=0){ //삭제됫을경우
		      response.sendRedirect("view.jsp?com_Num=" + comNum + "&com_Likes=" + comLikes);
		      
		}
		
	}

}
