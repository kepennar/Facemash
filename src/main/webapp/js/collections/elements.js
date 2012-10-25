define(['backbone', 'models/element'], function(Backbone, Element){
 
    var ElementsCollection = Backbone.Collection.extend({

        // Reference to this collection's model.
        model: Element,

        // URL of the 
        url: "api/elements"

  });
  return new ElementsCollection;
});
