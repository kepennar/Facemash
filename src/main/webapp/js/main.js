// Set the require.js configuration for your application.
require.config({
  paths: {
    'jquery': 'libs/jquery',
    'underscore': 'libs/underscore',
    'backbone': 'libs/backbone',
    'text': 'libs/text',	
    'jqueryform': 'libs/jqueryform',
    'atmosphere' : 'libs/jquery.atmosphere'
  }
});

// Load our app module and pass it to our definition function
require(['jquery', 'router', 'backbone', 'views/votes', 'navbar'] , function($, AppRouter,Backbone, VotesView) { 
	
    new AppRouter;
    Backbone.history.start();
    new VotesView({el: $('#totalVotes')});
    
});