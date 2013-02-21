define([
	'underscore',
	'mainView',
	'collections/search',
	'text!templates/search.html'
	], function(_, MainView, Search, templateSource) {
	var SearchView = MainView.extend({
		
		// compile templates
		template : _.template(templateSource),
	    
		initialize : function(option) {
			this.constructor.__super__.initialize.apply(this, [ option ]);
			
			var self = this;
			$("#searchDiv").show();
        	
			_.bindAll(this, 'render');
			
			$(document).bind('CloseView', this.close);
			
			this.el.html("");
			
		},
		
		render : function(model) {
			(this.el).html(this.template({
				elements : model.toJSON()
			}));
			
		},
		
		contentChanged : function(searchTerm) {
			if (typeof searchTerm !== 'undefined' && searchTerm.length > 3) {
				Search.search(searchTerm, this.render, function() {console.log("Error in searchView");});
			}
		},
		
		close : function() {
			$(this).unbind();
			$('div#searchDiv').find('input#criteria').unbind();
			$("#searchDiv").hide(100);
			$("#main").hide();
		}
	
	});
	return SearchView;
});
