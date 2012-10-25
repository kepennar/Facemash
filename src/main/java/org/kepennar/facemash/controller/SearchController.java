package org.kepennar.facemash.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/api/search")
public class SearchController {
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/{criteria}", method = RequestMethod.GET)
	@ResponseBody
	public String templateIndex(@PathVariable String criteria) {
		return "searched!!!";
	}

}