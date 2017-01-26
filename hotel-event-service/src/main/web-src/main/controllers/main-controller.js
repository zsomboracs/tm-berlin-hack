(function(controllers) {
    'use strict';

    var ROLLING_INTERVAL = 3000,
        ROLLING_STOP_DELAY = 1000;

    controllers.controller('MainController', ['$scope', '$q', 'TopArtistsService', 'EventsService', 'HotelDealsService', function($scope, $q, TopArtistsService, EventsService, HotelDealsService) {
        function MainController() {
            /*this.getArtists();
            this.getEvents();
            this.getHotels();*/
        }

        MainController.prototype = {
            participants: 2,
            fromDate: null,
            toDate: null,

            artists: [],
            events: [],
            hotels: [],

            selectedItems: {
                artist: null,
                event: null,
                hotel: null
            },

            getDateFor: function(offset) {
                var currentDate = new Date();
                currentDate.setDate(currentDate.getDate() + offset);

                return currentDate.toString();
            },

            getArtists: function() {
                console.log('getArtists');
                return TopArtistsService.getArtists({
                    startDate: this.fromDate,
                    endDate: this.toDate
                }).then(function(data) {
                    this.artists = data;
                }.bind(this));
            },

            getEvents: function() {
                console.log('getEvents');
                return EventsService.getEvents({
                    artistId: this.selectedItems.artist.id,
                    startDate: this.fromDate,
                    endDate: this.toDate
                }).then(function(data) {
                    this.events = data;
                }.bind(this));
            },

            getHotels: function() {
                console.log('getHotels');
                return HotelDealsService.getHotelDeals({
                    latitude: this.selectedItems.event.latitude,
                    longitude: this.selectedItems.event.longitude,
                    checkIn: this.selectedItems.event.startDate,
                    adults: this.participants
                }).then(function(data) {
                    this.hotels = data;
                }.bind(this));
            },

            initGame: function() {
                this.getArtists()
                    .then($.proxy(this.rollArtists, this))
                    .then($.proxy(this.getEvents, this))
                    .then($.proxy(this.rollEvents, this))
                    .then($.proxy(this.getHotels, this))
                    .then($.proxy(this.rollHotels, this));
            },

            rollArtists: function() {
                return this.roll('artist');
            },

            rollEvents: function() {
                return this.roll('event');
            },

            rollHotels: function() {
                return this.roll('hotel');
            },

            roll: function(type) {
                console.log('roll', type);

                var deferred = $q.defer();

                setTimeout(function() {
                    var $list = $('.' + type + '-list'),
                        machine = $list.slotMachine({
                            delay: ROLLING_STOP_DELAY,
                            direction: 'down'
                        });

                    this.selectedItems[type] = null;

                    machine.shuffle(false, function() {
                        $scope.$apply(function() {
                            $scope.vm.selectedItems[type] = $scope.vm[type + 's'][machine.active];
                            console.log('Machine stopped', type, this.selectedItems[type]);
                            deferred.resolve();
                        }.bind(this));
                    }.bind(this));

                    setTimeout(function() {
                        machine.stop();
                    }, ROLLING_INTERVAL);
                }.bind(this), 1000);

                return deferred.promise;
            }
        };

        $scope.vm = new MainController();
        window.test = $scope.vm;
    }]);

}(window.controllers));


