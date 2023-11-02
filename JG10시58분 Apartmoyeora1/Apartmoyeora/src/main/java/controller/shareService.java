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

/**
 * Servlet implementation class SecondHandWrite
 */
@WebServlet("/shareService")
public class shareService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * 
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
//		String sh_title = request.getParameter("sh_title");
//		String sh_content = request.getParameter("sh_content");
//		String sh_priceStr = request.getParameter("sh_price");
//		String section = request.getParameter("section");
		
//		int sh_price = 0;
//		if (sh_priceStr != null && !sh_priceStr.isEmpty()) {
//		    try {
//		        sh_price = Integer.parseInt(sh_priceStr);
//		    } catch (NumberFormatException e) {
//		        // sh_priceStr이 정수로 변환할 수 없는 경우의 예외 처리
//		        e.printStackTrace(); // 적절한 로깅 또는 예외 처리를 수행하세요.
//		    }
//		}
		
		HttpSession session = request.getSession();

		String savePath = "/sh_img/";
		System.out.println("상대위치: " + savePath);
		//절대 위치
		String realSavePath = request.getServletContext().getRealPath(savePath);
		System.out.println("절대위치: " + realSavePath);
		
		int maxsize = 1024 * 1024 * 100;
		String encoding = "UTF-8";
		String Path = "C:\\Users\\gjaishcool\\Desktop\\git\\Amora\\Backend\\Apartmoyeora\\src\\main\\webapp\\sh_img";
		
		
		MultipartRequest multi = new MultipartRequest(request, Path, maxsize, encoding,
				new DefaultFileRenamePolicy());
		
		String sh_title = multi.getParameter("sh_title");
		String sh_content = multi.getParameter("sh_content");
		int sh_price = Integer.parseInt(multi.getParameter("sh_price"));
		String section = multi.getParameter("section");
		String fileName = savePath + multi.getFilesystemName("file");
		
		UsersDTO info = (UsersDTO) session.getAttribute("info");

		System.out.println(sh_title + sh_content + sh_price + section + fileName);

		//상대 위치


		if (info == null) {
			response.sendRedirect("sh_write.jsp");

		} else {

			ShDAO dao = new ShDAO();
			
			int sh_num = dao.secondhandwrite(info, sh_title, sh_content, sh_price, section);
			
			System.out.println(sh_num);
			
			dao.fileupload(fileName, sh_num);
			
			response.sendRedirect("community.jsp");
		}

	}
}
