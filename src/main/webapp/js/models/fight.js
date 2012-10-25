define([ 'underscore', 'backbone' ], function(_, Backbone) {
	var FightModel = Backbone.Model.extend({

		
		// Remove this Sample from *localStorage*.
		clear : function() {
			this.destroy();
		}

	});
	return FightModel;
});
