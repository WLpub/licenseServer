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
			.otherwise({ redirectTo: '/' });
	};
})();
