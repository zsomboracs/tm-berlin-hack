function toISODate(date) {
    return moment(date).format('YYYY-MM-DD');
}

(function(controllers) {
    'use strict';

    var ROLLING_INTERVAL = 100,
        ROLLING_STOP_DELAY = {
            artist: 4000,
            event: 4000,
            hotel: 2500
        };

    controllers.controller('MainController', ['$scope', '$q', 'TopArtistsService', 'EventsService', 'HotelDealsService', function($scope, $q, TopArtistsService, EventsService, HotelDealsService) {
        function MainController() {
            this.locked = false;
            this.initialized = false;
            this.participants = 2;
            this.fromDate = this.getDateFor(0);
            this.toDate = this.getDateFor(90);

            $scope.$on('initGame', $.proxy(this.initGame, this));
        }

        MainController.prototype = {
            artists: [],
            events: [],
            hotels: [],

            selectedItems: {
                artist: null,
                event: null,
                hotel: null
            },

            machines: {
                artist: null,
                event: null,
                hotel: null
            },

            getDateFor: function(offset) {
                var currentDate = new Date();
                currentDate.setDate(currentDate.getDate() + offset);

                return currentDate;
            },

            getArtists: function() {
                console.log('Loading artists...');
                return TopArtistsService.getArtists({
                    startDate: toISODate(this.fromDate),
                    endDate: toISODate(this.toDate)
                }).then(function(artists) {
                    this.artists = artists;
                }.bind(this));
            },

            getEvents: function() {
                console.log('Loading events...');
                return EventsService.getEvents({
                    artistId: this.selectedItems.artist.id,
                    startDate: toISODate(this.fromDate),
                    endDate: toISODate(this.toDate)
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
                    latitude: this.selectedItems.event.venue.latitude,
                    longitude: this.selectedItems.event.venue.longitude,
                    checkIn: toISODate(this.selectedItems.event.startDate),
                    adults: this.participants
                }).then(function(hotels) {
                    this.hotels = hotels;
                }.bind(this));
            },

            initGame: function() {
                this.locked = true;
                this.resetState();
                this.getArtists()
                    .then($.proxy(this.rollArtists, this))
                    .then($.proxy(this.getEvents, this))
                    .then($.proxy(this.rollEvents, this))
                    .then($.proxy(this.getHotels, this))
                    .then($.proxy(this.rollHotels, this))
                    .then($.proxy(function() {
                        var interval = setInterval($.proxy(function() {
                            if(!(this.machines.artist.running && this.machines.event.running && this.machines.hotel.running)) {
                                $scope.$apply($.proxy(function() {
                                    this.locked = false;
                                    console.log('ALL DONE');
                                    clearInterval(interval);
                                }, this));
                            }
                        }, this), 100);
                    }, this));
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

                var selectionDeferred = $q.defer();

                setTimeout($.proxy(function() {
                    var $list = $('.' + type + '-list'),
                        $placeholder = $('.slot-placeholder[data-type="' + type + '"]'),
                        machine = $list.slotMachine({
                            delay: ROLLING_STOP_DELAY[type],
                            direction: 'down'
                        });

                    $placeholder.hide();
                    this.selectedItems[type] = null;
                    this.machines[type] = machine;

                    machine.shuffle(true, function() {
                        console.log(type, 'onstopcb');
                    });

                    setTimeout($.proxy(function() {
                        $scope.$apply($.proxy(function() {
                            machine.stop();
                            $scope.vm.selectedItems[type] = $scope.vm[type + 's'][machine.active];
                            console.log('Rolling stopped for ' + type + 's: ', this.selectedItems[type].name);
                            selectionDeferred.resolve();
                        }, this));
                    }, this), ROLLING_INTERVAL);
                }, this), 1000);

                return selectionDeferred.promise;
            }
        };

        $scope.vm = new MainController();
        window.test = $scope.vm;
    }]);

}(window.controllers));


