<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta charset="utf-8">
    <title></title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width">
    <link rel="stylesheet" href="bower_components/bootstrap/dist/css/bootstrap.css" />
	<link href="assets/css/sweetalert.css" rel="stylesheet" />
    <link rel="stylesheet" href="styles/main.css">
    <link rel="stylesheet" href="styles/pages.css">
  </head>
  <body ng-app="uumaiApp">
    <header  class="navbar navbar-static-top">
        <div class="container">
            <div class="navbar-left">
                <span href="/" class="header_nav header_logo">LinkedIn HR</span>
             </div>
            <div class="">
            	<c:if test="${empty sessionScope.user}">
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <a href="#/login" class="header_nav">登录</a>
                        </li>
                        <li>
                            <a href="#/register" class="header_nav">注册</a>
                        </li>
                    </ul>
                </c:if>
               <c:if test="${!empty sessionScope.user}">
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <a href="" class="header_nav">Hi <span>${user.username}</span></a>
                        </li>
                        <li>
                            <a href="./logout" class="header_nav" >退出</a>
                        </li>
                    </ul>
                </c:if>
                    <!--  -->
              </div>       
        </div>
    </header>

    <div class="main" ng-view>
	</div>

    <footer class="">
        <p>UUmai.com 沪ICP备14043959号-1 </p>
    </footer>

    <!-- Google Analytics: change UA-XXXXX-X to be your site's ID 
     <script>
       (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
       (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
       m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
       })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

       ga('create', 'UA-XXXXX-X');
       ga('send', 'pageview');
    </script>
    -->
        <!-- Baidu Analytics 
        -->
  </body>      
        <script>
            var _hmt = _hmt || [];
            (function() {
              var hm = document.createElement("script");
              hm.src = "//hm.baidu.com/hm.js?d65c2a88bcebbac7d1006e8b16617a9d";
              var s = document.getElementsByTagName("script")[0]; 
              s.parentNode.insertBefore(hm, s);
            })();
        </script>
        

    <!-- build:js(.) scripts/oldieshim.js -->
    <!--[if lt IE 9]>
    <script src="bower_components/es5-shim/es5-shim.js"></script>
    <script src="bower_components/json3/lib/json3.js"></script>
    <![endif]-->
    <!-- endbuild -->

    <!-- build:js(.) scripts/vendor.js -->
    <!-- bower:js -->
   
    <!-- endbower -->
    <!-- endbuild -->
		<script src="bower_components/jquery/dist/jquery.js"></script>
		<script src="bower_components/angular/angular.js"></script>
		<script src="bower_components/bootstrap/dist/js/bootstrap.js"></script>
		<script src="bower_components/angular-animate/angular-animate.js"></script>
		<script src="bower_components/angular-route/angular-route.js"></script>
		<script src="bower_components/angular-cookies/angular-cookies.js"></script>
		<script src="assets/js/sweetalert.min.js"></script>
        <!-- build:js({.tmp,app}) scripts/scripts.js -->
        <script src="scripts/app.js"></script>
        <script src="scripts/controllers/keyboard.js"></script>
        <script src="scripts/controllers/paging.js"></script>
        <script src="scripts/controllers/search.js"></script>  
        <script src="scripts/controllers/items.js"></script>
        <script src="scripts/controllers/list.js"></script>
        <script src="scripts/controllers/detail.js"></script>
        <script src="scripts/controllers/login.js"></script>
        <script src="scripts/controllers/register.js"></script>
        <!-- endbuild -->
</html>
