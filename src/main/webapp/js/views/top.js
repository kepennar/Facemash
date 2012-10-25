define([
  'underscore',
  'backbone',
  'collections/top',
  'text!templates/elements.html'
  ], function(_, Backbone, Elements, templateSource){
  var TopView = Backbone.View.extend({
      
    // compile template
    template: _.template(templateSource),
        
    initialize: function(option) {
      _.bindAll(this, 'render');
      this.el = option.el;
      Elements.fetch({success: this.render});
    },

    render: function(model) {
      (this.el).html(this.template({elements: model.toJSON()}));
    }

  });
  return TopView;
});
