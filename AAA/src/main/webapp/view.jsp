<%@page import="model.ReportDAO"%>
<%@page import="model.ComReportDTO"%>
<%@page import="model.ComcDAO"%>
<%@page import="model.ComcDTO"%>
<%@page import="java.util.ArrayList"%>

<%@page import="model.ComLikeDTO"%>
<%@page import="model.LikesDAO"%>
<%@page import="model.UsersDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="model.BoardDTO" %>
<%@ page import="model.BoardDAO" %>
<link rel="stylesheet" href= "css/bootstrap.css">



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>JSP 게시판 웹 사이트</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description"
   content="Free HTML5 Website Template by FreeHTML5.co" />
<meta name="keywords"
   content="free website templates, free html5, free template, free bootstrap, free website template, html5, css3, mobile first, responsive" />
<meta name="author" content="FreeHTML5.co" />

<!-- Facebook and Twitter integration -->
<meta property="og:title" content="" />
<meta property="og:image" content="" />
<meta property="og:url" content="" />
<meta property="og:site_name" content="" />
<meta property="og:description" content="" />
<meta name="twitter:title" content="" />
<meta name="twitter:image" content="" />
<meta name="twitter:url" content="" />
<meta name="twitter:card" content="" />

<link href="https://fonts.googleapis.com/css?family=Lato:300,400,700"
   rel="stylesheet">

<!-- Animate.css -->
<link rel="stylesheet" href="css/animate.css">
<!-- Icomoon Icon Fonts-->
<link rel="stylesheet" href="css/icomoon.css">
<!-- Themify Icons-->
<link rel="stylesheet" href="css/themify-icons.css">
<!-- Bootstrap  -->
<link rel="stylesheet" href="css/bootstrap.css">

<!-- Magnific Popup -->
<link rel="stylesheet" href="css/magnific-popup.css">

<!-- Magnific Popup -->
<link rel="stylesheet" href="css/bootstrap-datepicker.min.css">

<!-- Owl Carousel  -->
<link rel="stylesheet" href="css/owl.carousel.min.css">
<link rel="stylesheet" href="css/owl.theme.default.min.css">

<!-- Theme style  -->
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/stylewrite.css">

<!-- Modernizr JS -->
<script src="js/modernizr-2.6.2.min.js"></script>
<!-- FOR IE9 below -->
<!--[if lt IE 9]>
   <script src="js/respond.min.js"></script>
   <![endif]-->

<style>
    .post-actions {
        display: flex;
        align-items: center;
        justify-content: space-between; /* 좌우 여백을 동일하게 분배 */
    }

    .action-buttons {
        display: flex;
        gap: 10px; /* 버튼 간의 여백 설정 */
    }

    .like-button {
        padding: 0;
        display: inline-block;
        background-color : white;
        border: none;
    }

   .report-button {
        padding: 0;
        display: inline-block;
        background-color : white;
        border: none;
    }
    .like-image  {
        width: 35px;
        height: 35px;
    }
    .report-image {
        width: 35px;
        height: 35px;
    }
    #com_content{
       resize : none;
       
    }
    .border-none{
       border : none;
       background-color: white;
    }
    
    .dadglespan{
       font-size: 20pt;
    }
    .datespan{
       font-size: 5pt;
    }
    .dadgleContent{
       font-size :15pt;
    }
    #dadgulWrite{
       font-size: 15px;
    }
    .deWrite{
   
       color: inherit;
    }
    .deWrite:hover{
        text-decoration: none;
    }
    
</style>


