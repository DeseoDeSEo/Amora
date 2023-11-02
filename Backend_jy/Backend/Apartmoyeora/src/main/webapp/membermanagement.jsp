<%@page import="model.UsersDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.UsersDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<ul class="actions">
		<% 
		UsersDAO dao = new UsersDAO();
		
		
		  request.setCharacterEncoding("UTF-8");
		  response.setCharacterEncoding("UTF-8");
		 

          // 로그인 정보 가져오기
          UsersDTO info = (UsersDTO) session.getAttribute("info");

          // 회원 정보 가져오기
          ArrayList<UsersDTO> userList = dao.showMyApartUser(info.getAddr());

          // 회원 정보를 출력
          for (int i = 0; i < userList.size(); i++) {
              UsersDTO user = userList.get(i);
				
              out.println("<li>");
              out.println("ID: " + user.getId());
              out.println("Nickname: " + user.getNickname());
              out.println("Name: " + user.getName());
              out.println("Address: " + user.getAddr());
              out.println("Dongho: " + user.getDongho());
              out.println("Approval: " + user.getApproval());
              out.println("</li>");
				
				
			}%>
		

		
		<form action="Approval" method="post">
			<button>전체승인하기</button></li>
		</form>										
		
	</ul>
</body>
</html>