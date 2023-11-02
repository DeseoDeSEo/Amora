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
<link rel="stylesheet" href="css/stylewrite.css">

<!-- Modernizr JS -->
<script src="js/modernizr-2.6.2.min.js"></script>
<!-- FOR IE9 below -->
<!--[if lt IE 9]>
   <script src="js/respond.min.js"></script>
   <![endif]-->

<script src="https://cdn.jsdelivr.net/npm/flatpickr@4.6.9/dist/flatpickr.min.js"></script>
<script>
    flatpickr("#deadline", {
        enableTime: false,
        dateFormat: "Y년 m월 d일",
        locale: "ko",
    });
</script>



</head>
<body>
<!-- 여기부터 메뉴바 -->
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
         role="banner" style="background-image: url(images/bgapt.jpg); height: 150px">
         <div class="overlay"></div>
         <div class="gtco-container">
            <div class="row">
               <div class="col-md-12 col-md-offset-0 text-center">
                  <div class="row row-mt-15em">
                     <div class="col-md-12 mt-text animate-box"
                        data-animate-effect="fadeInUp">
                        <!-- <h1>Plans for Everyone</h1> -->
                     </div>

                  </div>

               </div>
            </div>
         </div>
      </header>
<!-- 여기까지 메뉴바 -->
      

      <div>
         <!--class="gtco-section" -->
         <!-- 여기부터 글쓰기 에용 -->
         <div class="gtco-container">
            <div class="row">
               <div class="topmargin">
                  <!--class="col-md-8 col-md-offset-2 text-center gtco-heading animate-box"-->
                <form action ="ad_writee" method="post" enctype = "multipart/form-data">
                  <div class="board_wrap">
                     <div class="board_title">
                        <div style="margin: 170px 20% 0;">
                           <h1>커뮤니티</h1>
                           <p>아모라 아파트 홍보해요!</p>
                        </div>                        
                     </div>
                     
                     <div class="board_list_wrap" style="margin: 0 20%;">
                  
                        <div class="board_list">
                           
                           <div style="width: 840px; height: 10px;"></div>

                           <textarea name="ad_Title" style="height: 50px; width: 840px; resize: none; overflow: hidden; text-align: left;" placeholder="제목입력"></textarea>
                           <div>
                                      
                                  <label for="dateInput">날짜 선택:</label>
                                  <input type="date" id="ad_Period" name="ad_Period">
                              
                                    </div>
                           <div>
                               <textarea name="ad_Content" rows="10" cols="65" style="width: 840px; resize: none;" placeholder="관리자의 판단하에 비방글은 삭제 됩니다."></textarea>

                           </div>

                        </div>   
                        
                     </div>
                  
                     <div class="wi_line" style="margin: 0 20%; display: flex; justify-content: space-between; align-items: center; width: 840px;">
                         <input type="file" name="SelectFile">
                         <div>
                             <input name="write" type="submit" value="완료">
                             <a href="promotion.jsp"><button style="margin-left: 10px;">취소</button></a>
                         </div>
                     </div>
                     </div>
                     </form>
                  
                  
            
            
         </div>
      </div>


      

      <footer id="gtco-footer" role="contentinfo" style="padding: 7%;">
    <hr>
    <div class="gtco-container" style="margin: 0 20%;">
        <div class="row row-p   b-md" style="display: flex; justify-content: space-between; align-items: center;">
            <div class="col-md-4" style="margin-right: auto;">
                <div class="gtco-widget">
                    <h3>About Us</h3>
                    <p>아모라 아파트</p>
                </div>
            </div>

            <div class="col-md-4">
                <div class="gtco-widget" style="text-align: right;">
                    <h3>관리사무소</h3>
                    <p>사과시</p>
                </div>
            </div>
        </div>
        
        <div class="row copyright" style="padding: 0;">
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