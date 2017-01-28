(function(services) {
    'use strict';

    services.factory('EventsService', ['$http', function($http) {
        function EventsService() {
            //this.url = '../mocks/events.json';
            this.url = '/events';
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
                            city: event.city,
                            date: moment(event.localStartDate).format('YYYY.MM.DD.'),
                            time: event.localStartTime,
                            price: event.minPrice,
                            priceFormatted: '$' + parseFloat(event.minPrice),
                            venue: {
                                name: event.venueName,
                                address: event.venueAddress,
                                longitude: event.longitude,
                                latitude: event.latitude,
                            },
                            url: event.url
                        };
                    });
                });
            }
        };

        return new EventsService();
    }]);

}(window.services));
