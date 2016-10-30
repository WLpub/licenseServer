(function(){
	'use strict';

	angular
		.module('uumaiApp',['ngRoute'])
		.config(config);
    
	function config($routeProvider){
		$routeProvider
		/*.when('/', {
				templateUrl: 'views/manage.html',
				resolve:{
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
				}
				controller: 'MainCtrl'
			})*/
			.when('/qlifEval', {
				templateUrl: 'views/qlifEval.html',
				controller: 'QlifEvalCtrl'
			})
			.when('/licsExam', {
				templateUrl: 'views/licsExam.html',
				controller: 'LicsExamCtrl'
			})
			.otherwise({ redirectTo: '/licsExam' });
	};
})();
