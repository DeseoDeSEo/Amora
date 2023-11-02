<%@page import="model.UsersDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
#wrap, h2, table {
	margin: auto;
	text-align: center;
}
</style>
</head>
<body>
	   <div id="wrap">
      <h2>회원가입페이지</h2>

      <%-- 세션에서 로그인 정보가 있는지 확인 --%>
      <% UsersDTO info = (UsersDTO) session.getAttribute("info");
         if (info != null) { %>
            <p>로그인 상태입니다. 환영합니다, <%= info.getId() %>님!</p>
            <a href="../UserLogout">로그아웃</a>
      <% } else { %>
            <form action="../UserLogin" method="post">
               <table>
                  <tr>
                  
                     <td>아이디</td>
                     <td><input type="text" name="id"></td>
                  </tr>
                  <tr>
                     <td>PW</td>
                     <td><input type="password" name="pw"></td>
                  </tr>
                  <tr>
                     <td colspan="2">
                        <input type="submit" value="로그인">
                        <input type="reset">
                     </td>
                  </tr>
               </table>
               
            </form>
      <% } %>
	</div>
</body>
</html>

