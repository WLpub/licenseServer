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
				data : JSON.stringify({'id':company.id,'status':company.statusChanged,"userID":company.userID}),
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
				$scope.getCompanys();
			}else if(page==-1&&$scope.pageStart>1){
				$scope.pageStart--;
				$scope.getCompanys();
			}else if(page==-2&&$scope.pageTotal>$scope.pageStart*10){
				$scope.pageStart++;
				$scope.getCompanys();
			}else{
				swal("沒有更多數據！");
			}
		};
		$scope.getCompanys();
	};
	function LicsExamCtrl($rootScope,$scope){
		$scope.licenses=[];
		$scope.pageStart = 1;
		$scope.pages = [1];
		$scope.pageTotal = 10;
		$scope.statusChoosed = '0';
		$scope.statusList = [{'name':'未处理','value':'0'},{'name':'已处理','value':'1'},{'name':'不通过','value':'-1'}];
		$scope.statusTrans = {'0':'未处理','1':'已处理','-1':'未通过'};
		$scope.getLicense = function() {
			$.ajax({
				type : 'POST',
				url : "./getLicenseByStatus",
				data : JSON.stringify({'start':($scope.pageStart*10-10)||0,'license':{'status':$scope.statusChoosed}}),
				success : function(ret) {
					if (ret.status > -1) {
						$scope.licenses = ret.licenses;
						$scope.pages = [];
						$scope.pageTotal = ret.count||10;
						for(var i=0;i<$scope.pageTotal/10;i++){
							$scope.pages.push(i+1);
						}
						$scope.$apply();
					} else {
						swal('获取信息失败：'+ret.errMsg);
					}
				},
				error : function(ret) {
					swal('获取记录信息失败！');
				},
				contentType : 'application/json'
			});
		};
		$scope.changeStatus = function(license){
			$.ajax({
				type : 'POST',
				url : "./updateLicenseStatus",
				data : JSON.stringify({'id':license.id,'status':license.statusChanged,'file':license.file}),
				success : function(ret) {
					if (ret.status > -1) {
						license.status = license.statusChanged;
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
				$scope.getLicense();
			}else if(page==-1&&$scope.pageStart>1){
				$scope.pageStart--;
				$scope.getLicense();
			}else if(page==-2&&$scope.pageTotal>$scope.pageStart*10){
				$scope.pageStart++;
				$scope.getLicense();
			}else{
				swal("沒有更多數據！");
			}
		};
		$scope.getLicense();
	};
})();