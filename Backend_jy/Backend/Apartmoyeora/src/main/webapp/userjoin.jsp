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
							<li style="position: relative; left: -20px;"><a href="index.jsp">돌아가기</a></li>


						</ul>
					</div>
				</div>

			</div>
		</nav>

		<header  id="gtco-header" class="gtco-cover gtco-cover-sm"
			role="banner" style= "height:150px !important; background-image: url(images/bgapt.jpg);">
			
			
		</header>



		<div><!--class="gtco-section" -->
			<div class="gtco-container">
				<div class="row">
					<div class="topmargin">
						<!--class="col-md-8 col-md-offset-2 text-center gtco-heading animate-box"-->
						<div class="board_wrap" style= "margin-top:200px !important;">
							<div class="board_title">
								<h1>회원가입</h1>
								<p>회원가입 페이지 입니다.</p>
							</div>
							
							<!-- 백엔드 연결주소 -->
							<form action="${pageContext.request.contextPath}/userJoin" method="post" id = "Inputform">
							
							<div class="form-wrap" >
										<div class="tab">
											<div class="tab-content">
												<div class="tab-content-inner active" data-content="signup">
													<h3></h3>
														</div>
														<!--  회원가입탭 -->
														<div class="row form-group">
															<div class="col-md-12">
																<label for="activities">ID</label>
																<input type="text" id="id" name="id" placeholder = " 아이디 입력" class="form-control">
																	
																</select>
															</div>
														</div>
														<div class="row form-group">
															<div class="col-md-12">
																<label for="PW">PW</label>
															    <input type="text" id="pw" name="pw" placeholder = " 비밀번호 입력" class="form-control">												</div>
														</div>
														
														<div class="row form-group">
															<div class="col-md-12">
																<label for="activities">닉네임</label>
																<input type="text" id="nickname" name="nickname" placeholder = " 닉네임 입력" class="form-control">				
															</div>
														</div>
														
														<div class="row form-group">
															<div class="col-md-12">
																<label for="activities">이름</label>
																<input type="text" id="name" name="name" placeholder = " 이름 입력" class="form-control">				
															</div>
														</div>
														
														<div class="row form-group">
															<div class="col-md-12">
																<label for="activities">Phone</label>
																<input type="number" id="phone" name="phone" placeholder = " 숫자만 입력해주세요" class="form-control">	
															</div>
														</div>
														
														<div class="row form-group">
															<div class="col-md-12">
																<label for="activities">email</label>
																<input type="email" id="email" name="email" placeholder = " email 입력" class="form-control">	
															</div>
														</div>
														
														<div class="row form-group">
															<div class="col-md-12">
																<label for="activities">생년월일</label>
																<input type="date" id="birthdate" name="birthdate" class="form-control">
															</div>
														</div>
														
														<div class="row form-group">
															<div class="col-md-12">
																<label for="gender">성별</label><br>
															    <label style="font-size: 80%; margin-right: 50px;"for="gender_male">
															      남자 <input type="radio" value="남자" id="gender_male" name="gender" style="width: 20px; height: 20px; border: 1px;">
															    </label>
															    <label style="font-size: 80%"; for="gender_female">
															      여자 <input type="radio" value="여자" id="gender_female" name="gender" style="width: 20px; height: 20px; border: 1px;">
															    </label>									
															</div>
														</div>
														
														<div class="row form-group">
													<div class="col-md-12">
														<label for="activities">아파트등록</label>
														<div style="display: flex; justify-content: center; float: right; margin-right: 630px;">
														  <input type="button" onclick="findMyApart()" value="아파트 검색" class="form-control" style="width: 200px !important;">
														</div>
														
														<div style="line-height:50%;">
															<input type="text" id= "addr" name="addr" placeholder="주소" class="form-control"><br>
															<input type="text" id= "dongho" name="dongho" placeholder="동, 호수" class="form-control"><br>
															<input type="text" id="sample6_extraAddress" placeholder="참고항목" class="form-control">	
														</div>													
													</div>
												</div>
														
														
														<div class="row form-group">
															<div class="col-md-12">
																<input type="submit" id = "adminjoin" class="btn btn-primary1 btn-block" value="회원가입하기">
															</div>
														</div>
												
												</div>
												</div>
												</div>
												
												
												
											</form>	
		<!-- ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ -->												
										</div>

										
									</div>
								</div>
							
						</div>
					</div>
					<div class="row">
						<div class="col-md-8 col-md-offset-2">
							<ul class="fh5co-faq-list">

							</ul>
						</div>
					</div>
				</div>
			</div>


			

			<footer id="gtco-footer" role="contentinfo" style="padding-top:0 ; padding-bottom:0 !important;">
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
		
		
		<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
		
		<!-- 주소API -->
		<script>
		   function findMyApart() {
		     new daum.Postcode({
		            oncomplete: function(data) {
		                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
		
		                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
		                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
		                var addr = ''; // 주소 변수
		                var extraAddr = ''; // 참고항목 변수
		
		                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
		                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
		                    addr = data.roadAddress;
		                } else { // 사용자가 지번 주소를 선택했을 경우(J)
		                    addr = data.jibunAddress;
		                }
		
		                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
		                if(data.userSelectedType === 'R'){
		                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
		                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
		                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
		                        extraAddr += data.bname;
		                    }
		                    // 건물명이 있고, 공동주택일 경우 추가한다.
		                    if(data.buildingName !== '' && data.apartment === 'Y'){
		                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
		                    }
		                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
		                    if(extraAddr !== ''){
		                        extraAddr = ' (' + extraAddr + ')';
		                    }
		                    // 조합된 참고항목을 해당 필드에 넣는다.
		                    document.getElementById("sample6_extraAddress").value = extraAddr;
		                
		                } else {
		                    document.getElementById("sample6_extraAddress").value = '';
		                }
		
		                // 우편번호와 주소 정보를 해당 필드에 넣는다.
		                document.getElementById("addr").value = addr;
		                // 커서를 상세주소 필드로 이동한다.
		                document.getElementById("dongho").focus();
		            }
		        }).open();
		     
		     <!-- 폼 입력조건 안채워지면 실행안되게 -->
		     
		  }
		   document.getElementById('Inputform').addEventListener('submit', function(event) {
			    var inputs = document.querySelectorAll("#Inputform input"); // 폼 내의 모든 입력 필드 선택
			    var emptyFieldExists = false;
			    
			    for (var i = 0; i < inputs.length; i++) {
			        var inputValue = inputs[i].value.trim();
			        
			        if (inputValue === "") {
			            emptyFieldExists = true;
			            break; // 빈 값이 하나라도 있으면 반복문 종료
			        }
			    }
			    
			    if (emptyFieldExists) {
			        alert("모든 필드를 입력하세요.");
			        event.preventDefault(); // 제출 막기
			    }
			});

		</script>
		
		
</body>
</html>

