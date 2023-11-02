<%@page import="model.UsersDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="bbs.BbsDAO"%>
<%@page import="bbs.Bbs"%>    

<%@page import="java.io.PrintWriter"%>

<!-- 건너오는 데이터를 UTF-8으로 받을 수 있도록 -->
 <%
 request.setCharacterEncoding("UTF-8");
 %>




<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>JSP 게시판 웹사이트</title>
</head>
<body>

 <%

//!!!세션이란 현재접속한 회원의 할당해주는 고유의 아이디입니다
	//로그인에 성공을 했을때 세션아이디를 부여해주는것으로 부터 세션관리가 시작이된다 
	String userID = null;

	
	
	// session아이디에 값이 있으면 String으로 String 형변환을 해서 userID에 넣는다
	if (session.getAttribute("userID")== null){

		

		UsersDTO info = (UsersDTO) session.getAttribute("info");
		
		userID = info.getId();
	
		
	}
	
	
	
	
	
	
	
	
	
	
	
	//글쓰기는 로그인이 되어있는 사람이므로 userID == null가 없으면 
	if(userID == null){
		PrintWriter script = response.getWriter();
		
		script.println("<script>");
		script.println("alert('로그인을 하세요')");
		//로그인 안된사람은 'login.jsp'페이지로 이동시킨다 			
		script.println("location.href ='index.jsp'");
		script.println("</script>");	
	
	//로그인 된사람은 else로 넘어간다 
	}
	
 
 
 
 
 
 
 
 
 
 
 
		
		//복사시작// 
		//bbsID를 0으로 설정
		 int no_num = 0;
		 
		 if(request.getParameter("no_num")!= null){
			 no_num= Integer.parseInt(request.getParameter("no_num"));
		
		    // bbsID 번호가 들어오지 않았다면
		 	}if(no_num == 0){
		    	
				PrintWriter script = response.getWriter();
				
				script.println("<script>");
				script.println("alert('유효하지 않는 글입니다')");
				//다시 bbs.jsp페이지로 이동을 한다  			
				script.println("location.href ='pricing.jsp'");
				script.println("</script>");
		  }

	     Bbs bbs = new BbsDAO().getBbs(no_num);
	     if(!userID.equals(bbs.getId())){
		   PrintWriter script = response.getWriter();
			
			script.println("<script>");
			script.println("alert('권한이 없습니다')");
			//다시 bbs.jsp페이지로 이동을 한다  			
			script.println("location.href ='pricing.jsp'");
			script.println("</script>");
		   
	   }//복사끝//
		
		else{			
			//자바빈즈가 사용하지 않기때문에 title과 content 넘어오는값들을 다 분석해서 비교할 필요가 있다 
			//request.getParameter로 넘어온 수정한 bbsTitle과 bbsContent이 널값이거나 빈칸인경우 입력이 안되는 사항이 있습니다 를 알린다
			if(request.getParameter("no_title") == null || request.getParameter("no_content") == null
			   || request.getParameter("no_title").equals("") || request.getParameter("no_content").equals("")){

		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('입력이 안되는 사항이 있습니다')");
		//뒤로가기수행
		script.println("history.back()");
		script.println("</script>");	
			
			//***여기에서부터 값이 들어간다 		
			}else{ 		
			
	    
		BbsDAO bbsDAO = new BbsDAO();
			    
		// bbsDAO.update(): bbsDAO라는 객체의 update 메서드를 호출합니다
		// request.getParameter("bbsTitle"): 웹 페이지의 입력 폼에서 전달된 게시물 제목과
		// bbsID: 아마도 현재 사용자의 식별자인 사용자 ID 값을 사용합니다
		int result = bbsDAO.update(no_num, request.getParameter("no_title"), request.getParameter("no_content"));
			    
			    //함수에 반환된 결과값이 -1이면 오류가 발생한것이다
		if(result == -1){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('글수정에 실패했습니다')");
			script.println("history.back()");
			script.println("</script>");
		 
		// 글쓰기에 성공하는경우
		}
		else { 
			// 텍스트를 출력하기 위한 클래스입니다 PrintWriter를 사용하여 문자열을 클라이언트에게 보낼 수 있습니다
			PrintWriter script = response.getWriter();
			script.println("<script>");
			// bbs.jsp게시글 메인에 이동을 한다 
			script.println("location.href ='pricing.jsp'");
			script.println("</script>");
		}
			}
		}
	
	
	
	
	%>
	
</body>
</html>