define(['backbone', 'models/element'], function(Backbone, Element){
 
    var TopCollection = Backbone.Collection.extend({

        // Reference to this collection's model.
        model: Element,

        // URL of the 
        url: "api/elements/top/1"

  });
  return new TopCollection;
});
