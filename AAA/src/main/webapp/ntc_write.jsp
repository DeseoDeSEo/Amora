<!-- 테이블 메인페이지 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 라이브러리를 불러올수 있도록한다 -->    
<%@page import="java.io.PrintWriter"%>



<!DOCTYPE HTML>
<html>
<head>



<!--종권시작  -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- HTML 문서 안에 있는 <meta> 태그로, 뷰포트(Viewport) 설정을 변경하는 역할을 합니다 -->
<!-- 기본적으로 부트스트랩은 컴퓨터, 핸드폰 어떤거에 접속하더라도 해상도에 맞게 알아서 디자인이 변경되는 탬플릿 
 그래서 간단하게 반응형 배타태그를 이렇게 넣어줄 수 있다-->
<meta name = "viewport" content="width=device-width", initial-scale="1">

<!-- 링크안에 스타일시트로 이렇게 참조해준다 
같은 폴더안에 있는 css폴더안에있는 bootstrap.css를 참조해서 이 홈페이지에 디자인으로 사용하겠다-->
<link rel="stylesheet" href="css/bootstrap.css">
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

		  
		  
		  
		  		  
<!-- 종권시작 -->		    
			
			<p>아모라 아파트의 공지사항을 빠르고 정확하게 안내드립니다.</p>

		<!-- 테이블만들기 -->					
		</div>
	
	
	
	</nav>	
	<div class="container">
		<div class="row">
		
		<!-- table은 form태그에 감싸줘야한다//ntc_writeAction.jsp로 이동하도록 만든다-->
		<form method= "post" action="ntc_writeAction.jsp">		
			
			<!-- table-striped 클래스는 Bootstrap에서 제공하는 스타일 클래스 중 하나로, 테이블의 행을 번갈아가며 서로 다른 배경 색으로 스트라이프 모양으로 스타일링합니다.  -->
		  	<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
		  		<!-- <thead>는 HTML 문서에서 테이블의 헤더(Header) 부분을 정의하는 태그입니다 -->
		  		<thead>
		  			<tr>
		  			    <!-- colspan="2" 2개의 열을 사용한다 -->
		  				<th colspan="2" style="background-color: #eeeeee; text-align: center;">공지사항 글쓰기</th>
		  			</tr>
		  		</thead>
		  		<tbody>		  		
		  			<tr>
		  				<!-- 제목을 작성 -->
		  				<td><input type="text" class="form-control" placeholder="글 제목을 입력해주세요" name="no_title" maxlength="50"></td>
		  			</tr>	
		  					
		  			<tr>	
		  			    <!-- textarea 장문의 글을 사용할 떄는 -->
		  				<td><textarea class="form-control" placeholder="글 내용을 입력해주세요" name="no_content" maxlength="2048" style="height: 500px;"></textarea></td>
		  			</tr>			  				  	
		  		</tbody>
			</table>
			
			<!-- 글쓰기 버튼 -->
			<input type ="submit" class="btn btn-primary pull-right" value="글쓰기">		
			
		</form>		 
	  </div>	
	</div>

         
         
         




	<!-- 애니메이션을 담당하게 될 자바스크립트 참조 제이쿼리를 특정 홈페이지에서 가져올수 있도록한다  -->
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<!-- 부트스트랩에서 제공해주는 자바스크립트 파일또한 넣어준다 
		이 파일안에있는 부트스트랩을 참조하겠다라는 의미  -->
	<script src="js/bootstrap.js"></script>


<!-- 종권끝 -->




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

