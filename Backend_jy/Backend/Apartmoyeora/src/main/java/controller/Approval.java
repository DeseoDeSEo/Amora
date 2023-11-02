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
 * Servlet implementation class Approval
 */
@WebServlet("/Approval")
public class Approval extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");	
		
		request.setCharacterEncoding("UTF-8");
		
		// 세션가져오기
		HttpSession session = request.getSession();
		UsersDTO info = (UsersDTO) session.getAttribute("info");
		
		
		// info(로그인 시 생성된 세션에서 관리인 주소 가져오기
		String addr = info.getAddr();
		// dao import
		UsersDAO dao =new UsersDAO();
		int cnt =dao.approval(addr);
		
		 // PrintWriter out=response.getWriter();
		//완료되었을 때 어떻게 할건가
		if(cnt>0) {
			
			// 회원정보를 수정하고 나서 새롭게 info가 수정되어 세션이 업데이트 되는 과정이 필요!
			String UpdateMessage = "승인 완료.";
			
			
			session.setAttribute("info", info);
			info = (UsersDTO)session.getAttribute("info");
			
	
			
	    	String redirectScript = "<script>alert('" + UpdateMessage + "'); location.href='destination.jsp';</script>";
	    	response.getWriter().write(redirectScript);
	    	
	    	
		}
		else {
			String NotUpdateMessage = "승인 실패.";
	    	String redirectScript = "<script>alert('" + NotUpdateMessage + "'); location.href='membermanagement.jsp';</script>";
	    	response.getWriter().write(redirectScript);
		}
		
		
		
	}

}
