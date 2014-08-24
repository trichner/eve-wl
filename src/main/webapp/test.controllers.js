var evewt = angular.module('test', []);

evewt.controller('test-controller',[ '$scope','$http','$location', function ($scope,$http,$location) {
    $scope.$location = $location;
}]);