package org.kepennar.facemash.controller;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.kepennar.facemash.model.Element;
import org.kepennar.facemash.mvc.model.Fight;
import org.kepennar.facemash.service.ElementService;
import org.kepennar.facemash.util.CacheUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/api/elements")
public class ElementController {

	private static final Integer PAGE_SIZE = 5;
	@Inject @Named("elementService")
	private ElementService elementService;
	
	@Inject @Named("cacheUtil")
	private CacheUtil cacheUtil;
	
    
    @RequestMapping(value = "top/{page}", method = RequestMethod.GET)
    @ResponseBody
    List<Element> getTop(@PathVariable Integer page) {
    	return elementService.getTopRated(page - 1, PAGE_SIZE).getContent();
    }
    
    @RequestMapping(value = "worst/{page}", method = RequestMethod.GET)
    @ResponseBody
    List<Element> getWorst(@PathVariable Integer page) {
    	return elementService.getWorstRated(page - 1, PAGE_SIZE).getContent();
    }
   
    @RequestMapping(value = "winner", method = RequestMethod.GET)
    @ResponseBody
    Element getWinner() {
    	if (cacheUtil.getTolalPlayed() == null) {
    		cacheUtil.setTolalPlayed(elementService.getTotalPlayed());
    	}
    	Element element = elementService.getWinner();
    	element.setTotalPLayed(cacheUtil.getTolalPlayed());
    	return element;
    }
    
    @RequestMapping(value = "fight", method = RequestMethod.GET)
    @ResponseBody
    Fight getFight() {
    	return elementService.getFight();
    }
}
