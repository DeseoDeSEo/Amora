package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ShDAO;

@WebServlet("/Share_ViewCountUpService")
public class Share_ViewCountUpService extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int shNum = Integer.parseInt(request.getParameter("sh_Num"));
        ShDAO dao = new ShDAO();
        dao.Viewup(shNum);
        int shLikes= dao.getSh_Likes(shNum);
        response.sendRedirect("sh_view.jsp?sh_Num=" + shNum +"&sh_Likes="+shLikes);
    }
}
