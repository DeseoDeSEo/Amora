package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ReportDAO;
import model.ShDAO;
import model.ShReportDTO;
import model.UsersDAO;
import model.UsersDTO;

@WebServlet("/ShUnReportUpdateAction")
public class ShUnReportUpdateAction extends HttpServlet {
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
   ShDAO sdao =new ShDAO();
   ReportDAO rdao = new ReportDAO(); //좋아요와 관련된 DAO
   String id = info.getId(); // id 가져오기
   int shNum = Integer.parseInt(request.getParameter("sh_Num")); //com_Num에서 숫자 받아서
   sdao.ReportDown(shNum);
   UsersDAO udao = new UsersDAO();
   ShReportDTO srdto = new ShReportDTO(id, shNum);
   int ShReportNum =rdao.getShReport_num(srdto); //신고 등록번호
   
//id, comNum 을 매개변수로 하는 업데이트 구문 사용
   int cnt =rdao.shUnReport(ShReportNum); 
   if (cnt !=0) {
      // 업데이트 성공 신고 +1이나 신고수는 보이지 않으므로 좋아요만 가져오기 
      int shLikes = sdao.getSh_Likes(shNum);
      // 다른 id 변수 필요 --> 신고 버튼의 게시글 주인
      String rid = sdao.getId(shNum);
      udao.pointUnReportCom(rid); // 신고로 인해 포인트 증가하기
      response.sendRedirect("view.jsp?com_Num=" + shNum + "&sh_Likes=" + shLikes);
   }
   else{
      // 업데이트 실패 좋아요 변동 X
      
      out.print("<script>alert('업데이트가 되지 않았습니다');");
      out.print("history.back();</script>");
   }
   }

}