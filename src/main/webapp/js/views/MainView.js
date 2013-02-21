define([
	'backbone',
	'text!templates/alert.html'
	], function(Backbone, templateAlert) {
	
	var MainView = Backbone.View.extend({
		
		templateAlert: _.template(templateAlert),
		alertElem: "#alertDiv",
	
		initialize: function(option) {
			console.log("Initializing MainView");
			this.el = option.el;
			$("#main").fadeIn();
			
			var self = this;
			this.el.bind({
			    ajaxError: function(e, xhr, options) {
			        self.displayAlert(self, "Error", xhr.responseText);
			 
			    }
			});
		},
		displayAlert: function(scope, title, message) {
			var infos = {title: title, message: message};
			$(scope.alertElem).html(
					scope.templateAlert({info: infos}
			));
			
			$(scope.alertElem).alert();
			
		} 
	});
	return MainView;
});