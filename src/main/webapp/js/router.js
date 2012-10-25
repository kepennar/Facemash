define(['backbone', 'views/about', 'views/winner', 'views/top', 'views/worst', 'views/fight', 'views/search', 'views/upload'],
		function(Backbone, AboutView, WinnerView, TopView, WorstView, FightView, SearchView, UploadView){
	
	var AppRouter = Backbone.Router.extend({
        routes: {
            '': 'home',
            'top': 'top',
            'worst': 'worst',
            'elements' :"elements",
            'search': 'search',
            'about': 'about',
            'upload': 'upload'
        },
        home: function( ){
        	 new WinnerView({el: $('#main')});
        },
        top: function( ){
            new TopView({el: $('#main')});
        },
        worst: function( ){
            new WorstView({el: $('#main')});
        },
        elements: function( ){
            new FightView({el: $('#main')});
        },
        search: function( ){
            new SearchView({el: $('#main')});
        },
        about: function( ){
            new AboutView({el: $('#main')});
        },
        upload: function( ){
            new UploadView({el: $('#main')});
        }
    });
    
    return AppRouter;
});