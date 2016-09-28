<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta content="" name="description" />
	<meta content="webthemez" name="author" />
	<title>个人账号管理</title>
	<!-- Bootstrap Styles-->
	<link href="assets/css/bootstrap.css" rel="stylesheet" />
	<!-- FontAwesome Styles-->
	<link href="assets/css/font-awesome.css" rel="stylesheet" />
	<link href="assets/css/sweetalert.css" rel="stylesheet" />
	<!-- Custom Styles-->
	<link href="assets/css/custom-styles.css" rel="stylesheet" />
</head>
<body ng-app="uumaiApp">
	<div id="wrapper">
		<nav class="navbar navbar-default top-navbar" role="navigation">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-collapse">
				<span class="sr-only">Toggle navigation</span> 
				<span class="icon-bar"></span> <span class="icon-bar"></span> 
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="./manage#/">
				<strong><i class="icon fa fa-plane"></i> LicenseServer</strong>
			</a>
			<div id="sideNav" href="">
				<i class="fa fa-bars icon"></i>
			</div>
		</div>

		<ul class="nav navbar-top-links navbar-right">
			<li class="dropdown">
				<a class="dropdown-toggle" data-toggle="dropdown" href="#/" aria-expanded="false"> 
					<i class="fa fa-envelope fa-fw"></i> <i class="fa fa-caret-down"></i>
				</a>
				<ul class="dropdown-menu dropdown-messages">
					<li><a href="#/">
							<div>
								<strong>John Doe</strong> <span class="pull-right text-muted">
									<em>Today</em>
								</span>
							</div>
							<div>你有一封新的简历需要处理</div>
					</a></li>
					<li class="divider"></li>
					<li><a class="text-center" href="#/"> 
						<strong>查询所有信件</strong> <i class="fa fa-angle-right"></i>
					</a></li>
				</ul>
			</li>
			<!-- /.dropdown -->
			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#/" aria-expanded="false"> <i
					class="fa fa-tasks fa-fw"></i> <i class="fa fa-caret-down"></i>
			</a>
				<ul class="dropdown-menu dropdown-tasks">
					<li><a href="#/">
							<div>
								<p>
									<strong>Task 1</strong> <span class="pull-right text-muted">60%
										Complete</span>
								</p>
								<div class="progress progress-striped active">
									<div class="progress-bar progress-bar-success"
										role="progressbar" aria-valuenow="60" aria-valuemin="0"
										aria-valuemax="100" style="width: 60%">
										<span class="sr-only">60% Complete (success)</span>
									</div>
								</div>
							</div>
					</a></li>
					<li class="divider"></li>
					<li><a class="text-center" href="#/"> <strong>See
								All Tasks</strong> <i class="fa fa-angle-right"></i>
					</a></li>
				</ul> <!-- /.dropdown-tasks --></li>
			<!-- /.dropdown -->
			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#/" aria-expanded="false"> <i
					class="fa fa-bell fa-fw"></i> <i class="fa fa-caret-down"></i>
			</a>
				<ul class="dropdown-menu dropdown-alerts">
					<li><a href="#/">
							<div>
								<i class="fa fa-comment fa-fw"></i> New Comment <span
									class="pull-right text-muted small">4 min</span>
							</div>
					</a></li>
					<li class="divider"></li>
					<li><a class="text-center" href="#/"> <strong>See
								All Alerts</strong> <i class="fa fa-angle-right"></i>
					</a></li>
				</ul> <!-- /.dropdown-alerts --></li>
			<!-- /.dropdown -->
			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#/" aria-expanded="false"> <i
					class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
			</a>
				<ul class="dropdown-menu dropdown-user">
					<li><a href="#/"><i class="fa fa-user fa-fw"></i> User
							Profile</a></li>
					<li><a href="#/"><i class="fa fa-gear fa-fw"></i> Settings</a>
					</li>
					<li class="divider"></li>
					<li><a href="./logout"><i class="fa fa-sign-out fa-fw"></i>
							Logout</a></li>
				</ul> <!-- /.dropdown-user --></li>
			<!-- /.dropdown -->
		</ul>
		</nav>
		<nav class="navbar-default navbar-side" role="navigation">
			<div class="sidebar-collapse">
				<ul class="nav" id="main-menu">
					<li><a href="#/license"><i class="fa fa-dashboard"></i> 我的License</a></li>
					<li class="active">
                        <a ><i class="fa fa-sitemap"></i> 产品<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level collapse in" aria-expanded="true" style="">
                            <li>
                                <a href="#/ScreenTiger">ScreenTiger</a>
                            </li>
                            <li>
                                <a href="#/ScreenTiger">Other Product</a>
                            </li>
						</ul>
					</li>
				</ul>
			</div>
		</nav>
		<div ng-view></div>
		<!-- /. PAGE WRAPPER  -->
	</div>
	<!-- /. WRAPPER  -->
</body>
	<!-- JS Scripts-->
	<!-- jQuery Js -->
	<script src="assets/js/jquery-1.10.2.js"></script>
	<!-- Bootstrap Js -->
	<script src="assets/js/bootstrap.min.js"></script>
	<script src="assets/js/jquery.metisMenu.js"></script>
	<script src="assets/js/custom-scripts.js"></script>
	<script src="assets/js/angular.min.js"></script>
	<script src="assets/js/angular-route.min.js"></script>
	<!-- Metis Menu Js -->
	<script src="assets/js/sweetalert.min.js"></script>
	<!-- Custom Js -->
	<script src="scripts/appManage.js"></script>
	<script src="scripts/controllers/manage.js"></script>
</html>