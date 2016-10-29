<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="cn" class=" js no-touch backgroundsize opacity csstransforms csstransitions" slick-uniqueid="6">
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>ScreenTiger - Screen Capture &amp; ScreenTiger - Video Editor</title>
		<link rel="stylesheet" href="bower_components/bootstrap/dist/css/bootstrap.css" />
		<link href="assets/css/sweetalert.css" rel="stylesheet" />
		<link href="styles/screen.css" type="text/css" rel="stylesheet">
	</head>
	<body class="" ng-app="uumaiApp">
		<div id="page-border">
		<div id="wrap" class="container">
			<header id="header" class="clearfix section">
				<nav id="nav" class="center grid-14">
					<div class="fonts-display clearfix">
                        <p class="logo mq-mobile-i-block">
                            <a href="/" title="ScreenTiger" class="active">
                                <img src="assets/img/logo-plain@2x.png" alt="ScreenTiger">
                            </a>
                        </p>

                        <br class="mq-mobile">

						<p class="text-left alpha"><a href="./#/">首页</a>
						</p><p class="text-left"><a href="http://www.screentiger.cn">Tiger</a>
						</p><p class="text-left"><a href="./#register">加入我们</a>
						</p><p class="logo mq-wide-i-block mq-mid-i-block mq-small-i-block">
							<a href="/" title="ScreenTiger">
								<img src="assets/img/logo-type@2x.png" alt="Built By">
								<img class="logo-img" src="assets/img/logo-plain@2x.png" alt="Buffalo">
							</a>
						</p><p class="text-right"><a href="./manage">购买</a>
						</p><p class="text-right"><a onclick="window.scrollTo(0,$('body')[0].scrollHeight)">反馈</a>
						</p><p class="text-right omega"><a onclick="window.scrollTo(0,$('body')[0].scrollHeight)">联系我们</a>
					</p></div>
				</nav> <!-- /#nav -->
			</header>
			<a name="top"></a>
			<div class="main" ng-view>
			</div>
			<!--联系我们-->
			
			<a name="contact"></a>
			<aside id="footer" class="grid-12 center section">
				<ul class="list-icon-items clearfix">
					<li class="grid-4 alpha subsection">
						<a href="/planner" class="display-block" title="Proposal planner">
							<i class="circ secondary ss-icon ss-standard">📝<i class="circ pulse"></i></i>
                            <h3>项目进展</h3>
                            <p class="colour-body">Interested in working with us? Launch our proposal planner to get started.</p>
                            <p class="button tertiary display-inline-block">Launch it</p>
                        </a>
					</li>
                    <li class="grid-4 subsection">
                        <a href="/contact" title="Connect"><i class="circ secondary ss-icon ss-standard">💬<i class="circ pulse"></i></i></a>
                        <h3>联系我们</h3>
                        <ul class="list-plain list-regular">
                            <li>+86 (0)1273 434 060</li>
							<li><a href="mailto:hello@builtbybuffalo.com" class="has-border">hello@builtbybuffalo.com</a></li>
							<li><a href="http://twitter.com/builtbybuffalo" class="has-border">Follow us on Twitter</a></li>
                        </ul>
                    </li>
                    <li class="grid-4 omega subsection">
                        <i class="circ secondary ss-icon ss-standard">🔔</i>
                        <h3>邮件通知</h3>
                        <p>在下方输入你的邮箱，就可以接收最新的软件更新通知.</p>
						<form id="newsletter-signup" action="/newsletter" method="post">
							<div class="input-wrap">
								<input type="text" name="email" placeholder="Your email address">
								<button type="submit"><i class="ss-icon ss-standard">▻</i></button>
                            </div>
                        </form>
                    </li>
                </ul>
                    
                <footer class="subsection">
                    <p>
                        Copyright © 2006 - 2016 <a href="/">ScreenTiger</a> Limited. All rights reserved.<br>
                        Registered in England Company No. 06048231 VAT Registration No. 899 6307 54
                    </p>
                </footer>
            </aside> <!-- /#footer -->
        <!--</div>  /.container -->	
		</div> <!-- /#wrap -->

		<!-- Arrow to top -->
		<a onclick="window.scrollTo(0,0)" id="to-top" class="icon-box" style="display: block; opacity: 1;">
			<i class="ss-icon ss-standard">🔝</i>
		</a>
		<!-- Modal markup -->
		<div id="modal-overlay"></div>
			<div id="modal-wrap" class="clearfix">
				<div class="vertical-center"></div>
				<div id="modal" class="grid-14 center">
					<div id="modal-content" class="clearfix">
						<div class="grid-14 alpha omega">
							<!-- Title -->
							<h2></h2>
							
							<!-- Tagline -->
							<h3 class="tagline h4"></h3>
						</div>
						
						<!-- Body column 1 -->
						<div class="grid-7 alpha"></div>
						
						<!-- Body column 2 -->
						<div class="grid-7 omega"></div>
						
						
						<!-- UI Controls -->
						<a href="./#/" id="modal-close" class="ss-icon ss-standard">␡</a>

						<a href="./#/" id="modal-left">
							<i class="ss-icon ss-standard">◅</i>
						</a>
						<a href="./#/" id="modal-right">
							<i class="ss-icon ss-standard">▻</i>
						</a>
					</div>
					
					<div id="modal-footer" class="clearfix">
						<!-- Footer column 1 -->
						<div class="grid-7 alpha"></div>
						
						<!-- Footer column 2 -->
						<div class="grid-7 omega"></div>
					</div>
				</div>

			</div>
			<div id="ie6">
				<p id="ie6-logo"><a href="/" class="no-border" title="Built by Buffalo - Web Design Brighton UK"><img src="assets/img/logo-ie6.png" alt="Built by Buffalo - Web Design Brighton UK"></a></p>
				<h1 id="tagline">We are Buffalo</h1>
				
				<h2 class="grid-10 center">
					Sadly you are using Internet Explorer 6.  This browser is over 10 years old so we can't show you the lovely site that you would get with a modern browser like <a href="https://www.google.com/chrome">Chrome</a>, <a href="http://www.apple.com/safari/">Safari</a> or <a href="http://www.mozilla.org">Firefox</a>. Upgrade and embrace the future of the internet!
					<br><br>
					Until you upgrade you will have to take our word for it when we say that over the last few years we've made a reputation for building websites that look great and are easy-to-use. 
					<br><br>
					Founded in 2006, Buffalo is a small web design &amp; development agency based in Brighton, UK. If you the sound of us, and think we could work together then get in touch with us. Either by phone on +44 (0)1273 434 060 or by email on <a href="mailto:hello@builtbybuffalo.com">hello@builtbybuffalo.com</a>.
				</h2>
			</div>
		</div>
	</body>
	<script src="bower_components/jquery/dist/jquery.js"></script>
	<script src="bower_components/angular/angular.js"></script>
	<script src="bower_components/bootstrap/dist/js/bootstrap.js"></script>
	<script src="bower_components/angular-route/angular-route.js"></script>
	<script src="bower_components/angular-cookies/angular-cookies.js"></script>
	<script src="assets/js/sweetalert.min.js"></script>
    <!-- build:js({.tmp,app}) scripts/scripts.js -->
    <script src="scripts/app.js"></script>
    <script src="scripts/controllers/index.js"></script>
    <script src="scripts/controllers/login.js"></script>
    <script src="scripts/controllers/register.js"></script>
</html>