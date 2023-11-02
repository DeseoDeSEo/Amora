package controller;

import java.io.IOException;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import model.ShDAO;
import model.UsersDAO;
import model.UsersDTO;

@WebServlet("/SecondhandService")
public class SecondhandService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * 
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();

		String savePath = "/sh_img/";
		System.out.println("상대위치: " + savePath);
		//절대 위치
		String realSavePath = request.getServletContext().getRealPath(savePath);
		System.out.println("절대위치: " + realSavePath);
		
		
		int maxsize = 1024 * 1024 * 100;
		String encoding = "UTF-8";
		
		
		MultipartRequest multi = new MultipartRequest(request, realSavePath, maxsize, encoding,
				new DefaultFileRenamePolicy());
		
		
		
		String sh_title = multi.getParameter("sh_title");
		String sh_content = multi.getParameter("sh_content");
		String fileName = savePath + multi.getFilesystemName("file");
		int sh_price = Integer.parseInt(multi.getParameter("sh_price"));

		 
		UsersDTO info = (UsersDTO) session.getAttribute("info");

		System.out.println(sh_title + sh_content  + sh_price + fileName);

		//상대 위치

		if (info == null) {
			response.sendRedirect("sh_write.jsp");

		} else {

			ShDAO dao = new ShDAO();
			
			int sh_num = dao.secondhandwrite(info, sh_title, sh_content,sh_price);
			
			System.out.println(sh_num);
			
			dao.fileupload(fileName, sh_num);
			
			response.sendRedirect("secondhand.jsp");
		}

	}
}
