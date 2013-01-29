package org.kepennar.facemash.controller;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.kepennar.facemash.model.Element;
import org.kepennar.facemash.service.SearchElementService;
import org.kepennar.facemash.util.ElementUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/api/search")
public class SearchController {
	
	@Inject @Named("searchElementService")
	private SearchElementService searchElementService;
	
	@Inject
	private ElementUtils elementUtils;
	
	@RequestMapping(value = "/{criteria}", method = RequestMethod.GET)
	@ResponseBody
	public List<Element> templateIndex(@PathVariable String criteria) {
		List<Element> searchResult = searchElementService.searchElement(criteria);
		if (searchResult.isEmpty()) {
			searchResult.add(elementUtils.newNoResultElement());
		}
		return searchResult;
	}

}