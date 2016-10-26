(function(){
	'use strict';

	angular
		.module('uumaiApp')
		.controller('MainCtrl', MainCtrl)
		.controller('QlifEvalCtrl', QlifEvalCtrl)
		.controller('LicsExamCtrl', LicsExamCtrl);

	function MainCtrl($rootScope, $scope){
	};
	function QlifEvalCtrl($rootScope,$scope){
		$scope.records=[];
		$scope.pageStart = 1;
		$scope.pages = [1];
		$scope.pageTotal = 10;
		$scope.statusList = [{'name':'未审核','value':'0'},{'name':'通过','value':'1'},{'name':'不通过','value':'-1'}];
		$scope.statusTrans = {'0':'未审核','1':'通过','-1':'未通过'};
		$scope.statusChoosed = '0';
		$scope.getCompanys = function() {
			$.ajax({
				type : 'POST',
				url : "./getAllCompanyByStatus",
				data : JSON.stringify({'start':($scope.pageStart*10-10)||0,'company':{'status':$scope.statusChoosed}}),
				success : function(ret) {
					if (ret.status > -1) {
						$scope.companys = ret.companys;
						$scope.pages = [];
						$scope.pageTotal = ret.count||10;
						for(var i=0;i<$scope.pageTotal/10;i++){
							$scope.pages.push(i+1);
						}
						$scope.$apply();
					} else {
						swal('获取购买记录信息失败：'+ret.errMsg);
					}
				},
				error : function(ret) {
					swal('获取购买记录信息失败！');
				},
				contentType : 'application/json'
			});
		};
		$scope.changeStatus = function(company){
			$.ajax({
				type : 'POST',
				url : "./updateCompanyStatus",
				data : JSON.stringify({'id':company.id,'status':company.statusChanged}),
				success : function(ret) {
					if (ret.status > -1) {
						company.status = company.statusChanged;
						$scope.$apply();
					} else {
						swal('更新失败，请重试！');
					}
				},
				error : function(ret) {
					swal('更新失败，请重试！');
				},
				contentType : 'application/json'
			});
		};
		$scope.changePage = function(page){
			if(page>0&&$scope.pageTotal>(page-1)*10){
				$scope.pageStart = page;
				$scope.getRecords();
			}else if(page==-1&&$scope.pageStart>1){
				$scope.pageStart--;
				$scope.getRecords();
			}else if(page==-2&&$scope.pageTotal>$scope.pageStart*10){
				$scope.pageStart++;
				$scope.getRecords();
			}else{
				swal("沒有更多數據！");
			}
		};
		$scope.getCompanys();
	};
	function LicsExamCtrl($rootScope,$scope){
		$scope.records=[];
		$scope.pageStart = 1;
		$scope.pages = [1];
		$scope.pageTotal = 10;
		$scope.getRecords = function() {
			$.ajax({
				type : 'POST',
				url : "./getRecord",
				data : JSON.stringify({'start':($scope.pageStart*10-10)||0,'resume':{}}),
				success : function(ret) {
					if (ret.status > -1) {
						$scope.records = ret.records;
						$scope.pages = [];
						$scope.pageTotal = ret.count||10;
						for(var i=0;i<$scope.pageTotal/10;i++){
							$scope.pages.push(i+1);
						}
						$scope.$apply();
					} else {
						swal('获取购买记录信息失败：'+ret.errMsg);
					}
				},
				error : function(ret) {
					swal('获取购买记录信息失败！');
				},
				contentType : 'application/json'
			});
		};
		$scope.changePage = function(page){
			if(page>0&&$scope.pageTotal>(page-1)*10){
				$scope.pageStart = page;
				$scope.getRecords();
			}else if(page==-1&&$scope.pageStart>1){
				$scope.pageStart--;
				$scope.getRecords();
			}else if(page==-2&&$scope.pageTotal>$scope.pageStart*10){
				$scope.pageStart++;
				$scope.getRecords();
			}else{
				swal("沒有更多數據！");
			}
		};
		$scope.getRecords();
	};
})();