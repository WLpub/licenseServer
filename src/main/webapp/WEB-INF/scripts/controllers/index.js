(function(){
	'use strict';

	angular
		.module('uumaiApp')
		.controller('IndexCtrl', IndexCtrl);

	function IndexCtrl($scope){
		$scope.showTip = function(index){
			$('.list-inline-block.slider-markers .active').removeClass('active');
			$('.list-inline-block.slider-markers li a').eq(index).attr('class','active');
			$('.slider>li').css({'display':'none','opacity':'0'});
			$('.slider>li').eq(index).css({'display':'block','opacity':'1'});
		};
	};
})();