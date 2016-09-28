(function(){
	'use strict';

	angular
		.module('uumaiApp')
		.controller('MainCtrl', MainCtrl)
		.controller('ProductCtrl', ProductCtrl)
		.controller('RecordCtrl', RecordCtrl);

	function MainCtrl($rootScope, $scope){
		$scope.generalInfo = {
				"user":{},
				"totalWay":'',
				"totalFile":''
		};
		$scope.getInfo = function() {
			$.ajax({
				type : 'POST',
				url : "./getGeneralInfo",
				data : JSON.stringify({}),
				success : function(ret) {
					if (ret.status > -1) {
						$scope.generalInfo.user = ret.user;
						$scope.generalInfo.user.isGithub = ret.user.permission.charAt(3)=='0'?'未加入':'加入';
						$scope.generalInfo.user.isTeacher = ret.user.permission.charAt(4)=='0'?'未认证':'认证';
						$scope.$apply();
					} else {
						$scope.messageText = '获取失败！' + ret.errMsg;
						$scope.generalInfo.user.username = "游客";
						$scope.generalInfo.totalWay = "未登录·暂无";
						$scope.generalInfo.totalFile = "未登录·暂无";
						$scope.$apply();
					}
				},
				error : function(ret) {
					swal('获取基本信息失败！');
				},
				contentType : 'application/json'
			});
		};
		$scope.getInfo();
	};
	function GetQueryString(name){
		var svalue = location.href.match(new RegExp("[\?\&]" + name + "=([^\&]*)(\&?)", "i"));
        return svalue ? svalue[1] : svalue;
	}
	function ProductCtrl($scope){
		var id = GetQueryString("id");
		$scope.getProduct = function() {
			$.ajax({
				type : 'POST',
				url : "./getProductByID",
				data : JSON.stringify({'id':id}),
				success : function(ret) {
					if (ret.status > -1) {
						$scope.product = ret.product;
						$scope.product.price = eval(ret.product.price);
						console.log($scope.product.price);
						$scope.$apply();
					} else {
						swal('获取产品信息失败！');
					}
				},
				error : function(ret) {
					swal('获取产品信息失败！');
				},
				contentType : 'application/json'
			});
		};
		$scope.getProduct();
	};
	function RecordCtrl($scope){
	};
})();