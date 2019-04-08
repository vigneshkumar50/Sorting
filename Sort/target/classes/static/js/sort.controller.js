(function () {

    var app = angular.module("SortApp",['ngStorage']);
    app.controller("SortCtrl", SortCtrl);
    
    function SortCtrl($scope, $http, $location, $localStorage) {
    	// retrieve latest record from browser memory 
    	$scope.latestSortHistory = angular.fromJson(window.localStorage[localStorage.length - 1]);
    	
    	// retrieve previous sorted records from browser memory 		
    	$scope.records = localStorage;
    	$scope.history = []
		angular.forEach($scope.records, function(item){
			var record = angular.fromJson(item);
			$scope.history.push(record);
        })
    	
        $scope.submitForm = function(){
            var url = $location.absUrl() + "sortnumbers";
             
            var config = {
                    headers : {
                        'Content-Type': 'application/json;charset=utf-8;'
                    }
            }
            var data = {
            		unsortednumbers: $scope.unsortednumbers,
            };
            
            $http.post(url, data, config).then(function (response) {
            	$scope.response = response.data;
            	$scope.postResultMessage = response.data.status;
            	
            	$scope.unSortedNumbers = response.data.unSortedNumbers;
            	$scope.sortedNumbers = response.data.sortedNumbers;
            	$scope.duration = response.data.duration + " ns";
            	$scope.swapCount = response.data.swapCount;
            	
            	if(response.data.status == "success"){
            		$scope.showSuccessAlert = true;
            		$scope.showFailureAlert = false;
            	}else{
            		$scope.showSuccessAlert = false;
            		$scope.showFailureAlert = true;
            	}
            		
            	// if response is success then stores the sorted result to browser memory
            	if ($scope.showSuccessAlert) {
            		var sorted_result = {
            				id: localStorage.length + 1,
            				unSortedNumbers : $scope.unSortedNumbers,
            				sortedNumbers : $scope.sortedNumbers,
            				duration : $scope.duration,
            				swapCount : $scope.swapCount
            		}
            		window.localStorage[localStorage.length] = angular.toJson(sorted_result);
            	}
            	
            	// retrieve latest record from browser memory
        		$scope.latestSortHistory = angular.fromJson(window.localStorage[localStorage.length - 1]);
        		// retrieve previous records from browser memory
        		$scope.records = localStorage;
        		$scope.history = []
        		angular.forEach($scope.records, function(item){
        			var record = angular.fromJson(item);
        			$scope.history.push(record);
                })
            	
            }, function (response) {
                //$scope.postResultMessage = "Fail!";
            	$scope.postResultMessage = response.data.status;
            });
             
            $scope.unsortednumbers = "";
        }
    }


})();