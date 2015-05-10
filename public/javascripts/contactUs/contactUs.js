'use strict';

angular.module('myApp.contactUs', [ 'ngRoute' ])

.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/contactUs', {
		templateUrl : '/assets/templates/contactUs.html',
		controller : 'ContactUsController'
	});
} ])

.controller('ContactUsController',['$scope','$http','$log',function($scope,$http,$log) {
	$scope.formData={};
	$scope.submitForm = function(form) {
		$scope.submitted = true;
		$scope.processing=true;
		if (form.$invalid) {
			return;
		}
		$http.post("/contactUs", $scope.formData)
		.success(function(data, status, headers, config) {
			if (status == 200) {
				$scope.formData.name = null;
				$scope.formData.email = null;
				$scope.formData.address = null;
				$scope.formData.message = null;
				$scope.messages = data.message;
				$scope.submitted = false;
				$scope.success=true;
			} else {
				$scope.messages = data.message;
				$log.error(data);
				$scope.success=false;
			}
			$scope.processing=false;
		})
		.error(function(data, status, headers, config) {
			$scope.messages = data.message;
			$log.error(data);
			$scope.success=false;
		});
	};
} ]);