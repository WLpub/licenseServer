(function(){
	'use strict';

	angular
		.module('uumaiApp',['ngCookies','ngRoute','ngAnimate', 'brantwills.paging'])
		.config(config);

    // cookiesConfig.$inject = ['$cookiesProvider']
    // HeaderCtrl.$inject = ['$cookies'];

    
	function config($routeProvider){
		$routeProvider
			.when('/', {
				templateUrl: 'views/search.html',
				controller: 'SearchCtrl'
			})
			.when('/login', {
				templateUrl: 'views/login.html',
				controller: 'LoginCtrl'
			})
			.when('/register', {
				templateUrl: 'views/register.html',
				controller: 'RegisterCtrl'
			})
			.when('/list', {
				templateUrl: 'views/list.html',
				controller: 'ListCtrl'
			})
			.when('/detail/:pid', {
				templateUrl: 'views/detail.html',
				controller: 'DetailCtrl'
			})
			.otherwise({ redirectTo: '/' });
	}
})();
