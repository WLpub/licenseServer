(function(){
	'use strict';

	angular
		.module('uumaiApp')
		.directive('ngFocus', [function() {
			var FOCUS_CLASS = "ng-focused";
			return {
			    restrict: 'A',
			    require: 'ngModel',
			    link: function(scope, element, attrs, ctrl) {
			      	ctrl.$focused = false;
			      	element.bind('focus', function(evt) {
			        	element.addClass(FOCUS_CLASS);
			        	scope.$apply(function() {ctrl.$focused = true;});
			      	}).bind('blur', function(evt) {
			        	element.removeClass(FOCUS_CLASS);
			        	scope.$apply(function() {ctrl.$focused = false;});
			      	});
			    }
			}
		}])
		.directive('myPwdMatch', [function(){  
		    return {  
		        restrict: "A",  
		        require: 'ngModel',  
		        link: function(scope,element,attrs,ctrl){  
		            var tageCtrl = scope.$eval(attrs.myPwdMatch);  
		            tageCtrl.$parsers.push(function(viewValue){  
		                ctrl.$setValidity('pwdmatch', viewValue == ctrl.$viewValue);  
		                return viewValue;  
		            });  
		            ctrl.$parsers.push(function(viewValue){  
		                if(viewValue == tageCtrl.$viewValue){  
		                    ctrl.$setValidity('pwdmatch', true);  
		                    return viewValue;  
		                } else{  
		                    ctrl.$setValidity('pwdmatch', false);  
		                    return undefined;  
		                }  
		            });  
		        }  
		    };  
		}])
		.controller('RegisterCtrl', RegisterCtrl);

	function RegisterCtrl($scope, $http, $timeout, $location){
		// message tips
		$scope.showMsg = true;
		
		// switch register && sms vertify
		$scope.registerStage = true;

		// register form information
		$scope.currentUser = {
			username: "",
			email: "",
			password: "",
			phone: "",
			permission:""
		};
		
		// btn msg
		$scope.msgBtnFont = "发送验证码";
		$scope.msgSending = true;
		// register a new user
		$scope.submitRegister = function(){
			$.ajax({
				type : 'POST',
				url : "./createUser",
				data : JSON.stringify({'user':$scope.currentUser,'imageCode':$scope.imageCode,'phoneCode':$scope.phoneCode}),
				success : function(ret){
					if(ret.status>-1){
						$scope.isSuccess = true;
						window.location.href="#/login";
					}else{
						$scope.isSuccess = false;
						swal('注册失败:'+ret.errMsg);
					}
				},
				error:function(ret){
					$scope.isSuccess = false;
					swal( '注册失败！');
				},
				contentType : 'application/json'
			});
		};
		$scope.timeStart = 1;
		$scope.timeTotal = 60;
		$scope.update_p = function() { 
			if($scope.timeStart == $scope.timeTotal) { 
				$scope.msgBtnFont = "发送验证码";
				$scope.msgSending = true;
				$scope.timeStart = 1;
			 }else { 
				 var printnr = $scope.timeTotal-$scope.timeStart; 
				 $scope.msgBtnFont = " (" + printnr +")秒后重发";
				 $scope.msgSending = false;
				 $scope.timeStart++;
				 window.setTimeout("$('#updateBtn').click()", 1000 ); 
			 }
		}; 
		$scope.sendMsg = function(){
			if(!$scope.msgSending){
				swal("请稍后再试！");
				return;
			}
			if(!!$scope.currentUser.phone)
			{
				$scope.msgSending = false;
				$.ajax({
					type : 'POST',
					url : "./phoneMsg",
					data : JSON.stringify({'phone':$scope.currentUser.phone,"msg":"您的验证码为："}),
					success : function(ret){
						if(ret.status>-1){
							$scope.isSuccess = true;
						}else{
							$scope.isSuccess = false;
							$scope.timeStart = 60;
							swal('发送失败，请稍后再试！');
						}
					},
					error:function(ret){
						$scope.isSuccess = false;
						swal( '注册失败！');
					},
					contentType : 'application/json'
				});
				$scope.update_p();
			}else{
				swal("请先输入正确的手机号码");
			}
		};
		$scope.changeImg = function(){
			 var imgSrc = $("#imgObj");
			 var src = imgSrc.attr("src");
			 imgSrc.attr("src", chgUrl(src));
		};
		function chgUrl(url) {
		    var timestamp = (new Date()).valueOf();
		    url = url.substring(0, 17);
		    if ((url.indexOf("&") >= 0)) {
		      url = url + "×tamp=" + timestamp;
		    } else {
		      url = url + "?timestamp=" + timestamp;
		    }
		    return url;
		  }
	}
})();