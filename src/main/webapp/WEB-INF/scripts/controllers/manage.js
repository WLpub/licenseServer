(function(){
	'use strict';

	angular
		.module('uumaiApp')
		.controller('MainCtrl', MainCtrl)
		.controller('ProductCtrl', ProductCtrl)
		.controller('RecordCtrl', RecordCtrl)
		.controller('CompanyAuthCtrl', CompanyAuthCtrl)
		.controller('CompanyManageCtrl', CompanyManageCtrl);

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
						$scope.generalInfo.user.isCompany = ret.user.permission.charAt(2)=='0'?'非':(ret.user.permission.charAt(2)=='1'?'未认证':'认证');
						$scope.generalInfo.user.companyHref = ret.user.permission.charAt(2)=='0'?'#/':(ret.user.permission.charAt(2)=='1'?'#/companyAuth':'#/companyManage');
						$scope.generalInfo.user.isGithub = ret.user.permission.charAt(3)=='0'?'未加入':'加入';
						$scope.generalInfo.user.gitHref = ret.user.permission.charAt(3)=='0'?'#/githubAuth':'#/githubManage';
						$scope.generalInfo.user.isTeacher = ret.user.permission.charAt(4)=='0'?'未认证':'认证';
						$scope.generalInfo.user.teacherHref = ret.user.permission.charAt(4)=='0'?'#/teacherAuth':'#/teacherManage';
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
						swal(ret.errMsg);
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
	function parsePrice(price){
		var ret = {};
		price = eval(price);
		for(var a in price){
			var p  = price[a];
			ret[p["id"]] = p;
		}
		return ret;
	}
	function RecordCtrl($rootScope,$scope){
		$scope.records=[];
		$scope.pageStart = 1;
		$scope.pages = [1];
		$scope.pageTotal = 10;
		$scope.productsJson = {};
		$scope.getProducts = function(){
			$.ajax({
				type : 'POST',
				url : "./getProducts",
				data : JSON.stringify({}),
				success : function(ret) {
					if (ret.status > -1) {
						$scope.products = ret.products;
						for(var a in $scope.products){
							var product = $scope.products[a];
							product['price'] = parsePrice(product["price"]);
							$scope.productsJson[product['id']] = product;
						}
						$rootScope.productsJson = $scope.productsJson;
						$scope.$apply();
					} else {
						swal('获取产品信息失败：'+ret.errMsg);
					}
				},
				error : function(ret) {
					swal('获取产品信息失败！');
				},
				contentType : 'application/json'
			});
		}
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
		if(!!!$rootScope.productsJson||$rootScope.productsJson=={}){
			$scope.getProducts();
		}else{
			$scope.productsJson = $rootScope.productsJson;
		}
		$scope.getRecords();
	};
	
	function CompanyAuthCtrl($scope){
		var options = {
			target : 'nm_iframe', // 把服务器返回的内容放入id为output的元素中
			// beforeSubmit: showRequest, //提交前的回调函数
			success : function(ret) {
				console.log(ret);
				if (ret.status < 0)
					swal("提交失败：" + ret.errMsg);
				else
					swal("提交成功!");
			}, // 提交后的回调函数
			url: "./uploadCompany", //默认是form的action， 如果申明，则会覆盖
			// type: type, //默认是form的method（get or post），如果申明，则会覆盖
			dataType: "json", //html(默认), xml, script, json...接受服务端返回的类型
			clearForm: true, //成功提交后，清除所有表单元素的值
			resetForm: true //成功提交后，重置所有表单元素的值
			// timeout: 3000 //限制请求的时间，当请求大于3秒后，跳出请求
		};
	    $scope.submitComapny = function(){
	    	$("#companyForm").ajaxSubmit(options);
	    	return false;
	    };
	};
	
	function CompanyManageCtrl($scope){
		$scope.company = {'license':'defalut.jpg'};
		$scope.getCompany = function(){
			$.ajax({
				type : 'POST',
				url : "./getCompany",
				data : JSON.stringify({'start':0}),
				success : function(ret) {
					if (ret.status > -1) {
						$scope.company = ret.companys[0];
						$scope.$apply();
					} else {
						swal('获取执照信息失败：'+ret.errMsg);
					}
				},
				error : function(ret) {
					swal('获取执照信息失败！');
				},
				contentType : 'application/json'
			});
		};
		

		var options = {
			target : 'nm_iframe', // 把服务器返回的内容放入id为output的元素中
			// beforeSubmit: showRequest, //提交前的回调函数
			success : function(ret) {
				console.log(ret);
				if (ret.status < 0)
					swal("提交失败：" + ret.errMsg);
				else
					swal("提交成功!");
			}, // 提交后的回调函数
			url : "./mailCompany", // 默认是form的action， 如果申明，则会覆盖
			// type: type, //默认是form的method（get or post），如果申明，则会覆盖
			dataType : "json", // html(默认), xml, script, json...接受服务端返回的类型
			clearForm : true, // 成功提交后，清除所有表单元素的值
			resetForm : true
			// 成功提交后，重置所有表单元素的值
			// timeout: 3000 //限制请求的时间，当请求大于3秒后，跳出请求
		};
		$scope.sendMail = function(){
			$("#companyForm").ajaxSubmit(options);
	    	return false;
		}
		$scope.getCompany();
	};
})();