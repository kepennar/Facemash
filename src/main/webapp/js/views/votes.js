define([
  'underscore',
  'backbone',
  'atmosphere',
  'text!templates/totalVotes.html'
  ], function(_, Backbone, Atmosphere, templateSource){
  var WinnerView = Backbone.View.extend({
      
    // compile template
    template: _.template(templateSource),
        
    initialize: function(option) {
    	var self = this;
    	var socket = $.atmosphere;
    	var url = document.location.toString();
    	url = url.split("#")[0];
    	url += 'realtime/votes';
    	var request = {
    			url: url,
    			contentType : "application/json",
                logLevel : 'debug',
                transport : 'websocket' ,
                fallbackTransport: 'long-polling'
    	};
    	
    	request.onOpen = function(response) {
            console.log('Atmosphere connected using ' + response.transport);
        };

        request.onMessage = function (response) {
            detectedTransport = response.transport;
            if (response.status == 200) {
                var data = response.responseBody;
                console.log("data= " + data);
                if (data.length > 0) {
                	self.render(data);
                	
                }
            }
        };
    	
        subSocket = socket.subscribe(request);
    	
      _.bindAll(this, 'render');
      this.el = option.el;
    },

    render: function(totalVotes) {
      (this.el).html(this.template({totalVotes: totalVotes}));
    }

  });
  return WinnerView;
});