</head>
<body>
   
   <div class="gtco-loader"></div>
   <div id="page">
      <!-- <div class="page-inner"> -->
      <nav class="gtco-nav" role="navigation">
         <div>
            <div class="row">
               <div class="col-sm-4 col-xs-12">
                  <div id="gtco-logo">
                     <a href="index.jsp">AMORA</a>
                  </div>
               </div>
               <div class="col-xs-8 text-right menu-1">
                  <ul>
                     <li class ="has-dropdown"><a href="community.jsp">커뮤니티</a>
                        <ul class="dropdown">
                            <li><a href="community.jsp">전체 글 보기</a></li>
                           <li><a href="request.jsp">건의사항</a></li>
                           <li><a href="hobby.jsp">취미생활</a></li>
                           <li><a href="baby.jsp">육아</a></li>
                           <li><a href="find.jsp">찾아주세요</a></li>
                           <li><a href="food.jsp">동네맛집</a></li>
                        </ul>
                     </li>
                     <li class ="has-dropdown"> <a href="secondhand.jsp">중고거래/나눔</a>   
                        <ul class="dropdown">
                           <li><a href="secondhand.jsp">중고거래</a></li>
                           <li><a href="share.jsp">나눔</a></li>
                        </ul>
                     </li>
                     <li><a href="contact.jsp">공동구매</a></li>
                     <li><a href="promotion.jsp">홍보</a></li>
                     <li><a href="pricing.jsp">공지사항</a></li>
                     <li><a href="destination.jsp">마이페이지</a></li>
                  </ul>
               </div>
            </div>

         </div>
      </nav>

      <header id="gtco-header" class="gtco-cover gtco-cover-sm"
         role="banner" style=" background-image: url(images/bgapt.jpg)">
      <div class="overlay"></div>
         <div class="gtco-container">
             <div class="row">
               <div class="col-md-12 col-md-offset-0 text-center">
                  <div class="row row-mt-15em">
                     <div class="col-md-12 mt-text animate-box"
                        data-animate-effect="fadeInUp">
                        <h1> 커뮤니티</h1>
                     </div>

                  </div>

               </div>
            </div>
         </div> -->
      </header>

   <!-- <div class="gtco-section border-bottom" >
      <div class="gtco-container">
         <div class="row">
            <div class="col-md-8 col-md-offset-2 text-center gtco-heading">
               <h2>Choose The Best Plan For You</h2>
               <p>Join over 1 Million of users. Dignissimos asperiores vitae velit veniam totam fuga molestias accusamus alias autem provident. Odit ab aliquam dolor eius.</p>
            </div>
         </div>
         <div class="row">
            <div class="col-md-4">
               <div class="price-box">
                  <h2 class="pricing-plan">Starter</h2>
                  <div class="price"><sup class="currency">$</sup>7<small>/mo</small></div>
                  <p>Basic customer support for small business</p>
                  <hr>
                  <ul class="pricing-info">
                     <li>10 projects</li>
                     <li>20 Pages</li>
                     <li>20 Emails</li>
                     <li>100 Images</li>
                  </ul>
                  <a href="#" class="btn btn-default btn-sm">Get started</a>
               </div>
            </div>
            <div class="col-md-4">
               <div class="price-box">
                  <h2 class="pricing-plan">Regular</h2>
                  <div class="price"><sup class="currency">$</sup>19<small>/mo</small></div>
                  <p>Basic customer support for small business</p>
                  <hr>
                  <ul class="pricing-info">
                     <li>15 projects</li>
                     <li>40 Pages</li>
                     <li>40 Emails</li>
                     <li>200 Images</li>
                  </ul>
                  <a href="#" class="btn btn-default btn-sm">Get started</a>
               </div>
            </div>
            <div class="col-md-4">
               <div class="price-box popular">
                  <div class="popular-text">Popular</div>
                  <h2 class="pricing-plan">Plus</h2>
                  <div class="price"><sup class="currency">$</sup>79<small>/mo</small></div>
                  <p>Basic customer support for small business</p>
                  <hr>
                  <ul class="pricing-info">
                     <li>Unlimitted projects</li>
                     <li>100 Pages</li>
                     <li>100 Emails</li>
                     <li>700 Images</li>
                  </ul>
                  <a href="#" class="btn btn-primary btn-sm">Get started</a>
               </div>
            </div>
         </div>
      </div> -->
   

   <% 
      
      UsersDTO info =(UsersDTO)session.getAttribute("info"); 
         if (info ==null){
            response.sendRedirect("index.jsp");
         }
      String nickname = info.getNickname();
      BoardDAO bdao = new BoardDAO();
      LikesDAO ldao = new LikesDAO();
      int com_Num = 0;
      
      if (request.getParameter("com_Num") != null) {
         com_Num = Integer.parseInt(request.getParameter("com_Num")); // COMNUM 게시글 번호 
         //SQL ROWNUM ORDER BY COM_DATE DESC;
         
         //SELECT COM_CATEGORY, COM_NUM, COM_TITLE, NICKNAME, COM_DATE, COM_VIEWS FROM COMMUNITY ORDER BY COM_NUM DESC;
         
      }
       if (com_Num == 0) {
         PrintWriter script = response.getWriter();
         script.println("<script>");
         script.println("alert('유효하지 않는 글입니다.')");
         script.println("location.href = 'community.jsp'");
         script.println("history.back()");
         script.println("</script>");
       }
      
      BoardDTO dto = new BoardDAO().getBoardDTO(com_Num);
      
   %>
 <!--  <nav class="navbar navbar-default">
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
            <li class="active"><a href="community.jsp">게시판</a></li>
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
   </nav> -->

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
                  <td colspan="2"><%= dto.getCom_Title().replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">","&gt;").replaceAll("\n", "<br>") %></td>
               </tr>
               <tr>
                  <td>카테고리</td>
                  <td colspan="2"><%= dto.getCom_Category()%></td>   
               </tr>
               <tr>
                  <td>작성자</td>
                  <td colspan="2"><%= dto.getNickname() %></td>
               </tr>
               <tr>
                  <td>작성일자</td>
                  <td colspan="2"><%= dto.getCom_Date().substring(0, 11) + dto.getCom_Date().substring(11, 13) + "시" + dto.getCom_Date().substring(14, 16) + "분 " %></td>
               </tr>
               <tr>
                  <td>내용</td>
                  <td colspan="2" style="min-height: 200px; text-align: left;"><%= dto.getCom_Content().replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">","&gt;").replaceAll("\n", "<br>") %></td>
             
                         <%--파일 url 을 불러오기 --%>
               <%--상세보기 클릭에서 넘어온 ad_Num을 받아서 불러왔다 --%>
            <%
               BoardDAO dao = new BoardDAO();
               String imagePath = dao.com_img(com_Num);
                                     
               if(imagePath == null){
                 imagePath = "/sh_img/빈사진.jpg";
               }
            %>
            
            <%-- 사진을 불러옵니다 --%>
            <tr>
                  <td colspan="2">사 진</td>
                  <div style  = "width:320px; height:470px;"></div>
                  <td colspan="2" style="max-height:100px;"><img style="object-fit:cover; width: 100%; height:100%;"class="card-img-top" src="${pageContext.request.contextPath}<%=imagePath%>"  alt="..." /></td>        
           </tr>
            </tbody>
         </table>
