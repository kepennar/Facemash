define([ 'underscore', 'backbone' ], function(_, Backbone) {
	var VoteModel = Backbone.Model.extend({

		url : function() {
			return 'api/vote';
		},
		// Remove this Sample from *localStorage*.
		clear : function() {
			this.destroy();
		}

	});
	return VoteModel;
});
