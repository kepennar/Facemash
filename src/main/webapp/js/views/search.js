define([ 'underscore', 'backbone', 'collections/search', 'text!templates/search.html' ], 
	function(_, Backbone, Search, templateSource) {
		var SearchView = Backbone.View.extend({
		// compile template
		template : _.template(templateSource),
		
		initialize : function(option) {
		
			_.bindAll(this, 'render');
			this.el = option.el;
		
			$(document).bind('CloseView', this.close);
			$("#main").fadeIn(500);
			
			Search.search("wat", this.render, function() {console.log("Error in searchView");});
		},
		
		events : {
			"change input#criteria" : "contentChanged"
		}, 
		
		render : function(model) {
			(this.el).html(this.template({
				elements : model.toJSON()
			}));
			this.criteriaInput = this.el.find('input#criteria');
		},
		
		contentChanged : function() {
			console.log("change search term");
			var searchTerm = this.criteriaInput.val();
			console.log("searchterm = " + searchTerm);
			Search.search(searchTerm, this.render, function() {console.log("Error in searchView");});
		},
		
		close : function() {
			$(this).unbind();
			$("#main").hide();
		},
		
	});
	return SearchView;
});