</div>
</div>
                
         
         
         <!--  여기서부터는 좋아요,신고 기능입니다 -->
            <%! public Boolean isLike(String userId, int comNum){
      LikesDAO ldao = new LikesDAO();
      return ldao.isComUserLiked(userId, comNum);
  }
%>
<!-- 신고기능 -->
<%! public Boolean isReport(String userId, int comNum){
      ReportDAO rdao = new ReportDAO();
      return rdao.isComUserReported(userId, comNum);
  }
%>

         <%
         // 좋아요와 신고기능.
         ComLikeDTO cldto =new ComLikeDTO(info.getId(),com_Num );
         int comLikenum =ldao.getComLike_num(cldto);
         ComReportDTO crdto = new ComReportDTO(info.getId(),com_Num); 
        
         String likeServlet = "";
         String likeUrl ="";
         String reportServelt ="";
         String reportUrl ="";
         if (isLike(info.getId(), com_Num)){
            likeServlet = "BoardunLikeUpdateAction";
            likeUrl = "./images/liked.png";
         }   
            else{
            likeServlet = "BoardLikeUpdateAction";
            likeUrl = "./images/unLiked.png";
         }
         if (isReport(info.getId(), com_Num)){
            reportServelt = "BoardUnReportUpdateAction";
            reportUrl = "./images/reported.png";
         }   
            else{
           reportServelt = "BoardReportUpdateAction";
            reportUrl = "./images/unReported.png";
         }
         %>
        <%-- 좋아요 상태를 추적하기 위한 숨겨진 필드 추가 --%>
