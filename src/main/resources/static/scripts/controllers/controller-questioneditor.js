angular.module('sbAdminApp')
.filter('mapGender', function() {
	  var genderHash = {
	    1: 'amale',
	    2: 'afemale'
	  };
	 
	  return function(input) {
	    if (!input){
	      return '';
	    } else {
	      return genderHash[input];
	    }
	  };
	})
.controller('QuestionEditorCtrl', function($scope, $log, $q, QuestionEditorService, uiGridConstants){
	$log.debug('enter questions editor ctrl');
	  var paginationOptions = {
	    pageNumber: 1,
	    pageSize: 25,
	    sort: null
	  };
	$scope.law = QuestionEditorService.law;
	$scope.lawChapter = QuestionEditorService.lawChapter;
	function getPage(){
		var promise = QuestionEditorService.questions(paginationOptions);
		promise.then(
				function(data){
					$log.debug(data);
					$scope.questionsGrid.data = data.data;
					$scope.questionsGrid.totalItems = data.recordsTotal;
				},
				function(error){
					$log.debug(error);
				}
		);	}
	//加载第一页
	getPage();
	$scope.questionsGrid = {
			enableSorting : false,
			enableCellEditOnFocus : true,
		    paginationPageSizes: [25, 50, 75],
		    paginationPageSize: 25,
		    useExternalPagination: true,		    
			columnDefs :[
			     {
			    	 name : 'ID',
			    	 field : 'id',
			    	 enableCellEdit: false,
			    	 width:"1%"
			     },        
			     {
			    	 name : '问题',
			    	 field : 'question', 
			    	 cellTooltip : function(row, col){
			    		 return row.entity.question;
			    	 },
			    	 enableCellEdit: true,
//			    	 editableCellTemplate : '<textarea style="width:100%;height:100%;" ng-class="\'colt\' + col.index" ng-input="COL_FIELD" ng-model="COL_FIELD"></textarea>',
			    	 width:'20%'
			     },
			     {
			    	 name : 'A',
			    	 width : '5%',
			    	 field : 'a',
			    	 enableCellEdit: true
			     },
			     {
			    	 name : 'B',
			    	 width : '5%',
			    	 field : 'b',
			    	 enableCellEdit: true
			     },
			     {
			    	 name : 'C',
			    	 width : '5%',
			    	 field : 'c',
			    	 enableCellEdit: true
			     },
			     {
			    	 name : 'D',
			    	 width : '5%',
			    	 field : 'd',
			    	 enableCellEdit: true
			     },
			     {
			    	 name : '考点',
			    	 width : '5%',
			    	 field : 'point',
			    	 enableCellEdit: true
			     },			     
			     {
			    	 name : '答案',
			    	 width : '5%',
			    	 field : 'answer',
			    	 enableCellEdit: true
			     },
			     {
			    	 name : '解析',
			    	 width : '10%',
			    	 field : 'analysis',
			    	 enableCellEdit: true
			     },			     
			     {
			    	 name : '年份',
			    	 width : '5%',
			    	 field : 'published_at'
			     },
			     {
			    	 name : '法律',
			    	 width : '10%',
			    	 field : 'law_id',
			    	 editableCellTemplate: 'ui-grid/dropdownEditor',
			    	 editDropdownOptionsArray : $scope.law,
			    	 cellFilter : 'mapLaw'
			     },
			     {
			    	 name : '章节',
			    	 width : '10%',
			    	 field : 'chapter_id',
			    	 editableCellTemplate: 'ui-grid/dropdownEditor',
			    	 editDropdownRowEntityOptionsArrayPath : 'chapters',
			    	 cellFilter : 'mapChapter'
			     }
			],
		    onRegisterApi: function( gridApi ) { 
		        $scope.gridApi = gridApi;
		        var cellTemplate = 'ui-grid/selectionRowHeader';   // you could use your own template here
		        $scope.gridApi.core.addRowHeaderColumn( { name: 'rowHeaderCol', displayName: '', width: 30, cellTemplate: cellTemplate} );
		        
		        $scope.gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize) {
		            paginationOptions.pageNumber = newPage;
		            paginationOptions.pageSize = pageSize;
		            getPage();
		        });	
		        
		        $scope.gridApi.edit.on.afterCellEdit($scope, function(rowEntity, colDef, newValue, oldValue){
		        	if(colDef.name === '法律'){
		        		rowEntity.chapters = $scope.lawChapter[newValue];
		        	}
		        });
		        
		        $scope.gridApi.rowEdit.on.saveRow($scope, $scope.saveRow);
		    },			
			data : []
	};

	$scope.saveRow = function(rowEntity){
		var promise = QuestionEditorService.saveRow(rowEntity);
		$scope.gridApi.rowEdit.setSavePromise(rowEntity, promise);
	};
	
	$scope.addQuestion = function(){
		var maxId = $scope.questionsGrid.data.length;
		$scope.questionsGrid.data.push({
			id : maxId,
			question : null,
			a : null,
			b : null,
			c : null,
			d : null,
		});
	};
	
	$scope.publishToSqlite = function(){
		QuestionEditorService.publishToSqlite();
	};
	
}).factory('QuestionEditorService', function($http, $q, $log){
	
	var lawChapter = {};
	var law =[];
	function loadLawChapter(){
		$http.get('/lib/lawchapters').success(function(data){
			$log.debug(data);
			var lawid = [];
			for(var idx in data){
				var i = data[idx];
				if(lawid.indexOf(i.lawid) == -1){
					law.push({id:i.lawid, value:i.lawname});
					lawid.push(i.lawid);
				}
				if(lawChapter[i.lawid] == null){
					lawChapter[i.lawid] = [];
				}
				lawChapter[i.lawid].push({id:i.chapterid, value:i.chaptername});
			}
			$log.debug(lawChapter);
			$log.debug('law', law);
		}).error(function(error){
			$log.debug(error);
		});
	}
	
	loadLawChapter();
	return {
		law : law,
		lawChapter : lawChapter,
	
		publicshToSqlite : function(){
			var defer = $q.defer();
			$http.get('/lib/publish_to_sqlite').success(function(data){
				defer.resolve(data);
			}).error(function(error){
				defer.reject(error);
			});
			return defer.promise;
		},
	
		saveRow : function(row){
			var defer = $q.defer();
			$http.post('/lib/savequestion', row).success(
					function(data){
						$log.debug(data);
						defer.resolve(data);
					}).error(function(error){
						$log.debug(error);
						defer.reject(error);
					});
			return defer.promise;
		},
		questions : function(pageOpt){
			var defer = $q.defer();
			var start = (pageOpt.pageNumber - 1) * pageOpt.pageSize;
			var size = pageOpt.pageSize;
			$http.get('/lib/questions?start=' + start + '&size=' + size).success(
					function(data, status){
						defer.resolve(data);
					}
			).error(
					function(data, status){
						defer.reject(data);
					}
			);
			return defer.promise;
		}
	};
})
.filter('mapLaw', function(QuestionEditorService){
	var law = QuestionEditorService.law;
	return function(input){
		for(var i in law){
			if (law[i].id == input){
				return law[i].value;
			}
		}
	}
})
.filter('mapChapter', function(QuestionEditorService){
	var lawChapter = QuestionEditorService.lawChapter;
	return function(input){
		for(var i in lawChapter)
			for(var j in lawChapter[i]){
				if(lawChapter[i][j].id == input){
					return lawChapter[i][j].value;
				}
			}
	};
});