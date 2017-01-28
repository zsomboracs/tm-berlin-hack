(function(directives) {
    'use strict';

    directives.directive('searchModule', function() {
        return {
            restrict: 'E',
            scope: {
                participants: '=participants',
                fromDate: '=fromDate',
                toDate: '=toDate',
                locked: '=locked',
            },
            templateUrl: 'directives/search-module/search-module.html',
            controller: ['$scope', function($scope) {
                $scope.invalidStartDate = false;
                $scope.invalidEndDate = false;
                $scope.errorMessage = null;

                $scope.search = function() {
                    $scope.$emit('initGame');
                };

                $scope.$watch('fromDate', function() {
                    if(moment(toISODate()).isAfter(toISODate($scope.fromDate))) {
                        $scope.invalidStartDate = true;
                        $scope.errorMessage = 'Your start date can\'t be in the past!';
                    } else {
                        $scope.invalidStartDate = false;
                    }
                });

                $scope.$watch('toDate', function() {
                    if(moment(toISODate($scope.fromDate)).add(30, 'days').isAfter(toISODate($scope.toDate))) {
                        $scope.invalidEndDate = true;
                        $scope.errorMessage = 'Date range must be at least 30 days!';
                    } else {
                        $scope.invalidEndDate = false;
                    }
                });

                window.test2 = $scope;
            }]
        };
    });

}(window.directives));
