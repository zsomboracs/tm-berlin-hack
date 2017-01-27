(function(directives) {
    'use strict';

    directives.directive('slotItem', function() {
        return {
            restrict: 'E',
            scope: {
                item: '=item'
            },
            templateUrl: 'directives/slot-item/slot-item.html'
        };
    });

}(window.directives));
