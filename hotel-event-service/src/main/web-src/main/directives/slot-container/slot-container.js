(function(directives) {
    'use strict';

    directives.directive('slotContainer', function() {
        return {
            restrict: 'E',
            scope: {
                type: '@type',
                items: '=items'
            },
            templateUrl: 'directives/slot-container/slot-container.html'
        };
    });

}(window.directives));
