package org.kepennar.facemash.controller;

import javax.inject.Inject;
import javax.inject.Named;

import org.kepennar.facemash.mvc.model.Vote;
import org.kepennar.facemash.service.VoteService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping(value = "/api/vote")
public class VoteController {

	@Inject @Named("voteService")
	private VoteService voteService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value=HttpStatus.OK)
    public void vote( @RequestBody Vote vote) {	
    	voteService.vote(vote.getWinnerId(), vote.getLoserId());
    	
    }
}
