package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ApartDAO;
import model.UsersDAO;
import model.UsersDTO;

/**
 * Servlet implementation class DropoutService
 */
@WebServlet("/DropoutService")
public class DropoutService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 회원탈퇴 서비스
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		UsersDAO dao = new UsersDAO();
		UsersDTO info =(UsersDTO)session.getAttribute("info");
		String id =info.getId();
		int cnt =dao.dropout(id);
		
		if (cnt!= 0) {
			session.invalidate(); // 모든 세션삭제
			out.print("<script>alert(탈퇴가 완료되었습니다) ");
			out.print("location.href='index.jsp'</script>");
		}else {
			out.print("<script>alert(탈퇴가 되지않았습니다. 메인페이지로 이동합니다.) ");
			out.print("location.href='index.jsp'</script>");
		}
	}
	
}


