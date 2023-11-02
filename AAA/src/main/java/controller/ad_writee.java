package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import model.AdDAO;
import model.UsersDTO;


@WebServlet("/ad_writee")
public class ad_writee extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		UsersDTO info = (UsersDTO) session.getAttribute("info");
		
		
		String savePath = "/ad_img/";
		System.out.println("상대위치: " + savePath);
		//절대 위치
		String realSavePath = request.getServletContext().getRealPath(savePath);
		System.out.println("절대위치: " + realSavePath);
		
		int maxsize = 1024 * 1024 * 100;
		String encoding = "UTF-8";
		String Path = "C:\\Users\\gjaischool1\\Desktop\\Backend\\Apartmoyeora\\src\\main\\webapp\\ad_img";
		
		
		MultipartRequest multi = new MultipartRequest(request, realSavePath, maxsize, encoding,
				new DefaultFileRenamePolicy());
		
		String ad_Title = multi.getParameter("ad_Title");
		String ad_Content = multi.getParameter("ad_Content");
		String ad_Period = multi.getParameter("ad_Period");
		String SelectFile = savePath + multi.getFilesystemName("SelectFile");
		


		System.out.println(ad_Title + ad_Content + ad_Period  + SelectFile);
		
		
	
		if (info == null) {
			response.sendRedirect("index.jsp");
			System.out.println("실패");
		} else {
			AdDAO dao = new AdDAO();
			int ad_num = dao.adwrite(info, ad_Title, ad_Content, ad_Period);
			
			System.out.println(ad_num);
			
			dao.fileupload(SelectFile, ad_num);
			
			System.out.println("성공");
			System.out.println(ad_Title);
			System.out.println(ad_Content);
			System.out.println(ad_Period);

			response.sendRedirect("promotion.jsp");
		}

	}
}
