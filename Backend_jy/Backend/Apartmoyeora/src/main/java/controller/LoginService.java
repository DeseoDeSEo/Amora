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
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		  String id = request.getParameter("id");
		  String pw = request.getParameter("pw");
		  	
		  	// System.out.println(id+pw);
		  	
	        UsersDAO dao = new UsersDAO(); 
	        UsersDTO dto = new UsersDTO(id, pw);
	        UsersDTO info =dao.login(dto);
	        // info에는 아이디, 닉네임, 관리자여부, 아파트 주소가 들어있음.
	        
	       
	        
	        
	        
	        if(info != null){
			    //로그인 성공
		    	   System.out.println("로그인 성공");
		    	   System.out.println(info.toString());
		    	   // 세션에 로그인 정보 저장
		    	   // 세션은 서버에 있는 영역
		    	   // 브라우저를 종료하면 꺼짐
		    	   HttpSession session = request.getSession();
		    	   session.setAttribute("info", info);
		    	   //로그인 후 community.jsp로 이동하기
			       response.sendRedirect("community.jsp");
			       
			    }
			    else{
			    	//로그인 실패
			    	 String errorMessage = "로그인에 실패하였습니다.";
			    	 String redirectScript = "<script>alert('" + errorMessage + "'); location.href='index.jsp';</script>";
			    	 response.getWriter().write(redirectScript);
			    }
		        
	        
	}

}
