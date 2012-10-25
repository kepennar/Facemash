define(['jquery'], function($) {
	$(document).ready(function() {
		setNav();
		$("a").click( function() {
			setNav(this);
		});
		
	});
	
	function setNav(elem) {
		if(elem == null) {
			var anchor = getUrlAnchor();
		} else {
			anchor = $(elem).attr("id");
		}
		
		if (!anchor || 0 === anchor.length) {
			anchor = "home";
		}
		$("li[class='active']").removeClass("active");
		$("li[id='"+ anchor + "']").addClass("active");
		
	}
	
	function getUrlVars() {
	    var vars = [], hash;
	    var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
	    for(var i = 0; i < hashes.length; i++)
	    {
	        hash = hashes[i].split('=');
	        vars.push(hash[0]);
	        vars[hash[0]] = hash[1];
	    }
	    return vars;
	}
	function getUrlAnchor() {
	   var sharpIndex = window.location.href.indexOf('#');
	   if (sharpIndex === -1) {
		   return null;
	   }
	   var anchor = window.location.href.slice(window.location.href.indexOf('#') + 1);
	   return anchor;
	}
});