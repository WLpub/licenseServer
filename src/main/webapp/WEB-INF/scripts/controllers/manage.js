(function(){
	'use strict';

	angular
		.module('uumaiApp')
		.controller('MainCtrl', MainCtrl)
		.controller('ProductCtrl', ProductCtrl)
		.controller('RecordCtrl', RecordCtrl)
		.controller('CompanyAuthCtrl', CompanyAuthCtrl)
		.controller('CompanyManageCtrl', CompanyManageCtrl)
		.controller('LicenseManageCtrl', LicenseManageCtrl)
		.controller('UpdateInfoCtrl',UpdateInfoCtrl);
		
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
						$scope.generalInfo.user.isCompany = ret.user.permission.charAt(2)=='0'?'非':(ret.user.permission.charAt(2)=='1'?'未认证':(ret.user.permission.charAt(2)=='2'?'认证':'认证失败'));
						$scope.generalInfo.user.companyHref = ret.user.permission.charAt(2)=='0'?'#/':(ret.user.permission.charAt(2)=='1'?'#/companyAuth':(ret.user.permission.charAt(2)=='2'?'#/companyManage':'#/companyAuth'));
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
	function UpdateInfoCtrl($rootScope, $scope){
		$scope.generalInfo = {
				"user":{},
				"totalWay":'',
				"totalFile":''
		};
		$scope.msgBtnFont = "发送验证码";
		$scope.msgSending = true;
		$scope.getInfo = function() {
			$.ajax({
				type : 'POST',
				url : "./getGeneralInfo",
				data : JSON.stringify({}),
				success : function(ret) {
					if (ret.status > -1) {
						$scope.generalInfo.user = ret.user;
						$scope.currentUser = $.extend({},ret.user);
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
				console.log($scope.currentUser.phone,$scope.generalInfo.user.phone);
				if($scope.currentUser.phone===$scope.generalInfo.user.phone)
				{
					swal("未更改手机信息,不需要验证");
					return;
				}
				$scope.msgSending = false;
				$.ajax({
					type : 'POST',
					url : "./phoneMsg",
					data : JSON.stringify({'phone':$scope.currentUser.phone,"msg":"您的验证码为："}),
					success : function(ret){
						if(ret.status>-1){
						}else{
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
		$scope.updateInfo = function(){
			if(!!$scope.currentUser.password&&$scope.currentUser.password!==$scope.currentUser.passwordAgain){
				swal("两次密码不同,请确认后重新输入");
				return;
			}
			if(!!!$scope.currentUser.username) $scope.currentUser.username= $scope.generalInfo.user.username;
			if(!!!$scope.currentUser.email) $scope.currentUser.email= $scope.generalInfo.user.email;
			if(!!!$scope.currentUser.phone) $scope.currentUser.phone= $scope.generalInfo.user.phone;
			if($scope.currentUser.phone!==$scope.generalInfo.user.phone&&!!!$scope.generalInfo.phoneCode){swal("请输入正确的手机验证码"); return;}
			$.ajax({
				type : 'POST',
				url : "./updateUser",
				data : JSON.stringify({"user":$scope.currentUser,'phoneCode':$scope.generalInfo.phoneCode}),
				success : function(ret){
					if(ret.status>-1){
						swal("更新成功！");
					}else{
						swal('发送失败，请稍后再试！');
					}
				},
				error:function(ret){
					swal( '更新失败！');
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
				data : JSON.stringify({'start':($scope.pageStart*10-10)||0,'record':{}}),
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
			url : "./createLicense", // 默认是form的action， 如果申明，则会覆盖
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
	function LicenseManageCtrl($rootScope,$scope){
		$scope.licenses=[];
		$scope.pageStart = 1;
		$scope.pages = [1];
		$scope.pageTotal = 10;
		$scope.statusChoosed = '';
		$scope.statusList = [{'name':'未处理','value':'0'},{'name':'已处理','value':'1'},{'name':'不通过','value':'-1'}];
		$scope.statusTrans = {'0':'未处理','1':'已处理','-1':'未通过'};
		$scope.getLicense = function() {
			$.ajax({
				type : 'POST',
				url : $scope.statusChoosed===''?"./getLicenseByUserID":"./getLicenseByStatusUserID",
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