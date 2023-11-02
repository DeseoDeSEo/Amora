package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.BoardDAO;
import model.ComLikeDTO;
import model.LikesDAO;
import model.UsersDAO;
import model.UsersDTO;

/**
 * Servlet implementation class BoardLikeUpdateAction
 */
@WebServlet("/BoardUnLikeUpdateAction")
public class BoardUnLikeUpdateAction extends HttpServlet {
   private static final long serialVersionUID = 1L;

   /**
    * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
    */
   protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
   response.setContentType("text/html;charset=UTF-8");
   PrintWriter out =response.getWriter();
   HttpSession session = request.getSession();
   UsersDTO info = (UsersDTO) session.getAttribute("info");
   BoardDAO bdao = new BoardDAO();
   UsersDAO udao =new UsersDAO();

   
   String id = info.getId();
   int comNum = Integer.parseInt(request.getParameter("com_Num"));
   bdao.LikesDown(comNum);
   ComLikeDTO cldto = new ComLikeDTO(id, comNum); //id, comNum 을 매개변수로 하는 업데이트 구문 사용
   LikesDAO ldao = new LikesDAO(); //좋아요와 관련된 DAO 
   int comLikeNum =ldao.getComLike_num(cldto); //좋아요 등록번호
   int cnt =ldao.ComUnlike(comLikeNum); 
   if (cnt !=0) {
      // 업데이트 성공 좋아요 -1
      int comLikes = bdao.getCom_Likes(comNum);
      String rid = bdao.getId(comNum);
      udao.pointUnLikesCom(rid); //좋아요 업데이트로 인한 유저 포인트 증가s
      response.sendRedirect("view.jsp?com_Num=" + comNum + "&com_Likes=" + comLikes);
   }
   else{
      // 업데이트 실패 좋아요 변동 X
      out.print("<script>alert('업데이트가 되지 않았습니다');");
      out.print("history.back();</script>");
   }
   }

}