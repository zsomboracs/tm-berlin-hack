(function(directives) {
    'use strict';

    directives.directive('itemDetails', function() {
        return {
            restrict: 'E',
            scope: {
                type: '@type',
                item: '=item',
                participants: '=participants'
            },
            templateUrl: 'directives/item-details/item-details.html',
            controller: ['$scope', function($scope) {
                $scope.range = function(n) {
                    return new Array(n || 0);
                };
            }]
        };
    });

}(window.directives));
