(function(){
	'use strict';

	angular
		.module('uumaiApp')
		.controller('MainCtrl', MainCtrl)
		.controller('WayCtrl', WayCtrl)
		.controller('FileCtrl', FileCtrl)
		.controller('AddWayCtrl', AddWayCtrl)
		.controller('ChangeWayCtrl', ChangeWayCtrl);

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
						$scope.generalInfo.totalWay = ret.totalWay;
						$scope.generalInfo.totalFile = ret.totalFile;
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
	}
	function WayCtrl($rootScope, $scope){
		$scope.channels = [];
		$scope.jobNameJson={
				1:'51job',
				2:'智联',
				3:'拉钩',
				4:'猎聘',
				5:'linkedin'
		};
		$scope.statusNameJson={
				'NORMAL':'正常',
				'STOP':'停用',
				'DELETE':'删除'
		};
		$scope.pageStart = 1;
		$scope.pages = [1];
		$scope.pageTotal = 10;
		$scope.getChannel = function() {
			$.ajax({
				type : 'POST',
				url : "./getChannelByUserId",
				data : JSON.stringify({'start':($scope.pageStart*10-10)||0,'channel':{}}),
				success : function(ret) {
					if (ret.status > -1) {
						$scope.channels = ret.channels;
						$scope.pages = [];
						$scope.pageTotal = ret.count||10;
						for(var i=0;i<$scope.pageTotal/10;i++){
							$scope.pages.push(i+1);
						}
						$scope.$apply();
					} else {
						swal('获取渠道信息失败：'+ret.errMsg);
					}
				},
				error : function(ret) {
					swal('获取渠道信息失败！');
				},
				contentType : 'application/json'
			});
		};
		$scope.changeState = function(state,index){
			var temp = $scope.channels[index].status;
			$scope.channels[index].status = state;
			$.ajax({
				type : 'POST',
				url : "./changeChannelState",
				data : JSON.stringify($scope.channels[index]),
				success : function(ret) {
					if (ret.status > -1) {
							$scope.$apply();
							swal("状态修改成功！");
							if(state=="DELETE")window.history.go(0);
					} else {
						channel.status =temp;
					}
				},
				error : function(ret) {
					channel.status =temp;
				},
				contentType : 'application/json'
			});
		};
		$scope.changeWay =  function(channel){
			console.log(channel);
			$rootScope.changedChannel = channel;
			window.location.href="#/changeWay?id="+channel.id;
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
		$scope.getChannel();
	}
	function FileCtrl($rootScope, $scope){
		$scope.jobs=[];
		$scope.jobNameJson={
				1:'51job',
				2:'智联',
				3:'拉钩',
				4:'猎聘',
				5:'linkedin'
		};
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
	function AddWayCtrl($rootScope, $scope){
		$scope.channel={
			'channel_id':0,
			'username':"",
			'account':"",
			'password':""
		};
		$scope.ways = [
		   	{'name':'51job','value':'1'},
			{'name':'智联','value':'2'},
			{'name':'拉钩','value':'3'},
			{'name':'猎聘','value':'4'},
			{'name':'linkedin','value':'5'}
		];
		$scope.changeChannelWay = function(){
			if($scope.channel.channel_id!=1){
				$scope.channel.username = "";
			}
		}
		$scope.addChannel =function(){
			$.ajax({
				type : 'POST',
				url : "./createChannel",
				data : JSON.stringify($scope.channel),
				success : function(ret){
					if(ret.status>-1){
						$scope.channel={
								'channel_id':0,
								'username':"",
								'account':"",
								'password':""
						};
						swal("添加成功！");
					}else{
						swal('添加失败！'+ret.errMsg);
					}
				},
				error:function(ret){
					swal('添加失败！');
				},
				contentType : 'application/json'
			});
		};
	}
	function ChangeWayCtrl($rootScope, $scope){
		$scope.jobNameJson={
				1:'51job',
				2:'智联',
				3:'拉钩',
				4:'猎聘',
				5:'linkedin'
		};
		$scope.channel = $rootScope.changedChannel;
		if(!!!$scope.channel||!!!$scope.channel.id){
			$scope.channel  ={};
			$scope.channel.id=window.location.href.split("?id=")[1];
			$.ajax({
				type : 'POST',
				url : "./getChannelById",
				data : JSON.stringify($scope.channel),
				success : function(ret){
					if(ret.status>-1){
						$scope.channel = ret.channels[0];
						$scope.$apply();
					}else{
						$swal("獲取信息失敗："+ret.errMsg);
					}
				},
				error:function(ret){
					$swal("獲取信息失敗！");
				},
				contentType : 'application/json'
			});
		}
		$scope.changeWay = function(){
			$.ajax({
				type : 'POST',
				url : "./changeChannel",
				data : JSON.stringify($scope.channel),
				success : function(ret){
					if(ret.status>-1){
						swal("修改成功！");
						$scope.$apply();
					}else{
						swal( '修改失败！'+ret.errMsg);
					}
				},
				error:function(ret){
					swal( '修改失败！'+ret.errMsg);
				},
				contentType : 'application/json'
			});
		}
	};
})();