(function(window) {
    'use strict';

    window.directives = angular.module('Directives', []);
    window.services = angular.module('Services', []);
    window.controllers = angular.module('Controllers', []);

    window.app = angular.module('TicketMachineApp', ['Directives', 'Services', 'Controllers']);
}(window));


