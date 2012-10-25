define(['backbone', 'models/element'], function(Backbone, Element){
 
    var WortsCollection = Backbone.Collection.extend({

        // Reference to this collection's model.
        model: Element,

        // URL of the 
        url: "api/elements/worst/1"

  });
  return new WortsCollection;
});
