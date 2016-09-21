(function(){
	'use strict';

	angular
		.module('uumaiApp',['ngCookies','ngRoute'])
		.config(config);

    // cookiesConfig.$inject = ['$cookiesProvider']
    // HeaderCtrl.$inject = ['$cookies'];

    
	function config($routeProvider){
		$routeProvider
			.when('/', {
				templateUrl: 'views/index.html',
				controller: 'IndexCtrl'
			})
			.when('/login', {
				templateUrl: 'views/login.html',
				controller: 'LoginCtrl'
			})
			.when('/register', {
				templateUrl: 'views/register.html',
				controller: 'RegisterCtrl'
			})
			.otherwise({ redirectTo: '/' });
	}
})();
