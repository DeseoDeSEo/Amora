<%@page import="model.UsersDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.UsersDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>User Information</title>
<style>
    body {
        font-family: Arial, sans-serif;
    }
    table {
        width: 100%;
        border-collapse: collapse;
    }
    th, td {
        border: 1px solid #ccc;
        padding: 8px;
        text-align: left;
    }
    th {
        background-color: #f2f2f2;
    }
    .approval-button {
        margin-top: 20px;
    }
    
    .custom-button {
   margin-right: 10px;
   margin-left: 10px;
   padding: 10px;
   border-radius: 5px;
   font-size: 16px;
   transition: background-color 0.3s, color 0.3s;
}

.custom-button:hover {
   background-color: #f0f0f0; /* 호버될 때 배경색 변경 */
   color: #333; /* 호버될 때 텍스트 색 변경 */
}
</style>
</head>
<body>
    <table>
        <tr>
            <th>ID</th>
            <th>Nickname</th>
            <th>Name</th>
            <th>Address</th>
            <th>Dongho</th>
            <th>Approval</th>
        </tr>
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
        %>
        <tr>
            <td><%= user.getId() %></td>
            <td><%= user.getNickname() %></td>
            <td><%= user.getName() %></td>
            <td><%= user.getAddr() %></td>
            <td><%= user.getDongho() %></td>
            <td><%= user.getApproval() %></td>
        </tr>
        <%
        }
        %>
    </table>
    <form action="Approval" method="post" class="approval-button">
        <button style="background-color: white;">전체승인하기</button>
    </form>
</body>
</html>