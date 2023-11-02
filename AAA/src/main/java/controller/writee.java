package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import model.BoardDAO;
import model.BoardDTO;
import model.UsersDAO;
import model.UsersDTO;

/**
 * Servlet implementation class writee
 */
@WebServlet("/writee")
public class writee extends HttpServlet {
   private static final long serialVersionUID = 1L;

   /**
    * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
    *      response)
    */
   protected void service(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      // TODO Auto-generated method stub
      response.setContentType("text/html;charset=UTF-8");
      request.setCharacterEncoding("UTF-8");
      response.setCharacterEncoding("UTF-8");
      
      HttpSession session = request.getSession();
      UsersDTO info = (UsersDTO) session.getAttribute("info");

      //상대 위치
      String savePath = "/com_img/";
      System.out.println("상대위치: " + savePath);
      //절대 위치
      String realSavePath = request.getServletContext().getRealPath(savePath);
      System.out.println("절대위치: " + realSavePath);
      
      int maxsize = 1024 * 1024 * 100;
      String encoding = "UTF-8";
      String Path = "C:\\Users\\gjaishcool\\Desktop\\Project_bg14\\Project\\Project"
            + "\\Amora\\Backend\\Apartmoyeora\\src\\main\\webapp\\com_img";
            
            MultipartRequest multi = new MultipartRequest(request, realSavePath, maxsize, encoding,
                  new DefaultFileRenamePolicy());
      
            String com_Title = multi.getParameter("com_Title");
            String com_Content = multi.getParameter("com_Content");
            String com_Category = multi.getParameter("com_Category");
            String SelectFile = savePath + multi.getFilesystemName("SelectFile");
            
            
      if (info == null) {
         response.sendRedirect("index.jsp");
         System.out.println("실패");
      } else {
         BoardDAO dao = new BoardDAO();
         int com_num = dao.write(info,com_Title, com_Content,com_Category);
         
         System.out.println(com_num);
         
         dao.fileupload(SelectFile, com_num);
         
         System.out.println("성공");
         System.out.println(com_Title);
         System.out.println(com_Content);
         System.out.println(com_Category);
         System.out.println(SelectFile);
         response.sendRedirect("community.jsp");
      }

   }
}