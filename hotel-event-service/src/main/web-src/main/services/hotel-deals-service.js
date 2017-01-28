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
                    if(response.data.result && response.data.result.length) {
                        return $.map(response.data.result, function(hotel) {
                            return {
                                name: hotel.name,
                                image: hotel.image,
                                address: hotel.address,
                                priceFormatted: hotel.price,
                                starRating: parseInt(hotel.starRating, 10),
                                guestRating: hotel.guestRating,
                                tripAdvisorRating: hotel.tripAdvisorRating,
                                url: hotel.url
                            };
                        });
                    } else {
                        return [
                            {
                                name: 'Hotel Alexander Plaza',
                                image: 'http://exp.cdn-hotels.com/hotels/1000000/530000/526000/525997/400809e0_b.jpg',
                                address: 'Hotel Alexander Plaza',
                                priceFormatted: '$83',
                                starRating: 4,
                                guestRating: 4.4,
                                tripAdvisorRating: 4.0,
                                url: 'https://www.hotels.com/hotel/details.html?q-check-out=2017-02-03&q-check-in=2017-02-01&WOE=5&WOD=3&q-room-0-children=0&pa=1&tab=description&hotel-id=232896&q-room-0-adults=1&YGF=3&MGT=2&ZSX=0&SYE=3&pos=HCOM_US#rooms-and-rates-anchor'
                            },
                            {
                                name: 'Hotel Nikolai Residence',
                                image: 'http://exp.cdn-hotels.com/hotels/7000000/6660000/6654500/6654431/6654431_13_b.jpg',
                                address: 'Hotel Nikolai Residence',
                                priceFormatted: '$105',
                                starRating: 3,
                                guestRating: 4.7,
                                tripAdvisorRating: 4.5,
                                url: 'https://www.hotels.com/hotel/details.html?q-check-out=2017-02-03&q-check-in=2017-02-01&WOE=5&WOD=3&q-room-0-children=0&pa=6&tab=description&hotel-id=442447&q-room-0-adults=1&YGF=3&MGT=2&ZSX=0&SYE=3&pos=HCOM_US#rooms-and-rates-anchor'
                            }
                        ];
                    }
                });
            }
        };

        return new HotelDealsService();
    }]);

}(window.services));
