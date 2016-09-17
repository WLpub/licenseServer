(function(){
	'use strict';

	angular
		.module('uumaiApp')
		.constant('goodsOrderActiveClass', 'btn-success')
		.constant('goodsViewActiveClass', 'orange')
		.controller('ListCtrl', ListCtrl);

	function ListCtrl($scope, $http, $rootScope, goodsOrderActiveClass, goodsViewActiveClass){
		$scope.filter = {
			"query":'',
			"username":'',
			"startYear":'',
			"endYear":'',
			"company":'',
			"startConnection":'',
			"endConnection":'',
			"showContact":'1',
			"school":'',
			"address":'',
			"is985":'0',
			"is211":'1'
		}
		$scope.goodsOrder = [{
			type: "category_quanzhong",
			name: "综合排序"
		},{
			type: "price",
			name: "价格"
		},{
			type: "customerreview",
			name: "评论数"
		}];
		
		$scope.resetOrder = function(order){
			if( $scope.predicate == order){
				$scope.predicate = "-" + order;
			}else{
				$scope.predicate = order;
			}
			$scope.changeFilter();
		};
		$scope.setSelectedClass = function(type){
			if ( $scope.predicate == type || $scope.predicate == "-"+type ){
				return goodsOrderActiveClass;
			}else{
				return "";
			}
		};

		//list or table
		$scope.showBlock = false;
		$scope.showList = true;
		$scope.showFeedBack = false;
		$scope.viewType = "list";
		$scope.showGoods = function(type){
			if(!!!$scope.total){
				$scope.showBlock = false;
				$scope.showList = false;
				$scope.showFeedBack = true;
				return;
			}
			if( type == "block" ){
				$scope.showBlock = true;
				$scope.showList = false;
				$scope.showFeedBack = false;
				$scope.viewType = "block";
			}else{
				$scope.showBlock = false;
				$scope.showList = true;
				$scope.showFeedBack = false;
				$scope.viewType = "list";
			}
		};
		$scope.setView = function(type){
			return $scope.viewType == type? goodsViewActiveClass : "";
		};

		//get first page data
		//getPageData('data/list.json',[{query : $rootScope.query}]); 
		
		/**
		  * This function used to get the next page data from servers
		  *
		  * @param page      the current page number
		  * @param pageSize  the number of goods show in one page
		  * @param total     the total number of goods which we find in DB
		  *
		  */
		$scope.DoCtrlPagingAct = function(page, pageSize){
			
			$scope.currentPage = page>1?page-1:0;
			$scope.pageSize = pageSize;
			getPageData();
		};
		
		//search new good
		$scope.searchGoods = function(){
			$rootScope.query = $scope.query;
			$scope.currentPage = 0;
			getPageData();
		};

		$scope.enterSearchWord = function(event){
			if(event.keyCode !== 13) return;
			$scope.searchGoods();
		};

		//filter && loading Page
		$scope.highFilter = false;
		$scope.lowFilter = null;
		$scope.changeFilter = function(){
			$scope.currentPage = 0;
			getPageData();
		};
		
		// http request for getting page date
		var getPageData = function(){
			$.ajax({  
				type:'post',      
				url: './search',
				data:JSON.stringify($scope.filter),    
				dataType:'json', 
				contentType: 'application/json',		
				success:function(data){
					console.log(data);
					var goodData = [];
					$scope.goodBlocks = !!$scope.goodBlocks?$scope.goodBlocks:{};
					$scope.goodBlocks.items = goodData;
					
					$scope.total = data.hits.total;
					
					// init paging
					$scope.query = $rootScope.query;
					
					$scope.showGoods($scope.viewType);

					if(!!!$scope.pageSize){
						$scope.pageSize = 20;
					}
					if(!!!$scope.currentPage){
						$scope.currentPage = 1;
					}
					$scope.dots = "...";
					$scope.adjacent = 2;
					$scope.ulClass = "pagination";
					$scope.activeClass = "active";
					$scope.disabledClass = "disabled";
					$scope.hideIfEmpty = true;
					$scope.scrollTop = true;
					$scope.showPrevNext = false;
					$scope.$apply();
				}
			});  
		};

		// event listener
		$scope.handleEvent = function (e) {
            $scope.data.columnColor = e.type == "mouseover" ? "Green" : "Blue";
		};
		$scope.searchGoods();
    }

})();