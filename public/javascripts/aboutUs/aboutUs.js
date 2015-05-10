'use strict';

angular.module('myApp.aboutUs', [ 'ngRoute' ])

.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/aboutUs', {
		templateUrl : '/assets/templates/aboutUs.html',
		controller : 'AboutUsController'
	});
} ])

.controller('AboutUsController',['$scope','$http','$log',function($scope,$http,$log) {
	$http.get('/aboutUs')
	.success(function(data, status, headers, config) {
		if (status == 200) {
			$scope.aboutUs = data;
		} else {
			$log.error(data);
		}
	})
	.error(function(data, status, headers, config) {
		$log.error(data);
	});
} ]);