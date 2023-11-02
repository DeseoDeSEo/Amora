
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 웹 페이지의 출력을 생성하고 클라이언트 브라우저에게 보낼 수 있다  -->  
<%@page import="java.io.PrintWriter"%>

<!--데이터베이스 가져올수 있도록 bbs.Bbs가져온다 -->
<%@ page import="bbs.Bbs" %>
<!--데이터 베이스 접근객체 가져온다  -->
<%@ page import="bbs.BbsDAO" %>
<!DOCTYPE HTML>

<html>
<head>

<!--종권시작  -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name = "viewport" content="width=device-width", initial-scale="1">
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/custom.css">
<!--종권끝  -->


<meta charset="UTF-8">
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
<link rel="stylesheet" href="css/stylenotice.css">

<!-- Modernizr JS -->
<script src="js/modernizr-2.6.2.min.js"></script>
<!-- FOR IE9 below -->
<!--[if lt IE 9]>
	<script src="js/respond.min.js"></script>
	<![endif]-->






<style>

.custom-btn {
    background-color: gray; /* 버튼 배경색을 회색으로 변경 */
    color: white; /* 버튼 글자색을 흰색으로 변경 */
   
}   

 

</style>
</head>
<body>
		<%
		//로그인이 된사람들은 로그인정보를 담을 수 있도록 만들어줍니다.
		String userID = null;
	
	    // 현재session이 존재하는 사람이라면 String으로 형변환을 해서 userID에 넣는다
		if(session.getAttribute("userID") != null){
			//스트링형태로 형변환을 한다음에 세션에 있는값을 가져오게 한다 
			userID =(String)session.getAttribute("userID");
		}
	   
	    

		
		
		
		
		
		
		
		
		
		
		
		 // bbsID를 0으로 설정한다음에//매개변수로 넘어온 bbsID라는 매개변수가 존재한다면 
	    // bbsID는 형변환 후 request.getParameter 받는다 
	    int no_num = 0;
	    if(request.getParameter("no_num")!= null){
	    	no_num= Integer.parseInt(request.getParameter("no_num"));
	   
	    //request.getParameter("bbsID")를 겪은 bbsID가 0이라면  //bbsID로 정보를 받는다
	    }if(no_num == 0){
	    	
			PrintWriter script = response.getWriter();
			
			script.println("<script>");
			script.println("alert('유효하지 않는 글입니다')");
			//다시 bbs.jsp페이지로 이동을 한다  			
			script.println("location.href ='pricing.jsp'");
			script.println("</script>");
	    }
		//.getBbs(bbsID): BbsDAO 클래스의 getBbs 메서드를 호출합니다. 
		//메서드는 매개변수로 bbsID를 받아서 해당 ID에 해당하는 게시물 정보를 데이터베이스에서 가져옵니다. 이 정보는 Bbs 객체에 담기게 됩니다.
		//만약 유효한글이라면 구체적인 정보를 bbs인스턴스안에 담게한다 
		//!!!BbsDAO 클래스의 getBbs 메서드를 호출하여 bbsID를 데이터베이스에서 가져와 bbs 변수에 저장하는 작업을 수행합니다. 
		// bbs 변수를 통해 해당 게시물의 정보에 액세스할 수 있게 될 것입니다.
		 Bbs bbs = new BbsDAO().getBbs(no_num);
		
		%>



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
			role="banner" style="background-image: url(images/bgapt.jpg)">
			<div class="overlay"></div>
			<div class="gtco-container">
			 	<div class="row">
					<div class="col-md-12 col-md-offset-0 text-center">
						<div class="row row-mt-15em">
							<div class="col-md-12 mt-text animate-box"
								data-animate-effect="fadeInUp">
								<h1>공지사항</h1>
							</div>

						</div>

					</div>
				</div>
			</div> -->
		</header>

		  
		<p style="color: white;">글씨 흰색으로</p>
