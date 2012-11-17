define([
  'underscore',
  'backbone',
  'collections/search',
  'text!templates/elements.html'
  ], function(_, Backbone, Elements, templateSource){
  var WinnerView = Backbone.View.extend({
      
    // compile template
    template: _.template(templateSource),
        
    initialize: function(option) {
    	
      _.bindAll(this, 'render');
      this.el = option.el;
      
      $(document).bind( 'CloseView', this.close );
  	$("#main").fadeIn(500);
      
      Elements.fetch({success: this.render});
    },

    render: function(model) {
      (this.el).html(this.template({elements: model.toJSON()}));
    },
    close: function() {
    	  $(this).unbind();
    	$("#main").hide();
    }

  });
  return WinnerView;
});