<input type="hidden" id="isLiked" value='<%= isLike(info.getId(), com_Num) ? "true" : "false" %>'>

<%-- 좋아요 버튼 이미지 --%>
<form id="like_form" action="<%= likeServlet %>" method="post">
   <input type="hidden" name="command" value="like_it">
   <input type="hidden" name="com_Num" value=<%=com_Num%>>
   <div><button type="button" onclick="toggleLike()" class ="like-button" ><img id="likeImage" src="<%= likeUrl %>" alt="좋아요!" class ="like-image"></button></div>
   <div id="like_result">${board.like_it}</div>
</form>
<%int likes= bdao.getCom_Likes(com_Num); %>
<span>좋아요<%=likes%></span>



<%-- 신고 버튼 이미지 --%>
<!-- 신고 버튼 -->
<form id="report_form" action="<%= reportServelt %>" method="post">
   <input type="hidden" name="command" value="like_it">
   <input type="hidden" name="com_Num" value=<%=com_Num%>>
   <div><button type="button" onclick="toggleReport()" class ="report-button" ><img id="reportImage" src="<%= reportUrl %>" alt="신고" class ="report-image" ></button></div>
   <div id="report_result">${board.report_it}</div>
</form>

<%int reports= bdao.getCom_Report(com_Num); %>
<!-- 신고 상태를 추적하기 위한 숨겨진 필드 추가 -->
<input type="hidden" id="isReported" value='<%= isReport(info.getId(), com_Num) ? "true" : "false" %>'>
<div class="post-actions">
          <div class="action-buttons">

         <%
         if (nickname != null && nickname.equals(dto.getNickname())) {
             %>
                  <a href="community.jsp" class="btn btn-primary">목록</a>
                   <a href="update.jsp?com_Num=<%= com_Num %>" class="btn btn-primary">수정</a>
                   <a onclick="return confirm('정말로 삭제하시겠습니까?')" href="deleteAction?com_Num=<%= com_Num %>&com_Category=<%= dto.getCom_Category() %>" class="btn btn-primary">삭제</a>
                   <!-- 삭제의 기준이 되는 com_Num과 com_Category의 값을 무조건 받아야함,그래서 전달도 같이 해줘야함. -->
             <%
                }
             %>      
         </div>
</div>

<%
    int comNum = Integer.parseInt(request.getParameter("com_Num"));// URL 또는 폼에서 "comNum" 파라미터를 가져와 정수로 변환 여기서는 url
    ComcDAO comcdao= new ComcDAO();
    ArrayList<ComcDTO> list = comcdao.vogiReply(comNum); //계층형으로 처리된 리스트

