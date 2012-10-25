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
        this.render();
      },

    render: function(model) {
        (this.el).html(this.template());
      }     
    

  });
  return UploadView;
});