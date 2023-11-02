<%@page import="model.UsersDTO"%>
<%@page import="bbs.BbsDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<%@page import="java.io.PrintWriter"%>

<!-- 건너오는 데이터를 UTF-8으로 받을 수 있도록 -->
 <%
 request.setCharacterEncoding("UTF-8");
 %>

<!-- id="bbs": 생성된 자바 빈 객체의 이름을 "bbs"로 설정한다
     class="bbs.Bbs": 생성할 자바 빈 객체의 클래스 경로를 "bbs.Bbs"로 설정합니다 "bbs" 패키지에 있는 "Bbs" 클래스를 가리킨다
     scope="page": 생성된 자바 빈 객체의 범위(scope)를 페이지 범위로 설정합니다. 이는 JSP 페이지 내에서만 해당 객체에 접근할 수 있음을 의미합니다.
     하나의 게시글 정보를 만들수 있게 해준다 -->
<!-- 이 태그는 "bbs" 자바 빈 객체의 bbsTitle 속성 값을 설정합니다.
     name="bbs": 속성 값을 설정할 자바 빈 객체의 이름을 "bbs"로 지정합니다. 이전에 생성한 "bbs" 자바 빈 객체를 가리킵니다.
     property="bbsTitle": 설정할 속성의 이름을 "bbsTitle"로 지정합니다. 이는 "Bbs" 클래스 내의 bbsTitle 속성을 가리킵니다-->
<jsp:useBean id="bbs" class="bbs.Bbs" scope="page"/> 
<jsp:setProperty name="bbs" property="no_title" />
<jsp:setProperty name="bbs" property="no_content" />



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
	
			//이거추가 
			String userNickname =null;	
			
			// session아이디에 값이 있으면 String으로 String 형변환을 해서 userID에 넣는다
			if (session.getAttribute("userID")== null){
	
				//userID = (String)session.getAttribute("userID");

				UsersDTO info = (UsersDTO) session.getAttribute("info");
				
				userID = info.getId();
				userNickname= info.getNickname();
				
			}
			//글쓰기는 로그인이 되어있는 사람이므로 userID == null가 없으면 
			if(userID == null){
				PrintWriter script = response.getWriter();
				
				script.println("<script>");
				script.println("alert('로그인을 하세요')");
				//로그인 안된사람은 'login.jsp'페이지로 이동시킨다 			
				script.println("location.href ='login.jsp'");
				script.println("</script>");	
			
		}else{			
			//제목과 내용이 작성이 되어있지 않으면 돌려보내준다 
		if(bbs.getNo_title() == null || bbs.getNo_content() == null){

		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('입력이 안되는 사항이 있습니다')");
		//뒤로가기수행
		script.println("history.back()");
		script.println("</script>");	
			
			//***여기에서부터 값이 들어간다 		
			}else{ 		
			
	    // bbsDAO 한다음에 하나의 인스턴스를 만든다  
		BbsDAO bbsDAO = new BbsDAO();
			    
	    // bbsDAO.write(): bbsDAO라는 객체의 write 메서드를 호출합니다
		// bbsget.bbsTitle: bbsget이라는 객체의 bbsTitle 속성 값을 가져옵니다
		// userID: 아마도 현재 사용자의 식별자인 사용자 ID 값을 사용합니다
		int result = bbsDAO.write(bbs.getNo_title(), bbs.getNo_content(), userID ,userNickname);
			    
			    //함수에 반환된 결과값이 -1이면 오류가 발생한것이다
		if(result == -1){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('글쓰기에 실패했습니다')");
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