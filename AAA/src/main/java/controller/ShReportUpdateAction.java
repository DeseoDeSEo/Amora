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
import model.ComReportDTO;
import model.ReportDAO;
import model.UsersDAO;
import model.UsersDTO;

/**
 * Servlet implementation class BoardLikeUpdateAction
 */
@WebServlet("/ShReportUpdateAction")
public class ShReportUpdateAction extends HttpServlet {
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
   BoardDAO bdao =new BoardDAO();
   ReportDAO rdao = new ReportDAO(); //좋아요와 관련된 DAO
   String id = info.getId(); // id 가져오기
   int comNum = Integer.parseInt(request.getParameter("com_Num")); //com_Num에서 숫자 받아서
   bdao.ReportUp(comNum);
   UsersDAO udao = new UsersDAO();
   ComReportDTO crdto = new ComReportDTO(id, comNum); //id, comNum 을 매개변수로 하는 업데이트 구문 사용
   int cnt =rdao.ComReport(crdto); // reportDAO에 행 추가
   if (cnt !=0) {
      // 업데이트 성공 신고 +1이나 신고수는 보이지 않으므로 좋아요만 가져오기 
      int comLikes = bdao.getCom_Likes(comNum);
      // 다른 id 변수 필요 --> 신고 버튼의 게시글 주인
      String rid = bdao.getId(comNum);
      udao.pointReportCom(rid); // 신고 포인트 감소 하기
      // 포인트가 음수가 되지 않도록 하기 위해 포인트가 음수라면 0으로 초기화하는 작업 
      int point =udao.getPoint(rid);
      if (point < 0) {
         udao.resetPoint(rid); // 포인트를 0으로 DB를 수정
      }
      response.sendRedirect("view.jsp?com_Num=" + comNum + "&com_Likes=" + comLikes);
   }
   else{
      // 업데이트 실패 좋아요 변동 X
      
      out.print("<script>alert('업데이트가 되지 않았습니다');");
      out.print("history.back();</script>");
   }
   }

}