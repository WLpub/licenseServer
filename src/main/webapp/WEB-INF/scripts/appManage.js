(function(){
	'use strict';

	angular
		.module('uumaiApp',['ngRoute','ngSanitize'])
		.config(config);
    
	function config($routeProvider){
		$routeProvider
			.when('/', {
				templateUrl: 'views/manage.html',
				/*resolve:{
					changeStyle: function($q) {
					console.log($q);
						var delay = $q.defer();
						delay.resolve();
						$('.active').removeClass("active");
						$('#mainBig').addClass("active");
						$('#main').addClass("active");
						$('#nagShow').show();
						return delay.promise;
					  }
				}*/
				controller: 'MainCtrl'
			})
			.when('/product', {
				templateUrl: 'views/product.html',
				controller: 'ProductCtrl'
			})
			.when('/record', {
				templateUrl: 'views/record.html',
				controller: 'RecordCtrl'
			})
			.when('/githubAuth', {
				templateUrl: 'views/githubAuth.html',
				controller: 'GithubAuthCtrl'
			})
			.when('/githubManage', {
				templateUrl: 'views/githubManage.html',
				controller: 'GithubManageCtrl'
			})
			.when('/teacherAuth', {
				templateUrl: 'views/teacherAuth.html',
				controller: 'TeacherAuthCtrl'
			})
			.when('/teacherManage', {
				templateUrl: 'views/teacherManage.html',
				controller: 'TeacherManageCtrl'
			})
			.when('/companyAuth', {
				templateUrl: 'views/companyAuth.html',
				controller: 'CompanyAuthCtrl'
			})
			.when('/companyManage', {
				templateUrl: 'views/companyManage.html',
				controller: 'CompanyManageCtrl'
			})
			.when('/license', {
				templateUrl: 'views/license.html',
				controller: 'LicenseManageCtrl'
			})
			.otherwise({ redirectTo: '/' });
	};
})();
