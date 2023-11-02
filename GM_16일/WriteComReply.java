package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ComcDAO;
import model.ComcDTO;
import model.LikesDAO;
import model.UsersDTO;

/**
 * Servlet implementation class WriteComReply
 */
@WebServlet("/WriteComReply")
public class WriteComReply extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	request.setCharacterEncoding("UTF-8");
	HttpSession session = request.getSession();
	UsersDTO info = (UsersDTO) session.getAttribute("info");
	
	String id =info.getId();
	String role =info.getRole();
	String nickname =info.getNickname(); // info에서 닉네임 가져오기
	int comNum = Integer.parseInt(request.getParameter("com_Num")); // 겟방식으로 위에 있는 com_Num 가져오기
	LikesDAO ldao= new LikesDAO();
	int comLikes = ldao.getLikes(comNum);
	ComcDAO ccdao =new ComcDAO();
	// 6개짜리 dto 필요
	
//	this.comcContent = comcContent;
//	this.id = id;
//	this.comNum=comNum;
//	this.role = role;
//	this.dadat = dadat;
//	this.pcomcNum =pcomcNum;
	
	
	
	// 입력 폼을 통해 받을 내용들
	String comcContent = request.getParameter("com_content");
	ComcDTO ccdto =new ComcDTO(comcContent, id, nickname, comNum, role);
	// "INSERT INTO COMMUNITY_C VALUES(comc_num_seq.nextval(),?,?,?,SYSDATE,?,0,?,?)"
	ccdao.writeReply(ccdto);
	response.sendRedirect("view.jsp?com_Num="+comNum+"&com_Likes="+comLikes); //comLikes는 화면에 표시하기 위해서 필요
	
	
	}
	
}
