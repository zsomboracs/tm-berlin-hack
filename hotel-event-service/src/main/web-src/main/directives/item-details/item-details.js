(function(directives) {
    'use strict';

    directives.directive('itemDetails', function() {
        return {
            restrict: 'E',
            scope: {
                type: '@type',
                item: '=item'
            },
            templateUrl: 'directives/item-details/item-details.html'
        };
    });

}(window.directives));
