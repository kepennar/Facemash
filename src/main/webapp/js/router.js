define(['backbone', 'jquery', 'views/about', 'views/winner', 'views/top', 'views/worst', 'views/fight', 'views/search', 'views/upload'],
		function(Backbone, $, AboutView, WinnerView, TopView, WorstView, FightView, SearchView, UploadView){
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
        	$(document).trigger('CloseView');
        	new WinnerView({el: $('#main')});
        },
        
        top: function( ){
        	$(document).trigger('CloseView');
        	new TopView({el: $('#main')});
        },
        
        worst: function( ){
        	$(document).trigger('CloseView');
        	new WorstView({el: $('#main')});
        },
        
        elements: function( ){
        	$(document).trigger('CloseView');
        	new FightView({el: $('#main')});
        },
        
        search: function( ){
        	$(document).trigger('CloseView');
        	
        	var view = new SearchView({el: $('#main')});
        	$('div#searchDiv').find('input#criteria').keyup(function() {
        		view.contentChanged($(this).val());
        	});
        },
        
        about: function( ){
        	
        	$(document).trigger('CloseView');
        	new AboutView({el: $('#main')});
        },
        
        upload: function( ){
        	$(document).trigger('CloseView');
        	 new UploadView({el: $('#main')});
        }
    });
    
    return AppRouter;
});