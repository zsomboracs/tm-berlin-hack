(function(services) {
    'use strict';

    services.factory('EventsService', ['$http', function($http) {
        function EventsService() {
            this.url = '../mocks/events.json';
        }

        EventsService.prototype = {
            getEvents: function(params) {
                return $http({
                    method: 'GET',
                    url: this.url,
                    params: params
                }).then(function(response) {
                    return $.map(response.data, function(event) {
                        return {
                            name: event.city + ', ' + event.countryCode,
                            image: event.cityImageUrl,
                            price: event.minPrice,
                            url: event.url
                        };
                    });
                });
            }
        };

        return new EventsService();
    }]);

}(window.services));
