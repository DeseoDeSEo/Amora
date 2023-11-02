package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Con_purchaseDAO;
import model.UsersDTO;

/**
 * Servlet implementation class con_purchase
 */
@WebServlet("/con_purchase")
public class con_purchase extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		UsersDTO info = (UsersDTO) session.getAttribute("info");
		
		int gb_Num = Integer.parseInt(request.getParameter("gb_Num"));
		String id = info.getId();
		
		System.out.println(id +" "+ gb_Num);
		
		//신청자를 등록해주는 매소드
		Con_purchaseDAO dao = new Con_purchaseDAO();

		int cnt = dao.purchase(gb_Num, id);
				
		if(cnt==1) {
			System.out.println("공동구매 등록 성공");
		}else {
			System.out.println("공동구매 등록 실패");
		}
		
		
		
		//받아왔던 상세보기 gb_Num를 다시 돌려준다
		response.sendRedirect("con_view.jsp?gb_Num=" + gb_Num);
		
	}

}
