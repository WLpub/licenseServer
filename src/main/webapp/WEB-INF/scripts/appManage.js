(function(){
	'use strict';

	angular
		.module('uumaiApp',['ngRoute'])
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
			.when('/way', {
				templateUrl: 'views/way.html',
				controller: 'WayCtrl'
			})
			.when('/addWay', {
				templateUrl: 'views/addWay.html',
				controller: 'AddWayCtrl'
			})
			.when('/changeWay', {
				templateUrl: 'views/changeWay.html',
				controller: 'ChangeWayCtrl'
			})
			.when('/file', {
				templateUrl: 'views/file.html',
				controller: 'FileCtrl'
			})
			.otherwise({ redirectTo: '/' });
	};
})();
