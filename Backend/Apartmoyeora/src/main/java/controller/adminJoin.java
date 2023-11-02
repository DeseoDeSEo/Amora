package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Out; 

import model.UsersDAO;
import values.UsersDTO;

/**
 * Servlet implementation class userJoin
 */
@WebServlet("/adminJoin")
public class adminJoin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// post방식이므로 utf-8로 받기
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
		String id=request.getParameter("id"); // 아이디
		String pw=request.getParameter("pw");  // 비밀번호
		String nickname=request.getParameter("nickname"); //닉네임
		String name=request.getParameter("name");
		String phone=request.getParameter("phone"); // 전화번호 
		String email=request.getParameter("email");  //이메일
		String birthdate=request.getParameter("birthdate"); //생년월일
		String gender=request.getParameter("gender"); // 성별
		String addr=request.getParameter("addr"); //관리할 아파트 
		
		UsersDTO dto = new UsersDTO(id, pw, nickname,name,phone, email, birthdate, gender, addr); //dto 객체 생성
		UsersDAO dao = new UsersDAO(); //dao 객체 생성
		int cnt= dao.adminJoin(dto);
	
		if (cnt!=0) {
			//회원가입이 이루어졌다는 뜻이므로
			out.print("<script>alert(회원가입완료!) ");
			out.print("location.href='index.jsp'</script>");
			
		}
		else {
			out.print("<script> alert(회원가입이 되지 않았습니다 반복시 문의 바랍니다) ");
			out.print("location.href='index.jsp'</script>");
		}
	}
	
}
