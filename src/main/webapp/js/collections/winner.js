define(['backbone', 'models/element'], function(Backbone, Element){
 
    var WinnerCollection = Backbone.Collection.extend({

        // Reference to this collection's model.
        model: Element,

        // URL of the 
        url: "api/elements/winner"

  });
  return new WinnerCollection;
});
