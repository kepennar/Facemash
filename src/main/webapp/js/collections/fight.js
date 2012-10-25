define(['backbone', 'models/fight'], function(Backbone, Fight){
 
    var Fights = Backbone.Collection.extend({

        // Reference to this collection's model.
        model: Fight,

        // URL of the 
        url: "api/elements/fight"

  });
  return new Fights;
});
