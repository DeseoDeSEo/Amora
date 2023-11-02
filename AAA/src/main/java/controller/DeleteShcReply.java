package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ComcDAO;
import model.ShcDAO;

/**
 * Servlet implementation class DeleteComcReply
 */
@WebServlet("/DeleteShcReply")
public class DeleteShcReply extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		ShcDAO shcdao =new ShcDAO();
		int shNum = Integer.parseInt(request.getParameter("sh_Num"));
		int shcNum = Integer.parseInt(request.getParameter("shc_Num"));
		int shLikes = Integer.parseInt(request.getParameter("sh_Likes"));
		int cnt =shcdao.shcDelete(shcNum);
		if (cnt!=0){ //삭제됫을경우
		      response.sendRedirect("sh_view.jsp?sh_Num=" + shNum + "&sh_Likes=" + shLikes);
		      
		}
		
	}

}
