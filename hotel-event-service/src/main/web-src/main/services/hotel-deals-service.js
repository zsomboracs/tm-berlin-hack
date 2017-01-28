(function(services) {
    'use strict';

    services.factory('HotelDealsService', ['$http', function($http) {
        function HotelDealsService() {
            //this.url = '../mocks/hotels.json';
            this.url = '/hotels';
        }

        HotelDealsService.prototype = {
            getHotelDeals: function(params) {
                return $http({
                    method: 'GET',
                    url: this.url,
                    params: params
                }).then(function(response) {
                    return $.map(response.data.result, function(hotel) {
                        return {
                            name: hotel.name,
                            image: hotel.image,
                            priceFormatted: hotel.price,
                            starRating: parseInt(hotel.starRating, 10),
                            guestRating: hotel.guestRating,
                            tripAdvisorRating: hotel.tripAdvisorRating,
                            url: hotel.url
                        };
                    });
                });
            }
        };

        return new HotelDealsService();
    }]);

}(window.services));
