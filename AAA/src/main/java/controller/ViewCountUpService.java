package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.BoardDAO;

@WebServlet("/ViewCountUpService")
public class ViewCountUpService extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int comNum = Integer.parseInt(request.getParameter("com_Num"));
        BoardDAO dao = new BoardDAO();
        dao.Viewup(comNum);
        int comLikes= dao.getCom_Likes(comNum);
        response.sendRedirect("view.jsp?com_Num=" + comNum +"&com_Likes="+comLikes);
    }
}
