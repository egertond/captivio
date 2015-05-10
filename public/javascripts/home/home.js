'use strict';

angular.module('myApp.home', [ 'ngRoute' ])

.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/home', {
		templateUrl : '/assets/templates/home.html',
		controller : 'HomeController'
	});
} ])

.controller('HomeController',
		[ '$scope', '$location', function($scope, $location) {
			$scope.isActive = function(view) {
				return view === $location.path();
			}
		} ]);