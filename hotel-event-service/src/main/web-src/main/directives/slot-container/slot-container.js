(function(directives) {
    'use strict';

    directives.directive('slotContainer', function() {
        return {
            restrict: 'E',
            scope: {
                type: '@type',
                items: '=items'
            },
            templateUrl: 'directives/slot-container/slot-container.html',
            controller: ['$scope', function($scope) {
                var placeholders = {
                        artist: {
                            image: null,
                            name: 'Artists',
                            price: 'TicketMaster'
                        },
                        event: {
                            image: null,
                            name: 'Events',
                            price: 'TicketMaster'
                        },
                        hotel: {
                            image: null,
                            name: 'Hotel deals',
                            price: 'Hotels.com'
                        }
                    };

                $scope.placeholder = placeholders[$scope.type];
            }]
        };
    });

}(window.directives));
