(function(services) {
    'use strict';

    services.factory('TopArtistsService', ['$http', function($http) {
        function TopArtistsService() {
            this.url = '../mocks/top-artists.json';
        }

        TopArtistsService.prototype = {
            getArtists: function(params) {
                return $http({
                    method: 'GET',
                    url: this.url,
                    params: params
                }).then(function(response) {
                    return $.map(response.data, function(artist) {
                        return {
                            name: artist.name,
                            image: artist.picture
                        };
                    });
                });
            }
        };

        return new TopArtistsService();
    }]);

}(window.services));
