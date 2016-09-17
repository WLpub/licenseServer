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
			phone: ""
		};

		//register a new user
		$scope.submitRegister = function(){
			$.ajax({
				type : 'POST',
				url : "./createUser",
				data : JSON.stringify($scope.currentUser),
				success : function(ret){
					if(ret.status>-1){
						$scope.isSuccess = true;
						window.location.href="#/login";
					}else{
						$scope.isSuccess = false;
						swal('注册失败！'+ret.errMsg);
					}
				},
				error:function(ret){
					$scope.isSuccess = false;
					swal( '注册失败！');
				},
				contentType : 'application/json'
			});
		};
	}
})();