%>
<!--  여기서부터는 댓글창입니다. -->
<div class = "reply.wrap">
   <div class ="reply index">
   <hr>
   <h3>댓글창</h3>
      <button onclick="showReplyForm()" class= "border-none" id = "dadgulWrite">댓글 쓰기</button>
   <form action ="WriteComReply" method ="post"  id ="replyForm" style ="display: none;">
   <input type ="hidden" name ="com_Num" value ="<%= com_Num %>">
   <textarea name="com_content" cols="150" rows ="4" id = "com_content"></textarea>
    <button type="submit" class="border-none">댓글 등록</button>
   </form>
   <br>
   </div>
   <br>
   <div class = "replies"> <!-- 댓글 쓰기 창 아래에 이제 댓글 보는 칸 -->
    <% for (int i = 0 ; i< list.size(); i++) { %>
    
   <% if(list.get(i).getDadat() == 0) { %> <!-- 예를 들어 dadat가 0 일때만 대댓글 작성을 허용 --> 
    <div class = "dadgle">
    <span class = "dadglespan"><%= list.get(i).getNickname() %></span>
    <span class ="datespan" ><%= list.get(i).getComcDate()%></span>
    <br>
    <span class = "dadgleContent"><%=list.get(i).getComcContent() %></span>
    <br>
          <div>
          <br>
          <%if(!list.get(i).getComcContent().equals("삭제된 댓글 입니다.")){ %>
            <button class = "border-none" onclick="showSubReplyForm(<%=i%>)" style="flex-right">답글 쓰기</button>
                <form action="WriteComSubReply" method="post" class="SubReplyForm" id="SubReplyForm_<%= i %>" style="display: none;">
                    <input type="hidden" name="com_Num" value="<%=com_Num%>">
                    <input type = "hidden" name = "pcomcnum" value ="<%=list.get(i).getComcNum()%>" >
                    <textarea name="subcomcContent" cols="150" rows="4"></textarea>
                    <button type="submit" class="border-none">답글 등록</button>
                    </form>
          
          <% } %>
          <% if(list.get(i).getNickname().equals(info.getNickname())){ %>
          <!--  댓글 수정 -->
          <%if(!list.get(i).getComcContent().equals("삭제된 댓글 입니다.")){ %>
            <button onclick="showReplyUpdateForm()" style ="flex-right" class = "border-none">댓글수정</button>
            <form action ="UpdateReply" method ="post" class ="ReplyUpdateForm" id = "ReplyUpdateForm" style= "display: none;">
            <input type ="hidden" name ="com_Num" value ="<%= com_Num %>">
            <input type = "hidden" name = "com_Likes" value ="<%=likes%>">
                    <input type = "hidden" name = "comc_Num" value ="<%=list.get(i).getComcNum()%>" >
                    <textarea name="comcContent" cols="150" rows="4"></textarea>
                    <button type="submit" class="border-none">댓글 수정</button>
                    </form>
            <!--  댓글 삭제 -->
           <a class= "deWrite" href="javascript:replyDelete()">댓글 삭제</a>
            <form action ="DeleteComcReply" method ="post"  id="repplyFrm">
            <input type ="hidden" name ="com_Num" value ="<%= com_Num %>">
                    <input type = "hidden" name = "comc_Num" value ="<%=list.get(i).getComcNum()%>">
                    <input type = "hidden" name = "com_Likes" value ="<%=likes%>">
                    <!-- <input type="submit" class="border-none" value ="댓글 삭제"> -->
             </form>
            
            
            </div>
            <%} %>
             <% } %>       
            
    </div>
        <%}else{ %>
        
        
    <div class = "dadgle">
        <span style ="padding-right : 100px;"></span>
    
    <span class = "dadglespan"><%= list.get(i).getNickname() %></span>
    
    <span class ="datespan" ><%= list.get(i).getComcDate()%></span>
    <br>
        <span style ="padding-right : 100px;"></span>
    
    <span class = "dadgleContent"><%=list.get(i).getComcContent() %></span>
            <% if(list.get(i).getNickname().equals(info.getNickname())){ %>
          <span style ="padding-right : 100px;"></span>
          <!--  댓글 수정 -->
          <%if(!list.get(i).getComcContent().equals("삭제된 댓글 입니다.")){ %>
          <br>
          <span style ="padding-right : 100px;"></span>
            <button class ="border-none" onclick="showReReplyUpdateForm(<%=i%>)" style = "flex-right">답글 수정</button>
            <form action ="UpdateReply" method ="post" class ="ReplyUpdateForm" id = "ReReplyUpdateForm_<%= i %>" style= "display: none;">
                  <input type="hidden" name="comc_Num" value="<%=list.get(i).getComcNum()%>">
                    <input type ="hidden" name ="com_Num" value ="<%= com_Num %>">
                    <textarea name="comcContent" cols="150" rows="4"></textarea>
                    <input type = "hidden" name = "com_Likes" value ="<%=likes%>">
                    <button type="submit" class="border-none">댓글 수정</button>
                    </form>
           <!--  댓글 삭제 -->
           <a class= "deWrite" href="javascript:replyDelete1()">답글 삭제</a>
            <form action ="DeleteComcReply" method ="post" id="repplyFrm1">
            <input type ="hidden" name ="com_Num" value ="<%= com_Num %>">
                    <input type = "hidden" name = "comc_Num" value ="<%=list.get(i).getComcNum()%>">
                    <input type = "hidden" name = "com_Likes" value ="<%=likes%>">
                    <!-- <input type="submit" class="border-none" value ="댓글 삭제"> -->
                    </form>
            
            </div>
             <%} %>
             <% } %>     
            
            <%} %>      <!-- 예를 들어 dadat가 0 일때만 대댓글 작성을 허용 -->
    <br>
    </div>
        
    <hr>
   <%} %>
   
    
    <%if(list.size()>=6){%>
    <hr>
   <button onclick="showReplyForm6()" class = "border-none">댓글 쓰기</button>
   <form action ="WriteComReply" method ="post"  id ="replyForm6" style ="display: none;">
   <input type ="hidden" name ="com_Num" value ="<%= com_Num %>">
   <textarea name="comcContent" cols="150" rows ="3" id = "com_content"></textarea>
    <button type="submit" class="reply-write">댓글 등록</button>
   </form>
    <% }%>
   </div> 
   
