<%@page import="model.Con_purchaseDAO"%>
<%@page import="model.ContactDAO"%>
<%@page import="model.ContactDTO"%>
<%@page import="model.UsersDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %>

<link rel="stylesheet" href= "css/bootstrap.css">



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP 게시판 웹 사이트</title>
</head>
<body>
   <% 
   
   
      UsersDTO info =(UsersDTO)session.getAttribute("info"); 
      String nickname = info.getNickname();
      
      int gb_Num = 0;
      
      if (request.getParameter("gb_Num") != null) {
    	  gb_Num = Integer.parseInt(request.getParameter("gb_Num")); // AD_NUM 게시글 번호 
         //SQL ROWNUM ORDER BY COM_DATE DESC;
         
         //SELECT COM_CATEGORY, COM_NUM, COM_TITLE, NICKNAME, COM_DATE, COM_VIEWS FROM COMMUNITY ORDER BY COM_NUM DESC;
         
      }
    /*   if (com_Num == 0) {
         PrintWriter script = response.getWriter();
         script.println("<script>");
         script.println("alert('유효하지 않는 글입니다.')");
         script.println("location.href = 'community.jsp'");
         script.println("history.back()");
         script.println("</script>");
      } */
      
      ContactDTO dto = new ContactDAO().getContactDTO(gb_Num);
     
      
   %>
   <nav class="navbar navbar-default">
      <div class="navbar-header">
         <button type="button" class="navbar-toggle collapsed"
            data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
            aria-expanded="false">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
         </button>
         <a class="navbar-brand" href="community.jsp">AMORA</a>
      </div>
      <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
         <ul class="nav navbar-nav">
            <li><a href="index.jsp">메인</a></li>
            <li class="active"><a href="contact.jsp">게시판</a></li>
         </ul>
         <% 
            if (nickname == null) {
         %>
         <ul class="nav navbar-nav navbar-right">
            <li class="dropdown">
               <a href="#" class="dropdown-toggle"
                  data-toggle="dropdown" role="button" aria-haspopup="true"
                  aria-expanded="false">접속하기<span class="caret"></span></a>
               <ul class="dropdown-menu">
                  <li><a href="index.jsp">로그인</a></li>
                  <li><a href="join.jsp">회원가입</a></li>
               </ul>
            </li>
         </ul>
         <%       
            } else {
         %>
         <ul class="nav navbar-nav navbar-right">
            <li class="dropdown">
               <a href="#" class="dropdown-toggle"
                  data-toggle="dropdown" role="button" aria-haspopup="true"
                  aria-expanded="false">마이페이지<span class="caret"></span></a>
               <ul class="dropdown-menu">
                  <li><a href="logoutAction.jsp">로그아웃</a></li>
               </ul>
            </li>
         </ul>
         <%      
            }
         %>
         
      </div>
   </nav>
   <div class="container">
      <div class="row">
         <table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
            <thead>
               <tr>
                  <th colspan="3" style="background-color: #eeeeee; text-align: center;">게시판 글보기</th>                  
               </tr>
            </thead>
            <tbody>
               <tr>
                  <td style="width: 20%;">글제목</td>
                  <td colspan="2"><%= dto.getGb_Title().replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">","&gt;").replaceAll("\n", "<br>") %></td>
               </tr>
               <tr>
                  <td>작성자</td>
                  <td colspan="2"><%= dto.getNickname() %></td>
               </tr>
               <tr>
                  <td>작성일자</td>
                  <td colspan="2"><%= dto.getGb_Date().substring(0, 11) + dto.getGb_Date().substring(11, 13) + "시" + dto.getGb_Date().substring(14, 16) + "분 " %></td>
               </tr>
               <tr>
                  <td>작성자</td>
                  <td colspan="2"><%= dto.getNickname() %></td>
               </tr>

               <%--공동구매자 갯수 세기 불러오기--%>
               <%
               		Con_purchaseDAO conDAO = new Con_purchaseDAO();
               		int count = conDAO.purchase_count(gb_Num);
               %>
               
              
               <tr>
                  <td>신청자 수</td>
                  <td colspan="2"><%= count %></td>
               </tr>
               
               <%--파일 url 을 불러오기 --%>
               <%--상세보기 클릭에서 넘어온 ad_Num을 받아서 불러왔다 --%>
				<%
					ContactDAO contactDAO = new ContactDAO();
					String imagePath = contactDAO.gb_img(gb_Num);
												 
					if(imagePath == null){
					  imagePath = "/gb_img/빈사진.png";
					}
				%>
				
				<%-- 사진을 불러옵니다 --%>
			   <tr>
                  <td>사진</td>
                  <td colspan="2"><img class="card-img-top" src="${pageContext.request.contextPath}<%=imagePath%>"  alt="..." /></td>
               </tr>
               <tr>
                  <td>내용</td>

                  <td colspan="2" style="min-height: 200px; text-align: left;"><%= dto.getGb_Content().replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">","&gt;").replaceAll("\n", "<br>") %></td>
               </tr>
               
               
            </tbody>
         </table>
         
         <%-- 구매신청/취소 버튼 --%>
         <%-- db조회해서 아이디와 gb_num이 동시에 들어간 행이 있으면 취소버튼이 뜨고 
         	없으면 구매 버튼이 뜬다
          --%>
         <%
         String id = info.getId();

         Con_purchaseDAO conDAO2 = new Con_purchaseDAO();
         int check = conDAO2.purchase_check(gb_Num, id);

         if(check==0){
         %>
         
        <form action="con_purchase" method="get">
    	<input type="hidden" name="gb_Num" value="<%= gb_Num %>">
   	 	<button type="submit" class="btn btn-primary">구매신청</button>
		</form>
		
		<%}else{%>
		
		<form action="con_purchase_del" method="get">
    	<input type="hidden" name="gb_Num" value="<%= gb_Num %>">
   	 	<button type="submit" class="btn btn-primary">구매취소</button>
		</form>
		
        <% } %>
        
        
         <a href="contact.jsp" class="btn btn-primary">목록</a>
         <%
            if (nickname != null && nickname.equals(dto.getNickname())) {
         %>
               <a href="con_update.jsp?gb_Num=<%= gb_Num %>" class="btn btn-primary">수정</a>
               <a onclick="return confirm('정말로 삭제하시겠습니까?')" href="con_deleteAction?gb_Num=<%= gb_Num %>&gb_Title=<%= dto.getGb_Title() %>" class="btn btn-primary">삭제</a>
               <!-- 삭제의 기준이 되는 com_Num과 com_Category의 값을 무조건 받아야함,그래서 전달도 같이 해줘야함. -->
         <%
            }
         %>      
         
      </div>
   </div>
   <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
   <script src="js/bootstrap.js"></script>
</body>
</html>