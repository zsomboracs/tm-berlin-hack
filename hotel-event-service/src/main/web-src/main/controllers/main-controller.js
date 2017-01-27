(function(controllers) {
    'use strict';

    var ROLLING_INTERVAL = 3000,
        ROLLING_STOP_DELAY = 1000;

    controllers.controller('MainController', ['$scope', '$q', 'TopArtistsService', 'EventsService', 'HotelDealsService', function($scope, $q, TopArtistsService, EventsService, HotelDealsService) {
        function MainController() {
        }

        MainController.prototype = {
            initialized: false,

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
                console.log('Loading artists...');
                return TopArtistsService.getArtists({
                    startDate: this.fromDate,
                    endDate: this.toDate
                }).then(function(artists) {
                    this.artists = artists;
                }.bind(this));
            },

            getEvents: function() {
                console.log('Loading events...');
                return EventsService.getEvents({
                    artistId: this.selectedItems.artist.id,
                    startDate: this.fromDate,
                    endDate: this.toDate
                }).then(function(events) {
                    events.forEach(function(event) {
                        event.artist = this.selectedItems.artist;
                    }.bind(this));
                    this.events = events;
                }.bind(this));
            },

            getHotels: function() {
                console.log('Loading hotel deals...');
                return HotelDealsService.getHotelDeals({
                    latitude: this.selectedItems.event.latitude,
                    longitude: this.selectedItems.event.longitude,
                    checkIn: this.selectedItems.event.startDate,
                    adults: this.participants
                }).then(function(hotels) {
                    this.hotels = hotels;
                }.bind(this));
            },

            initGame: function() {
                this.resetState();
                this.getArtists()
                    .then($.proxy(this.rollArtists, this))
                    .then($.proxy(this.getEvents, this))
                    .then($.proxy(this.rollEvents, this))
                    .then($.proxy(this.getHotels, this))
                    .then($.proxy(this.rollHotels, this));
            },

            resetState: function() {
                this.artists = [];
                this.events = [];
                this.hotels = [];

                this.selectedItems = {
                    artist: null,
                    event: null,
                    hotel: null
                };

                if(this.initialized) {
                    ['artist', 'event', 'hotel'].forEach(function(type) {
                        $('.' + type + '-list').slotMachine().destroy();
                        $('.slot-placeholder[data-type="' + type + '"]').show();
                    });
                } else {
                    this.initialized = true;
                }
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
                console.log('Rolling ' + type + '...');

                var deferred = $q.defer();

                setTimeout(function() {
                    var $list = $('.' + type + '-list'),
                        $placeholder = $('.slot-placeholder[data-type="' + type + '"]'),
                        machine = $list.slotMachine({
                            delay: ROLLING_STOP_DELAY,
                            direction: 'down'
                        });

                    $placeholder.hide();
                    this.selectedItems[type] = null;

                    machine.shuffle(false, function() {
                        $scope.$apply(function() {
                            $scope.vm.selectedItems[type] = $scope.vm[type + 's'][machine.active];
                            console.log('Rolling stopped for ' + type + 's: ', this.selectedItems[type].name);
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