</div>

<script>
function replyDelete(){
   alert("OK");
   document.getElementById('repplyFrm').submit();   
}
function replyDelete1(){
   document.getElementById('repplyFrm1').submit();      
}
function toggleLike() {
    // 좋아요 상태를 가져옵니다.
    var isLiked = document.getElementById('isLiked').value === "true";
    
    // 폼과 이미지 엘리먼트를 가져옵니다.
    var likeForm = document.getElementById('like_form');
    var likeImage = document.getElementById('likeImage');
    
    // 좋아요 상태를 변경하고 이미지를 업데이트합니다.
    if (isLiked) {
        likeImage.src = "./images/unliked.png";
        likeForm.action = "BoardUnLikeUpdateAction";
        document.getElementById('isLiked').value = "false";
    } else {
        likeImage.src = "./images/liked.png";
        likeForm.action = "BoardLikeUpdateAction";
        document.getElementById('isLiked').value = "true";
    }
    
    // 폼을 제출합니다.
    likeForm.submit();
}


function toggleReport() {
    // 신고 상태를 가져옵니다.
    var isReported = document.getElementById('isReported').value === "true";

    // 신고 상태에 따라 확인 메시지를 선택합니다.
    var confirmMessage = isReported ? "이 글의 신고를 취소하시겠습니까?" : "이 글을 신고하시겠습니까?";
    var userConfirm = confirm(confirmMessage);
    if (!userConfirm){
       return; // 사용자가 취소를 선택하면 함수를 종료
    }else{
       

    // 폼과 이미지 엘리먼트를 가져옵니다.
    var reportForm = document.getElementById('report_form');
    var reportImage = document.getElementById('reportImage');
    
    // 신고 상태를 변경하고 이미지를 업데이트합니다.
    if (isReported) {
        reportImage.src = "./images/unReported.png";
        reportForm.action = "BoardUnReportUpdateAction";
        document.getElementById('isReported').value = "false";
    } else {
        reportImage.src = "./images/Reported.png";
        reportForm.action = "BoardReportUpdateAction";
        document.getElementById('isReported').value = "true";
    }
    
    // 폼을 제출합니다.
    reportForm.submit();
    }alert('반영되었습니다.')
}

function showReplyForm() {
    // 폼의 표시 상태를 변경합니다.
    var form = document.getElementById('replyForm');
    if(form.style.display === 'none') {
        form.style.display = 'block';
    } else {
        form.style.display = 'none';
    }
}

