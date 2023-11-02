package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ContactDAO;
import model.ContactDTO;
import model.UsersDTO;

/**
 * Servlet implementation class deleteAction
 */
@WebServlet("/con_deleteAction")
public class con_deleteAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			  response.setContentType("text/html;charset=UTF-8");
			  request.setCharacterEncoding("UTF-8");
			  response.setCharacterEncoding("UTF-8");

			  PrintWriter script = response.getWriter();
		      String nickname = null;
		      HttpSession session = request.getSession();
		      UsersDTO info =(UsersDTO)session.getAttribute("info"); 
		      //세션에 있는 닉네임 정보 가져옴.
		      nickname = info.getNickname();
		      ContactDAO dao = new ContactDAO();
		      //닉네임이 없다면 로그인 안 된거임.
		      if (nickname == null) {
		         script.println("<script>");
		         script.println("alert('로그인을 하세요.')");
		         script.println("location.href = 'index.jsp'");
		         //script.println("history.back()");
		         script.println("</script>");
		      }
		      int gb_Num = 0;
		      String gb_Title = null; // 초기값을 null로 설정
		      //com_Num과 com_Category로 구분해서 삭제함. 그래서 이게 null이면 db에서 안 들어온거임.
		      if (request.getParameter("gb_Num") != null && request.getParameter("gb_Title") != null) {
		    	  gb_Num = Integer.parseInt(request.getParameter("gb_Num"));
		          gb_Title = request.getParameter("gb_Title");
		      }
		      if (gb_Num == 0) {
		         script.println("<script>");
		         script.println("alert('유효하지 않는 글입니다.')");
		         script.println("location.href = 'contact.jsp'");
		         script.println("history.back()");
		         script.println("</script>");
		      }
		      
			 ContactDTO dto = new ContactDAO().getContactDTO(gb_Num); 
			 //dao의 getBoardDTO함수는 db연결해서 db의 값을 가져오는 거임.
		      if (!nickname.equals(dto.getNickname())) {
		         script.println("<script>");
		         script.println("alert('권한이 없습니다.')");
		         script.println("location.href = 'contact.jsp'");
		         script.println("history.back()");
		         script.println("</script>");
		      } else {
		    	
		    	 
		         int result = dao.delete(gb_Num, gb_Title);
		         if (result == -1) {
		            script.println("<script>");
		            script.println("alert('글 삭제에 실패 했습니다.')");
		            script.println("history.back()");
		            script.println("</script>");
		         } else {
		            script.println("<script>");
		            script.println("alert('해당 글을 삭제 했습니다.')");	
		            System.out.println(result);
		            System.out.println(gb_Num);
		            System.out.println(gb_Title);
		            
		            script.println("location.href = 'contact.jsp'");
		            script.println("</script>");
		           
		         }            
		      }      
	
		
		
		
	}

}
