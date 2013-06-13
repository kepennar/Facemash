// Set the require.js configuration for your application.
require.config({
  paths: {
    jquery: 'libs/jquery',
    underscore: 'libs/underscore',
    backbone: 'libs/backbone',
    marionette: 'libs/backbone.Marionette.min.js',
    text: 'libs/text',	
    jqueryform: 'libs/jqueryform',
    atmosphere: 'libs/jquery.atmosphere',
    mainView: 'views/MainView',
    bAlert: 'libs/bootstrap-alert'
  },
  shim: {
		backbone: {
	        deps: ["underscore", "jquery"],
	        exports: "Backbone"
	    },
	    marionette : {
	        deps : ['jquery', 'underscore', 'backbone'],
	        exports : 'Marionette'
	    },
        underscore: {
            exports: '_'
        }, 
        atmosphere: {
        	deps: ["jquery"],
        	exports: "atmosphere"
        }, 
        bAlert: {
        	deps: ["jquery"],
        	exports: "bAlert"
        }
  },
  waitSeconds: 15
});


 

// Load our app module and pass it to our definition function
define(['router', 'views/votes', 'navbar', 'bAlert'] , function(AppRouter, VotesView) { 
	'use strict';
	
	var $alertDiv = $("#alertDiv");
	
	$alertDiv.bind('closed', function () {
		$(this).find("#alertTitle").html("");
		$(this).find("#alertMessage").html("");
	});
	
	new AppRouter;
    Backbone.history.start();
    new VotesView({el: $('#totalVotes')});
    
});