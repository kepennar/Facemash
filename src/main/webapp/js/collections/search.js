define([ 'backbone', 'models/element' ], function(Backbone, Element) {

	var SearchCollection = Backbone.Collection.extend({

		// Reference to this collection's model.
		model : Element,

		url : function() {
			return "api/search/" + this.searchTerm;
		},
		search : function(searchTerm, onSuccess, onError) {
			this.searchTerm = searchTerm;
			this.fetch({
				success : onSuccess,
				error : onError
			});
		}

	});
	return new SearchCollection;
});
