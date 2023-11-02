<%@page import="model.AdDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.AdDAO"%>
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
<link rel="stylesheet" href="css/styleCommunity.css">
<link rel="stylesheet" href="css_shop/styles.css">


<style>
    .page-number {
        display: inline-block;
        padding: 5px 10px;
        margin: 2px;
        border: 1px solid #ccc;
        border-radius: 5px;
        text-decoration: none;
        color: #333;
    }
    
    .current-page {
        background-color: #007bff;
        color: #fff;
        font-weight: bold;
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
               <div style ="margin-top:20px" class="col-xs-8 text-right menu-1">
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
               <div style ="margin-top:280px" class="col-md-12 col-md-offset-0 text-center">
                  <div class="row row-mt-15em">
                     <div class="col-md-12 mt-text animate-box"
                        data-animate-effect="fadeInUp">
                        <h1>홍보</h1>
                     </div>

                  </div>

               </div>
            </div>
         </div> 
      </header>

      <!--  <div class="gtco-section border-bottom">
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
      </div>
   </div> -->

      <div>
         <!--class="gtco-section" -->
         <div class="gtco-container">
            <div class="row">
               <div class="topmargin">
                  <!--class="col-md-8 col-md-offset-2 text-center gtco-heading animate-box"-->
                  <div style="margin-top: 10px;" class="board_wrap">
                     <div class="board_title">
                        <h1>홍보</h1>
                        <p>아모라 입주민들의 홍보게시판!</p>
                     </div>
                     <div class="board_list_wrap" style="text-align: center;">
                        <div class="board_list">
                           <!-- Section-->
                           <section class="py-5">
                              <div class="container px-4 px-lg-5 mt-5">
                                 <div
                                    class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
                                    <%
                                       String nickname = null;
                                       if (session.getAttribute("nickname") != null) {
                                          nickname = (String) session.getAttribute("nickname");
                                       }
                                       
                                       int pageNumber = 1;
                                       if (request.getParameter("pageNumber") != null) {
                                          pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
                                       }
                                    %>
                                    <%
                                        AdDAO adDAO = new AdDAO();
                                        AdDAO adDAO2 = new AdDAO();
                                        ArrayList<AdDTO> list = adDAO.getList(pageNumber);
                                        for (int i = 0; i < list.size(); i++) { 
                                            AdDTO ad = list.get(i);
                                    %>
                                    
                                    <%--파일 url 을 불러오기  --%>
                                    <%
                                    String imagePath = adDAO2.ad_img(ad.getAd_Num());
                                     
                                    if(imagePath == null){
                                       imagePath = "/sh_img/빈사진.jpg";
                                    }
                                    
                                    %>
                                                                                       
                                    
                                    <div class="col mb-5">
                                        <div class="card h-100">
                                            <!-- Product image-->
                                            <%-- <img class="card-img-top" src="<%= ad.getImageURL() %>" alt="..." /> --%>
                                            
                                            <%-- 사진을 불러옵니다 --%>
                                           <img class="card-img-top" src="${pageContext.request.contextPath}<%=imagePath %>"  alt="..." />
                                            
                                            <!-- Product details-->
                                            <div class="card-body p-4">
                                                <div class="text-center">
                                                    <!-- Product name (AD_TITLE)-->
                                                    <h5 class="fw-bolder"><%= ad.getAd_Title() %></h5>
                                                    <!-- Product price (AD_PRICE)-->
                                                    <%-- <%= ad.getAd_Price() %> --%>
                                                </div>
                                            </div>
                                            <!-- Product actions-->
                                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                                <div class="text-center">
                                                <a class="btn btn-outline-dark mt-auto" href="ad_view.jsp?ad_Num=<%= list.get(i).getAd_Num() %>">상세 보기</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <%
                                           }
                                        
                                    %>
                                    
                                    
                                 </div>
                              </div>
                           </section>

                        </div>
                           <% 
                               System.out.println(adDAO.nextPage(pageNumber + 1));
                               if (pageNumber  != 1){
                           %>
                               <a href="promotion.jsp?pageNumber=<%=pageNumber - 1%>" class="btn btn-success btn-arrow-left">이전</a>
                           <%
                               }
                           %>
                           
                           <%
                               int n = (int)(adDAO.getCount() / 8 + 1);
                               for (int i = 1; i <= n; i++) {
                           %>
                               <a href="promotion.jsp?pageNumber=<%=i%>" class="<%= i == pageNumber ? "page-number current-page" : "page-number" %>"><%=i%></a>
                           <%
                               }
                           %>
                           
                           <%
                               if (adDAO.nextPage(pageNumber + 1)) {
                           %>
                               <a href="promotion.jsp?pageNumber=<%=pageNumber + 1%>" class="btn btn-success btn-arrow-right">다음</a>
                           <%
                               }
                           %>
                            <%-- <% 
                               System.out.println(adDAO.nextPage(pageNumber + 1));
                              if (pageNumber != 1) {
                           %>
                              <a href="promotion.jsp?pageNumber=<%=pageNumber - 1%>" class="btn btn-success btn-arrow-left">이전</a>
                           <%
                              } if (adDAO.nextPage(pageNumber + 1)) {
                           %>
                              <a href="promotion.jsp?pageNumber=<%=pageNumber + 1%>" class="btn btn-success btn-arrow-left">다음</a>
                           <%
                              }
                           %>
                           <%
                                       int n = (int)(adDAO.getCount() / 8 + 1);
                                       for (int i = 1; i <= n; i++) {
                           %>
                                       <a href="promotion.jsp?pageNumber=<%=i%>">|<%=i%>|</a>
                                       
                           <%
                                          }
                                        
                           %>  --%>
                           
                     
                        <div class="bt_wrap">
                           <a href="ad_write.jsp" class="on">글쓰기</a>
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


         <!--  <div id="gtco-subscribe">
      <div class="gtco-container">
         <div class="row animate-box">
            <div class="col-md-8 col-md-offset-2 text-center gtco-heading">
               <h2>Subscribe</h2>
               <p>Be the first to know about the new templates.</p>
            </div>
         </div>
         <div class="row animate-box">
            <div class="col-md-8 col-md-offset-2">
               <form class="form-inline">
                  <div class="col-md-6 col-sm-6">
                     <div class="form-group">
                        <label for="email" class="sr-only">Email</label>
                        <input type="email" class="form-control" id="email" placeholder="Your Email">
                     </div>
                  </div>
                  <div class="col-md-6 col-sm-6">
                     <button type="submit" class="btn btn-default btn-block">Subscribe</button>
                  </div>
               </form>
            </div>
         </div>
      </div>
   </div> -->

         <footer id="gtco-footer" role="contentinfo">
            <div class="gtco-container">
               <div class="row row-p   b-md">

                  <div class="col-md-4">
                     <div class="gtco-widget">
                        <h3>About Us</h3>
                        <p>아모라 아파트</p>
                     </div>
                  </div>

                  <div class="col-md-2 col-md-push-1">
                     <div class="gtco-widget">
                        <h3>관리사무소</h3>
                        <ul class="gtco-footer-links">
                           <li><a href="#">사과시 복숭아동 아모라아파트</a></li>
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
