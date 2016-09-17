(function() {
	'use strict';

	angular.module('uumaiApp')
		.controller('LoginCtrl', LoginCtrl);

	function LoginCtrl($scope) {
		$scope.userIdentity = {
			'username' : '',
			'password' : ''
		};
		$scope.loginForm = {
			'$valid' : false
		};

		var phoneReg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
		$scope.login = function() {
			if (!!!$scope.userIdentity.username	|| !phoneReg.test($scope.userIdentity.username)) {
				$scope.isSuccess = false;
				swal('请输入正确的手机号！');
			} else if (!!!$scope.userIdentity.password	|| $scope.userIdentity.password.length <6) {
				$scope.isSuccess = false;
				swal( '密码长度大于5！');
			} else {
				$.ajax({
					type : 'POST',
					url : "./judgeUser",
					data : JSON.stringify({
						'phone' : $scope.userIdentity.username,
						'password': $scope.userIdentity.password
					}),
					success : function(ret){
						if(ret.status>-1){
							$scope.isSuccess = true;
							window.location.href="manage";
						}else{
							$scope.isSuccess = false;
							swal("warning",'登录失败！');
						}
					},
					error:function(ret){
						$scope.isSuccess = false;
						swal("warning",'登录失败！');
					},
					contentType : 'application/json'
				});
			}
		}
	}
})();
