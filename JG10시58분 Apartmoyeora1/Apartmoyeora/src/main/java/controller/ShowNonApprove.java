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
 * Servlet implementation class Approvalservice
 */
@WebServlet("/Approvalservice")
public class ShowNonApprove extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		//버튼을 누르면 그 줄의 데이터가 있는지 확인하고 있으면 업데이트 서비스 addr은 세션에서 가져옴
		UsersDAO dao =new UsersDAO();
	
		HttpSession session = request.getSession();
		UsersDTO info = (UsersDTO)session.getAttribute("info");
		String admin_addr= info.getAddr(); //admin이 아닐경우 이페이지에 접근 불가하게 코드를 짜야합니다. 관리자의 관리주소가 입주자의 주소와 같은 사람만 보여지게
		dao.showNonApprove(admin_addr);
		
		
		
	
	
	
	}

}
