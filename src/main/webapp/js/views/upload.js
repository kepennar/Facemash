define([
  'underscore',
  'backbone',
  'text!templates/upload.html',
  'jqueryform'
  ], function(_, Backbone, templateSource){
  var UploadView = Backbone.View.extend({
      
    // compile template
    template: _.template(templateSource),
    initialize: function(option) {
        _.bindAll(this, 'render');
        this.el = option.el;
        
        $(document).bind( 'CloseView', this.close );
    	$("#main").fadeIn(500);
        
        this.render();
      },

    render: function(model) {
        (this.el).html(this.template());
      } ,
      close: function() {
      	  $(this).unbind();
    	  $("#main").hide();
      }    
    

  });
  return UploadView;
});