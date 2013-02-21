define([
	'underscore',
	'mainView',
	'collections/fight',
	'models/vote',
	'text!templates/fight.html'
	], function(_, MainView, Fight, Vote, templateSource) {
	var FightView = MainView.extend({
		
		template : _.template(templateSource),

		initialize : function(option) {
			this.constructor.__super__.initialize.apply(this, [ option ]);

			$(this.el).unbind();
			var self = this;

			Fight.on("reset", self.render, self);

			$(document).bind('CloseView', this.close);
			

			Fight.fetch();

		},

		render : function() {
			$(this.el).find(".thumbnail").fadeIn(800);
			$(this.el).html(this.template({
				fights : Fight.toJSON()
			}));
			return $(this.el);
		},

		vote : function(event) {
			$(this.el).find(".thumbnail").fadeOut();
			var targ = (window.event) ? window.event.srcElement : event.target;
			var ids = $(targ).closest("a").attr('id');
			var splittedIds = ids.split("-");
			var winnerId = splittedIds[0];
			var loserId = splittedIds[1];
			var vote = new Vote();

			vote.save({
				winnerId : winnerId,
				loserId : loserId
			}, {
				success : function(model, response) {
					Fight.fetch();
				},
				error : function(model, response) {
					console.log("error");
				}
			});
		},
		close : function() {
			$(this).unbind();
			$("#main").hide();
		},

		events : {
			"click a.vote" : "vote"
		}
	});
	return FightView;
});