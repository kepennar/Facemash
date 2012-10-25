define([ 'backbone', 'models/element' ], function(Backbone, Element) {

	var SearchCollection = Backbone.Collection.extend({

		// Reference to this collection's model.
		model : Element,

		url : function() {
			return "api/search/" + this.searchTerm;
		},
		search : function(searchTerm) {
			this.searchTerm = searchTerm;
			this.fetch({
				success : function() {
					MyApp.vent.trigger("search:results", results);
				},
				error : function(collection, response) {
					MyApp.vent.trigger("search:error", response);
				}
			});
		}

	});
	return new SearchCollection;
});
