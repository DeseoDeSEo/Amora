<%@page import="model.ShReportDTO"%>
<%@page import="model.ShLikeDTO"%>
<%@page import="model.ReportDAO"%>
<%@page import="model.LikesDAO"%>
<%@page import="model.ShDTO"%>
<%@page import="model.ShDAO"%>
<%@page import="model.UsersDTO"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="model.BoardDTO"%>
<%@ page import="model.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<!--
	Traveler by freehtml5.co
	Twitter: http://twitter.com/fh5co
	URL: http://freehtml5.co
-->
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Traveler &mdash; Free Website Template, Free HTML5
	Template by FreeHTML5.co</title>
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
<link rel="stylesheet" href="css/themify-icons.css">
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
							<li class="has-dropdown"><a href="community.jsp">커뮤니티</a>
								<ul class="dropdown">
									<li><a href="community.jsp">전체 글 보기</a></li>
									<li><a href="request.jsp">건의사항</a></li>
									<li><a href="hobby.jsp">취미생활</a></li>
									<li><a href="baby.jsp">육아</a></li>
									<li><a href="find.jsp">찾아주세요</a></li>
									<li><a href="food.jsp">동네맛집</a></li>
								</ul></li>
							<li class="has-dropdown"><a href="secondhand.jsp">중고거래/나눔</a>
								<ul class="dropdown">
									<li><a href="secondhand.jsp">중고거래</a></li>
									<li><a href="share.jsp">나눔</a></li>
								</ul></li>
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
			role="banner" style="background-image: url(images/bgapt.jpg)">
			<div class="overlay"></div>
			<div class="gtco-container">
				<div class="row">
					<div class="col-md-12 col-md-offset-0 text-center">
						<div class="row row-mt-15em">
							<div class="col-md-12 mt-text animate-box"
								data-animate-effect="fadeInUp">
								<h1>나눔</h1>
							</div>

						</div>

					</div>
				</div>
			</div>
			-->
		</header>

	
		<%
		UsersDTO info = (UsersDTO) session.getAttribute("info");
		String nickname = info.getNickname();
		LikesDAO ldao = new LikesDAO();
		int com_Num = 0;

		int sh_Num = 0;

		if (request.getParameter("sh_Num") != null) {
			sh_Num = Integer.parseInt(request.getParameter("sh_Num")); // COMNUM 게시글 번호 
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
		System.out.println(sh_Num);
		ShDTO dto = new ShDAO().getShDTOShare(sh_Num);
		%>
		<%--  <nav class="navbar navbar-default">
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
   </nav> --%>
		<div class="container">
			<div class="row">
				<table class="table table-striped"
					style="text-align: center; border: 1px solid #dddddd; position: relative; top: 100px;">
					<thead>
						<tr>
							<th colspan="3"
								style="background-color: #eeeeee; text-align: center;">게시판
								글보기</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td style="width: 20%;">글 제목</td>
							<td colspan="2"><%=dto.getSh_title().replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n",
		"<br>")%></td>
						</tr>
						<tr>
							<td>작성자</td>
							<td colspan="2"><%=dto.getNickname()%></td>
						</tr>
						<tr>
							<td>작성일자</td>
							<td colspan="2"><%=dto.getSh_date().substring(0, 11) + dto.getSh_date().substring(11, 13) + "시"
		+ dto.getSh_date().substring(14, 16) + "분 "%></td>
						</tr>

						<%--파일 url 을 불러오기 --%>
						<%--상세보기 클릭에서 넘어온 sh_Num을 받아서 불러왔다 --%>
						<%
						ShDAO shDAO = new ShDAO();
						String imagePath = shDAO.sh_img_share(sh_Num);

						if (imagePath == null) {
							imagePath = "/ad_img/빈사진.png";
						}
						%>


						<tr>
							<td>내용</td>
							<td colspan="2" style="min-height: 200px; text-align: left;"><%=dto.getSh_content().replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n",
		"<br>")%></td>
						</tr>

						<%-- 사진을 불러옵니다 --%>
						<tr>
							<td>사 진</td>
							<td colspan="2"><img class="card-img-top"
								src="${pageContext.request.contextPath}<%=imagePath%>" alt="..." /></td>
						</tr>


					</tbody>
				</table>
			</div>
		</div>
		<a style="position: relative; top: 100px; left: 1600px;"
			href="share.jsp" class="btn btn-primary">목록</a>
		<!--  좋아요와 신고 기능-->

		<!--  여기서부터는 좋아요,신고 기능입니다 -->
		<%!public Boolean isLike(String userId, int comNum) {
		LikesDAO ldao = new LikesDAO();
		return ldao.isComUserLiked(userId, comNum);
	}%>
		<!-- 신고기능 -->
		<%!public Boolean isReport(String userId, int comNum) {
		ReportDAO rdao = new ReportDAO();
		return rdao.isComUserReported(userId, comNum);
	}%>

		<%
		// 좋아요와 신고기능.
		ShLikeDTO shldto = new ShLikeDTO(info.getId(), sh_Num);
		int shLikenum = ldao.getShLike_num(dto);
		ShReportDTO srdto = new ShReportDTO(info.getId(), sh_Num);

		String likeServlet = "";
		String likeUrl = "";
		String reportServelt = "";
		String reportUrl = "";
		if (isLike(info.getId(), com_Num)) {
			likeServlet = "BoardunLikeUpdateAction";
			likeUrl = "./images/liked.png";
		} else {
			likeServlet = "BoardLikeUpdateAction";
			likeUrl = "./images/unLiked.png";
		}
		if (isReport(info.getId(), com_Num)) {
			reportServelt = "BoardUnReportUpdateAction";
			reportUrl = "./images/reported.png";
		} else {
			reportServelt = "BoardReportUpdateAction";
			reportUrl = "./images/unReported.png";
		}
		%>
		<%-- 좋아요 상태를 추적하기 위한 숨겨진 필드 추가 --%>
		<input type="hidden" id="isLiked"
			value='<%=isLike(info.getId(), com_Num) ? "true" : "false"%>'>

		<%-- 좋아요 버튼 이미지 --%>
		<form id="like_form" action="<%=likeServlet%>" method="post">
			<input type="hidden" name="command" value="like_it"> <input
				type="hidden" name="com_Num" value=<%=com_Num%>>
			<div>
				<button type="button" onclick="toggleLike()" class="like-button">
					<img id="likeImage" src="<%=likeUrl%>" alt="좋아요!"
						class="like-image">
				</button>
			</div>
			<div id="like_result">${board.like_it}</div>
		</form>
		<%
		int likes = shDAO.getSh_Likes(sh_Num);
		%>
		<span>좋아요<%=likes%></span>



		<%-- 신고 버튼 이미지 --%>
		<!-- 신고 버튼 -->
		<form id="report_form" action="<%=reportServelt%>" method="post">
			<input type="hidden" name="command" value="like_it"> <input
				type="hidden" name="com_Num" value=<%=com_Num%>>
			<div>
				<button type="button" onclick="toggleReport()" class="report-button">
					<img id="reportImage" src="<%=reportUrl%>" alt="신고"
						class="report-image">
				</button>
			</div>
			<div id="report_result">${board.report_it}</div>
		</form>

		<%
		int reports = shDAO.getSh_Report(sh_Num);
		%>
		<!-- 신고 상태를 추적하기 위한 숨겨진 필드 추가 -->
		<input type="hidden" id="isReported"
			value='<%=isReport(info.getId(), com_Num) ? "true" : "false"%>'>
		<div class="post-actions">








			<!-- 삭제하려면 db의 닉네임이랑 게시글 닉네임이랑 같아야함.  -->
			<%
			if (nickname != null && nickname.equals(dto.getNickname())) {
			%>
			<a href="share_update.jsp?sh_Num=<%=sh_Num%>" class="btn btn-primary">수정</a>
			<a onclick="return confirm('정말로 삭제하시겠습니까?')"
				href="share_deleteAction?sh_Num=<%=sh_Num%>&section=<%=dto.getSection()%>"
				class="btn btn-primary">삭제</a>
			<!-- 삭제의 기준이 되는 sh_Num과 com_Category의 값을 무조건 받아야함,그래서 전달도 같이 해줘야함. -->

			<%
			}
			%>



			<!-- 여기부터 푸터!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! -->
			<footer style="position: relative; top: 100px; left: 100px;"
				id="gtco-footer" role="contentinfo">
				<div class="gtco-container">
					<div class="row row-p	b-md">

						<div class="col-md-4">
							<div class="gtco-widget">
								<h3>About Us</h3>
								<p>아모라 아파트</p>
							</div>
						</div>

						<div style="position: relative; right: 2000px;"
							class="col-md-2 col-md-push-1">
							<div class="gtco-widget">
								<h3>관리사무소</h3>
								<ul class="gtco-footer-links">
									<li><a href="#">사과시 복숭아동 아모라아파트</a></li>
								</ul>
							</div>
						</div>

						<div style="position: relative; right: 150px;"
							class="col-md-3 col-md-push-1">
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
							</p>
						</div>
					</div>

				</div>
			</footer>
			<!-- </div> -->
		</div>

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

