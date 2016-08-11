angular.module('sbAdminApp')
.controller('NavQuestionEditorCtrl', function($scope, $log, $q, QuestionEditorService){
	$log.debug('enter questions editor ctrl');
	
	$scope.totalQuestions = 100;
	$scope.currentPage = 3;
	$scope.changePage = function(){
	    var paginationOptions = {
			    pageNumber: $scope.currentPage,
			    pageSize: 10
			    sort: null
	    };
	    getPage();
		$log.debug("page changed", $scope.currentPage);
	};
	
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
});
