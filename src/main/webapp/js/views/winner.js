define([
  'underscore',
  'mainView',
  'collections/winner',
  'text!templates/winner.html'
  ], function(_, MainView, Elements, templateSource){
  var WinnerView = MainView.extend({
      
    // compile template
    template: _.template(templateSource),
        
    initialize: function(option) {
    	this.constructor.__super__.initialize.apply(this, [ option ]);
    	
    	$(document).bind( 'CloseView', this.close );
    	_.bindAll(this, 'render');
    	
    	$(".hero-unit").fadeIn(500);
    	
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
