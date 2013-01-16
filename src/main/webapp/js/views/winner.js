define([
  'underscore',
  'backbone',
  'collections/winner',
  'text!templates/winner.html'
  ], function(_, Backbone, Elements, templateSource){
  var WinnerView = Backbone.View.extend({
      
    // compile template
    template: _.template(templateSource),
        
    initialize: function(option) {
    	$(document).bind( 'CloseView', this.close );
    	$("#main").fadeIn(500);
    	$(".hero-unit").fadeIn(500);
    	_.bindAll(this, 'render');
    	this.el = option.el;
    	Elements.fetch({success: this.render});
    },

    render: function(model) {
      (this.el).html(this.template({elements: model.toJSON()}));
    },
    close: function() {
    	  $(this).unbind();
    	$("#main").hide();
    	$(".hero-unit").hide();
    }

  });
  return WinnerView;
});