function showReplyForm6() {
    // 폼의 표시 상태를 변경합니다.
    var form = document.getElementById('replyForm6');
    if(form.style.display === 'none') {
        form.style.display = 'block';
    } else {
        form.style.display = 'none';
    }
}
function showSubReplyForm(index) {
    // 폼의 표시 상태를 변경합니다.
    var form = document.getElementById('SubReplyForm_' + index);
    if(form.style.display === 'none') {
        form.style.display = 'block';
    } else {
        form.style.display = 'none';
    }
}
function showReplyUpdateForm() {
    // 폼의 표시 상태를 변경합니다.
    var form = document.getElementById('ReplyUpdateForm');
    if(form.style.display === 'none') {
        form.style.display = 'block';
    } else {
        form.style.display = 'none';
    }
}

function showReReplyUpdateForm(index) {
    // 폼의 표시 상태를 변경합니다.
    var form = document.getElementById('ReReplyUpdateForm_' + index);
    if(form.style.display === 'none') {
        form.style.display = 'block';
    } else {
        form.style.display = 'none';
    }
}

</script>

   <footer style="position:relative; top: 100px; left: 100px;" id="gtco-footer" role="contentinfo">
      <div class="gtco-container">
         <div class="row row-p   b-md">

            <div class="col-md-4">
               <div class="gtco-widget">
                  <h3>About Us</h3>
                  <p>아모라 아파트</p>
               </div>
            </div>

            <div style ="position:relative; right:2000px;"class="col-md-2 col-md-push-1">
               <div class="gtco-widget">
                  <h3>관리사무소</h3>
                  <ul class="gtco-footer-links">
                     <li><a href="#">사과시 복숭아동 아모라아파트</a></li>
                  </ul>
               </div>
            </div>

            <div style="position: relative; right: 150px;"class="col-md-3 col-md-push-1">
               <div class="gtco-widget">
                  <h3>Get In Touch</h3>
                  <ul class="gtco-quick-contact">
                     <li><a href="#"><i class="icon-phone"></i> +1 234 567
                           890</a></li>
                  </ul>
               </div>
            </div>

         </div>

         <div class="row copyright">
            <div class="col-md-12">
               <p class="pull-left">
                  <small class="block">&copy; 2016 Free HTML5. All Rights
                     Reserved.</small> <small class="block">Designed by <a
                     href="https://freehtml5.co/" target="_blank">freehtml5.co</a>
                     Demo Images: <a href="http://unsplash.com/" target="_blank">Unsplash</a></small>
               </p>
               <p class="pull-right">
               <ul class="gtco-social-icons pull-right">
                  <li><a href="#"><i class="icon-twitter"></i></a></li>
                  <li><a href="#"><i class="icon-facebook"></i></a></li>
                  <li><a href="#"><i class="icon-linkedin"></i></a></li>
                  <li><a href="#"><i class="icon-dribbble"></i></a></li>
               </ul>
            </div>
         </div>

      </div>
   </footer>
   <!-- </div> -->

   <div class="gototop js-top">
      <a href="#" class="js-gotop"><i class="icon-arrow-up"></i></a>
   </div>

   <!-- jQuery -->
   <script src="js/jquery.min.js"></script>
   <!-- jQuery Easing -->
   <script src="js/jquery.easing.1.3.js"></script>
   <!-- Bootstrap -->
   <script src="js/bootstrap.min.js"></script>
   <!-- Waypoints -->
   <script src="js/jquery.waypoints.min.js"></script>
   <!-- Carousel -->
   <script src="js/owl.carousel.min.js"></script>
   <!-- countTo -->
   <script src="js/jquery.countTo.js"></script>

   <!-- Stellar Parallax -->
   <script src="js/jquery.stellar.min.js"></script>

   <!-- Magnific Popup -->
   <script src="js/jquery.magnific-popup.min.js"></script>
   <script src="js/magnific-popup-options.js"></script>

   <!-- Datepicker -->
   <script src="js/bootstrap-datepicker.min.js"></script>


   <!-- Main -->
   <script src="js/main.js"></script>
</body>
</html>