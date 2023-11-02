<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
   #wrap, h2, table{
      margin: auto;
      text-align: center;
   }

</style>
</head>
<body>
   <div id="wrap">
      <h2>회원가입페이지</h2>
      <form action="../LoginService" method="post">
         <table>
         	
         	<tr>
               <td>아이디</td>
               <td><input type="text" name="user_id"></td>
            </tr>
            <tr>
               <td>PW</td>
               <td><input type="password" name="user_pw"></td>
               
            </tr>
            
            <tr>
               <td colspan="2">
                  <input type="submit" value="로그인">
                  <input type="reset">
               </td>
            </tr>
            
            
            
            
         </table>
      </form>
   </div>
</body>
</html>

