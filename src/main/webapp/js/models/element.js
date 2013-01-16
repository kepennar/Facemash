define([ 'underscore', 'backbone' ], function(_, Backbone) {
	var ElementModel = Backbone.Model.extend({

		// Default attributes 
		defaults : {
			id : "",
			name : "Empty name",
			imgUrl : "img/no-picture.jpg",
			description : "No description yet ..."
		},

		// Ensure that each sample created has 'name, 'imgUrl' & 'description'.
		initialize : function() {

			if (!this.get("name")) {
				this.set({
					"name" : this.defaults.name
				});
			}
			if (!this.get("imgUrl")) {
				this.set({
					"imgUrl" : "img/" + this.get("family") + "/"
							+ this.defaults.imgUrl
				});
			}
			if (!this.get("description")) {
				this.set({
					"description" : this.defaults.description
				});
			}
		},

		// Remove this Sample from *localStorage*.
		clear : function() {
			this.destroy();
		}

	});
	return ElementModel;
});
