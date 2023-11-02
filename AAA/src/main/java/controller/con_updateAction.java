package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import model.ContactDAO;
import model.ContactDTO;
import model.UsersDTO;

/**
 * Servlet implementation class updateAction
 */
@WebServlet("/con_updateAction")
public class con_updateAction extends HttpServlet {
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
		ContactDTO dto = new ContactDAO().getContactDTO(gb_Num);
		
		if (session.getAttribute("info") != null) {
			String id = info.getId();
		}
		
		if (request.getParameter("gb_Num") != null) {
			gb_Num = Integer.parseInt(request.getParameter("gb_Num"));
		}
		//세션 로그인 정보 가져옴
		if (info == null) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('로그인을 하세요.')");
			script.println("location.href = 'index.jsp'");
			script.println("history.back()");
			script.println("</script>");
		}
		
		// 수정을 먼저
		
		if (!info.getId().equals(dto.getId())) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('권한이 없습니다.')");
			System.out.println(info.getId());
			System.out.println(dto.getId());
			script.println("location.href = 'contact.jsp'");
			script.println("history.back()");
			script.println("</script>");
		} else {
			//수정 내용에 null이 있으면 돌아감.
			if (request.getParameter("gb_Title") == null || request.getParameter("gb_Content") == null
					|| request.getParameter("gb_Title") == "" || request.getParameter("gb_Content") == ""){
					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("alert('입력이 안된 사항이 있습니다.')");
					script.println("history.back()");
					script.println("</script>");
				} else {
					ContactDAO dao = new ContactDAO();
					//dao의 update함수 가져옴. 매개변수개수, 타입 맞아야함.
					int result = dao.update(info, request.getParameter("gb_Title"), request.getParameter("gb_Content"), dto.getGb_Num());
					if (result == 0) {
									
						if (gb_Num == 0) {
							PrintWriter script = response.getWriter();
							script.println("<script>");
							script.println("alert('유효하지 않는 글입니다.')");
							script.println("location.href = 'contact.jsp'");
							script.println("history.back()");
							script.println("</script>");
						}
						
						PrintWriter script = response.getWriter();
						script.println("<script>");
						script.println("alert('글 수정에 실패 했습니다.')");
						script.println("history.back()");
						script.println("</script>");
					} else {
						PrintWriter script = response.getWriter();
						script.println("<script>");
						script.println("alert('수정에 성공')");
						// view로 이동하면서 com_Num값을 같이 보내줘야함.
						//script.println("location.href = 'con_view.jsp?com_Num="+gb_Num+"&gb_Likes"+dao.getGb_Likes(gb_Num)+"'");
						script.println("location.href = 'con_view.jsp?gb_Num="+gb_Num+"'");
						script.println("</script>");
					}
				}
		} 
		
	}

}
