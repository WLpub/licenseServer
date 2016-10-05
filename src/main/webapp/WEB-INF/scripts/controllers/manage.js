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
		$scope.priceChoose = {};
		$scope.code  = '';
		$scope.getProduct = function() {
			$.ajax({
				type : 'POST',
				url : "./getProductByID",
				data : JSON.stringify({'id':id}),
				success : function(ret) {
					if (ret.status > -1) {
						$scope.product = ret.product;
						console.log(ret.product.price);
						$scope.product.price = eval(ret.product.price);
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
		$scope.purchase = function(){
			console.log(!!!$scope.priceChoose.id&&$scope.priceChoose.id>-1&&!!$scope.code,$scope.priceChoose.id,$scope.priceChoose.id,$scope.code);
			if($scope.priceChoose.id==undefined||$scope.priceChoose.id==undefined||!!!$scope.code){
				swal("请检查输入的数据，然后再次点击购买！");
				return;
			}
			var data = {'productID':id,'priceID':$scope.priceChoose.id,'code':$scope.code};
			$.ajax({
				type : 'POST',
				url : "./createRecord",
				data : JSON.stringify(data),
				success : function(ret) {
					if (ret.status > -1) {
						$scope.priceChoose = {};
						$scope.code  = '';
						$scope.$apply();
						swal('购买成功！');
					} else {
						swal('购买失败，余额不足！');
					}
				},
				error : function(ret) {
					swal('购买失败，请稍后再试！');
				},
				contentType : 'application/json'
			});	
		};
		$scope.getProduct();
	};
	function RecordCtrl($scope){
		$scope.jobs=[];
		$scope.pageStart = 1;
		$scope.pages = [1];
		$scope.pageTotal = 10;
		$scope.getJobs = function() {
			$.ajax({
				type : 'POST',
				url : "./getResumeByUserId",
				data : JSON.stringify({'start':($scope.pageStart*10-10)||0,'resume':{}}),
				success : function(ret) {
					if (ret.status > -1) {
						$scope.jobs = ret.resumes;
						$scope.pages = [];
						$scope.pageTotal = ret.count||10;
						for(var i=0;i<$scope.pageTotal/10;i++){
							$scope.pages.push(i+1);
						}
						$scope.$apply();
					} else {
						swal('获取簡歷信息失败：'+ret.errMsg);
					}
				},
				error : function(ret) {
					swal('获取渠道信息失败！');
				},
				contentType : 'application/json'
			});
		};
		$scope.changePage = function(page){
			if(page>0&&$scope.pageTotal>(page-1)*10){
				$scope.pageStart = page;
				$scope.getChannel();
			}else if(page==-1&&$scope.pageStart>1){
				$scope.pageStart--;
				$scope.getChannel();
			}else if(page==-2&&$scope.pageTotal>$scope.pageStart*10){
				$scope.pageStart++;
				$scope.getChannel();
			}else{
				swal("沒有更多數據！");
			}
		};
		$scope.getJobs();
	}
})();