<!-- 이종권 여기시작 -->
		



		
				
	<!-- 테이블만들기 -->					
		</div>
	</nav>	
	<div class="container">
		<div class="row">

			<!-- table-striped 클래스는 Bootstrap에서 제공하는 스타일 클래스 중 하나로, 테이블의 행을 번갈아가며 서로 다른 배경 색으로 스트라이프 모양으로 스타일링합니다.  -->
		  	<table class="table table-striped" style="text-align:center; border:1px solid #dddddd">
		  		<!-- <thead>는 HTML 문서에서 테이블의 헤더(Header) 부분을 정의하는 태그입니다 -->
		  		<thead>
		  			<tr>
		  			    <!-- colspan="3" 3개의 열을 사용한다 -->
		  				<th colspan="3" style="background-color: #eeeeee; text-align: center;">게시판 글보기 </th>
		  			</tr>
		  		</thead>
		  		<tbody>
		  			<tr>
		  				<td style="width: 20%">글제목</td>
		  				<td colspan="2" ><%= bbs.getNo_title().replaceAll(" ","&nbsp;").replaceAll("<","&lt;").replaceAll(">", "&gt;").replaceAll("\n","<br>") %></td>
						<!-- 게시글 왼쪽으로 style="text-align: left;" -->			  			
		  			</tr>
		  			<tr>
		  				<td >작성자</td>
		  				<td colspan="2" ><%= bbs.getNickname().replaceAll(" ","&nbsp;").replaceAll("<","&lt;").replaceAll(">", "&gt;").replaceAll("\n","<br>") %></td>
		  			</tr>
		  			<tr>
		  				<td >작성일자</td>
		  				 <td colspan="2" ><%= bbs.getNo_date().substring(0,11) + bbs.getNo_date().substring(11, 13) + "시" + bbs.getNo_date().substring(14,16) + "분"  %></td>
                                
		  			</tr>
		  			<tr>
		  				<td >내용</td>
		  				<!-- min-height:200px; 최소높이 200px; -->
		  				<td colspan="2" style="height: 200px; text-align: left;"><%= bbs.getNo_content().replaceAll(" ","&nbsp;").replaceAll("<","&lt;").replaceAll(">", "&gt;").replaceAll("\n","<br>") %></td>
	
		  			</tr>		  	
		  		</tbody>
		  	<!-- "btn btn-primary puu-right" 하나의 버튼이 오른쪽에 있다 -->
		  	<!-- 글쓰기 버튼을 누르면 제출이 된다 -->	  	
		  		
			</table>
			<!-- 목록으로 돌아사는 목록 버튼 만들어준다  -->
			<a href= "pricing.jsp" class="btn btn-arraw-right custom-btn">목록</a> 
			<%
			//아모라//관리자가 아니면 수정삭제 못하게 한다 
			//글작성자가 본인이라면 글을 수정과 삭제를 할수 있는 기능  
			//userID 현재사용자가 bbs.getUserID() 글의 작성자와 동일하다면 --> a href="update.jsp?bbsID=<%= bbsID 이게 뜬다 
			//역할이 관리자이면 수정 과 삭제를 할 수 있다 
			  if("A".equals(bbs.getRole())){
			%>	  
				<a href="ntc_update.jsp?no_num=<%=no_num %>" class="btn btn-arraw-right custom-btn">수정</a>
				
                <!--onclick="return confirm('정말로 삭제하시겠습니까?')" 버튼을 통해서 삭제를 메세지를 확인한다  -->
                <a onclick="return confirm('정말로 삭제하시겠습니까?')" href="ntc_deleteAction.jsp?no_num=<%=no_num %>" class="btn btn-arraw-right custom-btn" ">삭제</a>
			<% 	  
			  }
			%>

		
	  </div>	
	</div>



	<!-- 애니메이션을 담당하게 될 자바스크립트 참조 제이쿼리를 특정 홈페이지에서 가져올수 있도록한다  -->
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<!-- 부트스트랩에서 제공해주는 자바스크립트 파일또한 넣어준다 
		이 파일안에있는 부트스트랩을 참조하겠다라는 의미  -->
	<script src="js/bootstrap.js"></script>


<!-- 이종권 여기끝 -->




			<footer id="gtco-footer" role="contentinfo">
				<div class="gtco-container">
					<div class="row row-p	b-md">

						<div class="col-md-4">
							<div class="gtco-widget">
								<h3>About Us</h3>
								<p>아모라 아파트</p>
							</div>
						</div>

						<div class="col-md-2 col-md-push-1">
							<div class="gtco-widget">
								<h3>Destination</h3>
								<ul class="gtco-footer-links">
									<li><a href="#">Europe</a></li>
								</ul>
							</div>
						</div>

						<div class="col-md-3 col-md-push-1">
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

