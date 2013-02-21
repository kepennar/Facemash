define([
  'underscore',
  'mainView',
  'text!templates/upload.html',
  'jqueryform'
  ], function(_, MainView, templateSource){
  var UploadView = MainView.extend({
    
	  template: _.template(templateSource),
	  initialize: function(option) {
		  this.constructor.__super__.initialize.apply(this, [option]);
		  
		  _.bindAll(this, 'render');
		  $(document).bind( 'CloseView', this.close );
		  
		  this.render();
		},
	
		render: function(model) {
			(this.el).html(this.template());
		},
		close: function() {
			$(this).unbind();
			$("#main").hide();
		}    
  });
  return UploadView;
});